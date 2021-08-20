package com.salary.model.system.dept;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@RequestMapping("/system/dept")
@ApiIgnore
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

    @GetMapping("/merge")
    public String merge(){
        return "system/dept/merge";
    }
}
