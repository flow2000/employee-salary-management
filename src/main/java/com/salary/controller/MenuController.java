package com.salary.controller;

import com.salary.entity.Menu;
import com.salary.entity.Ztree;
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

import java.util.List;

@RestController
@Api(value="MenuController",tags="菜单接口")
@RequestMapping("/api/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 获取所有菜单信息
     * @return 菜单信息
     */
    @ApiOperation(value = "获取所有菜单信息")
    @ApiImplicitParam()
    @GetMapping("/getAllMenu")
    public List<Menu> getAllMenu(){
        return menuService.getAllMenu();
    }

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

    /**
     * 获取菜单信息
     * @return 菜单信息
     */
    @ApiOperation(value = "获取菜单信息")
    @ApiImplicitParam()
    @GetMapping("/getMenu")
    public Object getMenu(int role_id){
        return menuService.getMenu(role_id);
    }

    /**
     * 获取角色指定的菜单树信息
     * @return 角色指定的菜单树信息
     */
    @ApiOperation(value = "获取角色指定的菜单树信息")
    @ApiImplicitParam()
    @GetMapping("/getRoleMenuTree")
    public List<Ztree> getRoleMenuTree(int role_id,int user_id){
        return menuService.getRoleMenuTree(role_id,user_id);
    }
}
