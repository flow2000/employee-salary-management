package com.salary.controller;

import com.salary.exception.GlobalException;
import com.salary.service.UserService;
import com.salary.util.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


@RestController
@Api(value="UserController",tags="用户接口")
@RequestMapping("/api")
public class UserController extends GlobalException {

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
    @RequiresPermissions("system:user:view")
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
    @RequiresPermissions("system:user:view")
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
    @RequiresPermissions("system:user:insert")
    @PostMapping("/user/insertUser")
    public AjaxResult insertUser(@RequestBody Map<String, Object> map){
        return userService.insertUser(map);
    }


    /**
     * 搜索用户
     * @param page 页码
     * @param limit 数量
     * @param searchKey 字段
     * @param searchValue 字段值
     * @return 查询结果
     */
    @ApiOperation(value = "搜索用户")
    @ApiImplicitParam
    @RequiresPermissions("system:user:select")
    @GetMapping("/user/searchUser")
    public AjaxResult searchUser(@RequestParam int page,@RequestParam int limit,
                                   @RequestParam String searchKey,@RequestParam String searchValue){
        return  userService.searchUser(page,limit,searchKey,searchValue);
    }

    /**
     * 修改个人信息
     * @param map 个人信息
     * @return 成功或者失败消息
     */
    @ApiOperation(value = "修改个人信息")
    @ApiImplicitParam()
    @PostMapping("/user/updatePerson")
    public AjaxResult updatePerson(@RequestBody Map<String, Object> map){
        return userService.updatePerson(map);
    }

    /**
     * 修改个人密码
     * @param map 个人信息-
     * @return 成功或者失败消息
     */
    @ApiOperation(value = "修改个人密码")
    @ApiImplicitParam()
    @PostMapping("/user/updatePersonPassword")
    public AjaxResult updateUPersonPassword(@RequestBody Map<String, Object> map){
        return userService.updatePersonPassword(map);
    }

    /**
     * 验证个人密码
     * @param map 个人信息
     * @return 成功或者失败消息
     */
    @ApiOperation(value = "验证个人密码")
    @ApiImplicitParam()
    @PostMapping("/user/verifyPersonPassword")
    public AjaxResult verifyPersonPassword(@RequestBody Map<String, Object> map){
        return userService.verifyPersonPassword(map);
    }

    /**
     * 修改用户信息
     * @param map 用户信息
     * @return 成功或者失败消息
     */
    @ApiOperation(value = "修改用户信息")
    @ApiImplicitParam()
    @RequiresPermissions("system:user:update")
    @PostMapping("/user/updateUser")
    public AjaxResult updateUser(@RequestBody Map<String, Object> map){
        return userService.updateUser(map);
    }

    /**
     * 重置用户密码
     * @param map 用户信息
     * @return 成功或者失败消息
     */
    @ApiOperation(value = "重置用户密码")
    @ApiImplicitParam()
    @RequiresPermissions("system:user:reset")
    @PostMapping("/user/resetUserPassword")
    public AjaxResult resetUserPassword(@RequestBody Map<String, Object> map){
        return userService.resetUserPassword(map);
    }

    /**
     * 修改用户状态
     * @param map 用户信息
     * @return 成功或者失败消息
     */
    @ApiOperation(value = "修改用户状态")
    @ApiImplicitParam()
    @RequiresPermissions("system:user:update")
    @PostMapping("/user/changeUserStatus")
    public AjaxResult changeUserStatus(@RequestBody Map<String, Object> map){
        return userService.changeUserStatus(map);
    }

    /**
     * 删除用户信息
     * @param map 用户信息
     * @return 成功或者失败消息
     */
    @ApiOperation(value = "删除用户信息")
    @ApiImplicitParam()
    @RequiresPermissions("system:user:delete")
    @PostMapping("/user/deleteUser")
    public AjaxResult deleteUser(@RequestBody Map<String, Object> map){
        return userService.deleteUser(map);
    }

}
