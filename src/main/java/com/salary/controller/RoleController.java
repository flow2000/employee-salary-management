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
     * @param type 类型
     * @param content 值
     * @return 角色信息
     */
    @ApiOperation(value = "获取单个角色信息")
    @ApiImplicitParam()
    @GetMapping("/getOneRole")
    public AjaxResult getOneRole(String type,String content){
        return roleService.getOneRole(type,content);
    }

    /**
     * 搜索角色
     * @param page 页码
     * @param limit 数量
     * @param searchKey 字段
     * @param searchValue 字段值
     * @return 查询结果
     */
    @ApiOperation(value = "搜索角色")
    @ApiImplicitParam
    @GetMapping("/searchRole")
    public AjaxResult searchRole(@RequestParam int page,@RequestParam int limit,
                                 @RequestParam String searchKey,@RequestParam String searchValue){
        return  roleService.searchRole(page,limit,searchKey,searchValue);
    }

    /**
     * 添加角色信息
     * @param map 角色信息
     * @return 成功或者失败消息
     */
    @ApiOperation(value = "获取单个角色信息")
    @ApiImplicitParam()
    @PostMapping("/insertRole")
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
    @PostMapping("/updateRole")
    public AjaxResult updateRole(@RequestBody Map<String, Object> map){
        return roleService.updateRole(map);
    }

    /**
     * 修改角色状态
     * @param map 角色信息
     * @return 成功或者失败消息
     */
    @ApiOperation(value = "修改角色状态")
    @ApiImplicitParam()
    @PostMapping("/changeRoleStatus")
    public AjaxResult changeRoleStatus(@RequestBody Map<String, Object> map){
        return roleService.changeRoleStatus(map);
    }

    /**
     * 删除角色信息
     * @param map 角色信息
     * @return 成功或者失败消息
     */
    @ApiOperation(value = "删除角色信息")
    @ApiImplicitParam()
    @PostMapping("/deleteRole")
    public AjaxResult deleteRole(@RequestBody Map<String, Object> map){
        return roleService.deleteRole(map);
    }
}
