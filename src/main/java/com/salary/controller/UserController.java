package com.salary.controller;

import com.salary.service.UserService;
import com.salary.util.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


@RestController
@Api(value="UserController",tags="用户接口")
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 验证码生成
     * @return 验证码
     */
    @GetMapping("/getVerify")
    public void getVerify(HttpServletRequest request, HttpServletResponse response) throws IOException {
        userService.getVerify(request,response);
    }

    /**
     * 登录
     * @return 登录成功或者失败消息
     */
    @ApiOperation(value = "登录")
    @ApiImplicitParam()
    @PostMapping("/login")
    public AjaxResult login(HttpServletRequest request, @RequestBody Map<String, Object> map) {
        return userService.login(request,map);
    }

    /**
     * 项目初始化
     * @return 用户，角色，菜单等消息
     */
    @ApiOperation(value = "项目初始化")
    @ApiImplicitParam()
    @PostMapping("/init")
    public AjaxResult init(HttpServletRequest request) {
        return userService.init(request);
    }

    /**
     * 获取所有用户信息
     * @return 所有用户信息
     */
    @ApiOperation(value = "获取所有的用户信息")
    @ApiImplicitParam()
    @GetMapping("/user/getAllUser")
    public AjaxResult getAllUser(){
        return userService.getAllUser();
    }

    /**
     * 分页获取用户信息
     * @param page 页码
     * @param limit 每页数量量
     * @return 用户信息
     */
    @ApiOperation(value = "分页获取用户信息")
    @ApiImplicitParam()
    @GetMapping("/user/getPageUser")
    public AjaxResult getPageUser(
            @RequestParam("page") Integer page,
            @RequestParam("limit") Integer limit){
        return userService.getPageUser(page,limit);
    }

    /**
     * 获取单个用户信息
     * @param login_name 登录名
     * @return 用户信息
     */
    @ApiOperation(value = "获取单个用户信息")
    @ApiImplicitParam()
    @GetMapping("/user/getOneUser")
    public AjaxResult getOneUser(@RequestParam("login_name") String login_name){
        return userService.getOneUser(login_name);
    }



    /**
     * 添加用户信息
     * @param map 用户信息
     * @return 成功或者失败消息
     */
    @ApiOperation(value = "获取单个用户信息")
    @ApiImplicitParam()
    @GetMapping("/user/insertUser")
    public AjaxResult insertUser(@RequestBody Map<String, Object> map){
        return userService.insertUser(map);
    }


    /**
     * 修改用户信息
     * @param map 用户信息
     * @return 成功或者失败消息
     */
    @ApiOperation(value = "修改用户信息")
    @ApiImplicitParam()
    @GetMapping("/user/updateUser")
    public AjaxResult updateUser(@RequestBody Map<String, Object> map){
        return userService.updateUser(map);
    }

    /**
     * 删除用户信息
     * @param map 用户信息
     * @return 成功或者失败消息
     */
    @ApiOperation(value = "删除用户信息")
    @ApiImplicitParam()
    @GetMapping("/user/deleteUser")
    public AjaxResult deleteUser(@RequestBody Map<String, Object> map){
        return userService.deleteUser(map);
    }

}
