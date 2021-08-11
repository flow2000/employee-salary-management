package com.salary.service.imp;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.salary.entity.User;
import com.salary.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class TokenServiceImp implements TokenService {

    private static final String TOKEN_SECRET="lAGC8k";  //密钥盐
    private static final String ISSUER="auth0";  //发行人
    private static final long EXPIRE_TIME= 60 * 60 * 1000;//token到期时间1小时
    private static final int REDIS_TIMEOUT = 1; //redis过期时间1小时
    SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.CHINA);//格式化CST时间

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public String getToken(User user) {
        String token=null;
        try {
            token = JWT.create()
                    .withIssuer(ISSUER)//发行人
                    .withClaim("login_name",user.getLogin_name())//存放数据
                    .withExpiresAt(new Date(System.currentTimeMillis()+EXPIRE_TIME))//过期时间
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));
        } catch (IllegalArgumentException | JWTCreationException je) {
            return null;
        }
        return token;
    }

    @Override
    public Boolean verifyToken(String login_name, String token) {
        String redisToken = (String) redisTemplate.boundHashOps(login_name).get("token");
        if(!Objects.equals(token,redisToken)){  //与存在redis对应用户的token比较
            return false;
        }
        //解密token
        try {
            JWTVerifier jwtVerifier=JWT.require(Algorithm.HMAC256(TOKEN_SECRET))
                    .withIssuer(ISSUER).build();//创建token验证器
            DecodedJWT decodedJWT=jwtVerifier.verify(token);
            redisTemplate.expire(login_name, REDIS_TIMEOUT, TimeUnit.HOURS); //刷新过期时间
            System.out.println("认证通过：");
            System.out.println("login_name: " + decodedJWT.getClaim("login_name").asString());
            System.out.println("过期时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").
                    format(decodedJWT.getExpiresAt()) );
        } catch (IllegalArgumentException | JWTVerificationException e) {
            //抛出错误即为验证不通过
            return false;
        }
        return true;
    }


}
