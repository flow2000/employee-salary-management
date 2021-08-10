package com.salary.controller;

import com.salary.service.MenuService;
import com.salary.service.RoleService;
import com.salary.util.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value="MenuController",tags="菜单接口")
@RequestMapping("/api/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 获取角色指定的菜单信息
     * @return 角色指定的菜单信息
     */
    @ApiOperation(value = "获取角色指定的菜单信息")
    @ApiImplicitParam()
    @GetMapping("/getRoleMenu")
    public AjaxResult getRoleMenu(int role_id){
        return menuService.getRoleMenu(role_id);
    }
}
