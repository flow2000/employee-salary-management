package com.salary.model.system.salary;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@RequestMapping("/system/salary")
@ApiIgnore
public class SettingModel {

    @GetMapping("/setting")
    public String role(){
        return "system/salary/setting";
    }
}
