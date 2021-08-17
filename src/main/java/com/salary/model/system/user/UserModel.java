package com.salary.model.system.user;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/system/user")
@Api(value="UserModel",tags="用户管理界面")
public class UserModel {

    @GetMapping("/user")
    public String user(){
        return "system/user/user";
    }

    @GetMapping("/insert")
    public String insert(){
        return "system/user/insert";
    }

    @GetMapping("/update")
    public String update(){
        return "system/user/update";
    }

    @GetMapping("/reset")
    public String reset(){
        return "system/user/reset";
    }
}
