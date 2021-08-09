package com.salary.service.imp;

import cn.hutool.captcha.ShearCaptcha;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.salary.dao.UserDao;
import com.salary.entity.User;
import com.salary.service.TokenService;
import com.salary.service.UserService;
import com.salary.util.AjaxResult;
import com.salary.util.DigestPass;
import com.salary.util.SaveCaptcha;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UserServiceImp implements UserService {

    private static final String CACHE_NAME_USER = "users";
    private static final int REDIS_TIMEOUT = 1; //redis过期时间1小时

    @Autowired
    private UserDao userDao;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 验证码生成
     * @param request 请求
     * @param response 响应
     */
    @Override
    public void getVerify(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ShearCaptcha captcha = SaveCaptcha.getCaptcha();  //获取验证码图片
        SaveCaptcha.saveSession(request,captcha);        //将答案存到session
        System.out.println("验证码："+captcha.getCode());//打印到控制台
        ServletOutputStream outputStream = response.getOutputStream();//写入到会话响应
        captcha.write(outputStream);
        outputStream.close();
    }

    /**
     * 登录
     * @param request 请求
     * @param param 登录参数
     *
     * @return 登录成功或登录失败的消息
     */
    @Override
    public AjaxResult login(HttpServletRequest request, Map<String, Object> param) {
        String userCaptcha = (String) param.get("captcha");
        HttpSession session = request.getSession(true);//获得session对象
        String key = session.getId();                     //获得sessionId
        String trueCaptcha = (String) session.getAttribute(key); //拿到正确的验证码
        if(Objects.equals(trueCaptcha,userCaptcha)){ //比较验证码是否一致
            User user = userDao.getOneUser((String)param.get("login_name")); //数据库查找用户
            if(user!=null){ //用户不存在
                DigestPass dp=new DigestPass();  //MD5摘要算法
                String password = dp.getDigestString(param.get("password")+user.getSalt()); //盐加密
                if(Objects.equals(password,user.getPassword())){ //数据库的密码与盐加密后的密码比较是否一致
                    Map<String, Object> map = new HashMap<>();
                    user.setPassword("******");
                    map.put("user",user);
                    String token = tokenService.getToken(user); //根据user生成token
                    map.put("token",token);
                    redisTemplate.boundHashOps(user.getLogin_name()).putAll(map); //将map存进redis
                    redisTemplate.expire(user.getLogin_name(), REDIS_TIMEOUT, TimeUnit.HOURS); //设置过期时间
                    return AjaxResult.success("登录成功",map);
                }
            }
            return AjaxResult.error("用户或密码错误");
        }
        return AjaxResult.error("验证码错误");
    }

    @Override
    public AjaxResult init(Map<String, Object> map) {
        String login_name = (String) map.get("login_name");
        if(login_name==null){
            return AjaxResult.error("登录名为空");
        }
        Map<String, Object> resultMap = new HashMap<>();
//        Map<String,Object> userMap
        return null;
    }

    /**
     * 获取所有用户
     *
     * @return 用户信息
     */
    @Override
    public AjaxResult getAllUser() {
        return AjaxResult.returnMessage(userDao.getAllUser());
    }

    /**
     * 分页获取用户
     * @param page 页码
     * @param limit 每页数量量
     *
     * @return 用户信息
     */
    @Override
    public AjaxResult getPageUser(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);//开始分页
        List<Map> userList = userDao.getPageUser(page,limit); //拼接sql语句
        return AjaxResult.returnMessage(new PageInfo<>(userList)); //将分页结果放入pageUserList
    }

    /**
     * 获取单个用户信息
     * @param login_name 登录名
     *
     * @return 用户信息
     */
    @Override
    public AjaxResult getOneUser(String login_name) {
        return AjaxResult.returnMessage(userDao.getOneUser(login_name));
    }



    /**
     * 添加用户信息
     * @param map 用户信息
     *
     * @return 影响的行数
     */
    @Override
    public AjaxResult insertUser(Map<String, Object> map) {
        return AjaxResult.toAjax(userDao.insertUser(map));
    }

    /**
     * 修改用户信息
     * @param map 用户信息
     *
     * @return 影响的行数
     */
    @Override
    public AjaxResult updateUser(Map<String, Object> map) {
        return AjaxResult.toAjax(userDao.updateUser(map));
    }

    /**
     * 删除用户信息
     * @param map 用户信息
     *
     * @return 影响的行数
     */
    @Override
    public AjaxResult deleteUser(Map<String, Object> map) {
        return AjaxResult.toAjax(userDao.deleteUser(map));
    }

}