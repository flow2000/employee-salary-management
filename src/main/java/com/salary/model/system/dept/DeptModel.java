package com.salary.model.system.dept;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/system/dept")
@Api(value="DeptModel",tags="部门管理界面")
public class DeptModel {

    @GetMapping("/dept")
    public String dept(){
        return "system/dept/dept";
    }

    @GetMapping("/insert")
    public String insert(){
        return "system/dept/insert";
    }

    @GetMapping("/update")
    public String update(){
        return "system/dept/update";
    }
}
