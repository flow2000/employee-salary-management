package com.salary.model.system.role;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/system/role")
@Api(value="RoleModel",tags="角色管理界面")
public class RoleModel {

    @GetMapping("/role")
    public String role(){
        return "system/role/role";
    }

    @GetMapping("/insert")
    public String insert(){
        return "system/role/insert";
    }

    @GetMapping("/update")
    public String update(){
        return "system/role/update";
    }
}
