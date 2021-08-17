package com.salary.service.imp;

import cn.hutool.captcha.ShearCaptcha;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.salary.dao.DeptDao;
import com.salary.dao.MenuDao;
import com.salary.dao.RoleDao;
import com.salary.dao.UserDao;
import com.salary.entity.Menu;
import com.salary.entity.User;
import com.salary.service.MenuService;
import com.salary.service.TokenService;
import com.salary.service.UserService;
import com.salary.util.AjaxResult;
import com.salary.util.DigestPass;
import com.salary.util.SaveCaptcha;
import com.salary.util.StringUtils;
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
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UserServiceImp implements UserService {

    private static final int REDIS_TIMEOUT = 1; //redis过期时间1小时
    private static final String PASSWORD = "password"; //用户密码标识符

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

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private DeptDao deptDao;

    @Autowired
    private MenuService menuService;

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
        if(!Objects.equals(trueCaptcha,userCaptcha)){ //比较验证码是否一致
            User user = userDao.getOneUser((String)param.get("login_name")); //数据库查找用户
            if(user!=null){ //用户不存在
                DigestPass dp=new DigestPass();  //MD5摘要算法
                String password = dp.getDigestString(param.get(PASSWORD)+user.getSalt()); //盐加密
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

    /**
     * 项目初始化
     * @param request 请求参数
     *
     * @return 可持久化数据
     */
    @Override
    public AjaxResult init(HttpServletRequest request) {
        String login_name = request.getHeader("login_name");
        int role_id = Integer.parseInt(request.getParameter("role_id"));
        if(login_name==null){
            return AjaxResult.error("登录名为空");
        }
        Map<String, Object> resultMap = new HashMap<>();
        List<Map> roleList = roleDao.getAllRole();
        List<Map> deptList = deptDao.getAllDept();
        Map<String, Object> menuMap = menuService.getMenu(role_id);

        resultMap.put("roles",roleList);
        resultMap.put("depts",deptList);
        resultMap.put("menus",menuMap);
        return AjaxResult.returnMessage(resultMap);
    }

    /**
     * 获取所有用户
     *
     * @return 用户信息
     */
    @Override
    public AjaxResult getAllUser() {
        List<Map> list = userDao.getAllUser();
        for (int i = 0; i < list.size(); i++) {
            Map<String,Object> map = list.get(i);
            map.put(PASSWORD,"******");
            list.set(i,map);
        }
        return AjaxResult.returnMessage(list);
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
        resetUserPassword(userList);
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
        User user = userDao.getOneUser(login_name);
        if(user!=null) {
            user.setPassword("*******");
        }
        return AjaxResult.returnMessage(user);
    }

    /**
     * 修改个人信息
     * @param map 个人信息
     *
     * @return 影响的行数
     */
    @Override
    public AjaxResult updatePerson(Map<String, Object> map) {
        return AjaxResult.toAjax(userDao.updatePerson(map));
    }

    /**
     * 修改个人密码
     * @param param 个人信息
     * @return 成功或者失败消息
     */
    @Override
    public AjaxResult updatePersonPassword(Map<String, Object> param) {
        User user = userDao.getOneUser((String)param.get("login_name")); //数据库查找用户
        DigestPass dp=new DigestPass();  //MD5摘要算法
        String password = dp.getDigestString(param.get(PASSWORD)+user.getSalt()); //盐加密
        param.put(PASSWORD,password);
        return AjaxResult.toAjax(userDao.updatePersonPassword(param));
    }

    /**
     * 验证个人密码
     * @param param 个人信息
     * @return 成功或者失败消息
     */
    @Override
    public AjaxResult verifyPersonPassword(Map<String, Object> param) {
        User user = userDao.getOneUser((String)param.get("login_name")); //数据库查找用户
        if(user!=null){ //用户不存在
            DigestPass dp=new DigestPass();  //MD5摘要算法
            String password = dp.getDigestString(param.get(PASSWORD)+user.getSalt()); //盐加密
            if(Objects.equals(password,user.getPassword())){
                return AjaxResult.success();
            }
        }
        return AjaxResult.error("密码错误");
    }


    /**
     * 添加用户信息
     * @param map 用户信息
     *
     * @return 影响的行数
     */
    @Override
    public AjaxResult insertUser(Map<String, Object> map) {
        String login_name= (String) map.get("login_name");
        if(userDao.getOneUser(login_name)!=null){
            return AjaxResult.error("用户已存在");
        }
        String salt = StringUtils.getSalt();
        map.put("salt",salt);
        DigestPass dp=new DigestPass();  //MD5摘要算法
        String password = dp.getDigestString(map.get(PASSWORD)+salt); //盐加密
        map.put(PASSWORD,password);
        if(userDao.insertUser(map)==0){
            return AjaxResult.error("添加失败");
        }
        User user = userDao.getOneUser(login_name);
        map.put("user_id",user.getUser_id());
        return AjaxResult.toAjax(userDao.insertUserRole(map));
    }

    /**
     * 搜索用户
     * @param page 页码
     * @param limit 数量
     * @param searchKey 字段
     * @param searchValue 字段值
     * @return 查询结果
     */
    @Override
    public AjaxResult searchUser(int page, int limit, String searchKey, String searchValue) {
        PageHelper.startPage(page, limit); //开始分页
        List<Map> queryList = StringUtils.strToMapList(searchKey,searchValue);
        List<Map> userList = userDao.searchUser(queryList); //拼接sql语句
        resetUserPassword(userList);
        return AjaxResult.returnMessage(new PageInfo<>(userList)); //将分页结果放入pageUserList
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

    /**
     * 修改用户信息
     * @param map 用户信息
     *
     * @return 影响的行数
     */
    @Override
    public AjaxResult updateUser(Map<String, Object> map) {
        userDao.updateUserRole(map);
        return AjaxResult.toAjax(userDao.updateUser(map));
    }

    /**
     * 重置用户密码
     * @param map 用户信息
     * @return 成功或者失败消息
     */
    @Override
    public AjaxResult resetUserPassword(Map<String, Object> map) {
        User user = userDao.getOneUser((String)map.get("login_name")); //数据库查找用户
        DigestPass dp=new DigestPass();  //MD5摘要算法
        String password = dp.getDigestString(map.get(PASSWORD)+user.getSalt()); //盐加密
        map.put(PASSWORD,password);
        return AjaxResult.toAjax(userDao.resetUserPassword(map));
    }

    /**
     * 将获得的用户数据中的用户密码重置
     * @param userList 用户数组
     */
    void resetUserPassword(List<Map> userList){
        for (int i = 0; i < userList.size(); i++) {
            Map map = userList.get(i);
            map.put(PASSWORD,"******");
            userList.set(i,map);
        }
    }
}