package com.salary.model.system.salary;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@RequestMapping("/system/salary")
@ApiIgnore
public class ConfigModel {

    @GetMapping("/config")
    public String config(){
        return "/system/salary/config/config";
    }

    @GetMapping("/update")
    public String update(){
        return "/system/salary/config/update";
    }
}
