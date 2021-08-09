package com.salary.controller;

import com.salary.service.RoleService;
import com.salary.util.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/role")
@Api(value="RoleController",tags="角色接口")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 获取所有角色信息
     * @return 所有角色信息
     */
    @ApiOperation(value = "获取所有的角色信息")
    @ApiImplicitParam()
    @GetMapping("/getAllRole")
    public AjaxResult getAllRole(){
        return roleService.getAllRole();
    }

    /**
     * 分页获取角色信息
     * @param page 页码
     * @param limit 每页数量量
     * @return 角色信息
     */
    @ApiOperation(value = "分页获取角色信息")
    @ApiImplicitParam()
    @GetMapping("/getPageRole")
    public AjaxResult getPageRole(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
        return roleService.getPageRole(page,limit);
    }

    /**
     * 获取单个角色信息
     * @param role_key 角色权限字符串
     * @return 角色信息
     */
    @ApiOperation(value = "获取单个角色信息")
    @ApiImplicitParam()
    @GetMapping("/getOneRole")
    public AjaxResult getOneRole(@RequestParam("role_key") String role_key){
        return roleService.getOneRole(role_key);
    }



    /**
     * 添加角色信息
     * @param map 角色信息
     * @return 成功或者失败消息
     */
    @ApiOperation(value = "获取单个角色信息")
    @ApiImplicitParam()
    @GetMapping("/insertRole")
    public AjaxResult insertRole(@RequestBody Map<String, Object> map){
        return roleService.insertRole(map);
    }


    /**
     * 修改角色信息
     * @param map 角色信息
     * @return 成功或者失败消息
     */
    @ApiOperation(value = "修改角色信息")
    @ApiImplicitParam()
    @GetMapping("/updateRole")
    public AjaxResult updateRole(@RequestBody Map<String, Object> map){
        return roleService.updateRole(map);
    }

    /**
     * 删除角色信息
     * @param map 角色信息
     * @return 成功或者失败消息
     */
    @ApiOperation(value = "删除角色信息")
    @ApiImplicitParam()
    @GetMapping("/deleteRole")
    public AjaxResult deleteRole(@RequestBody Map<String, Object> map){
        return roleService.deleteRole(map);
    }
}
