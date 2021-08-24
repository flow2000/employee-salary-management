package com.salary.model.system.salary;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@RequestMapping("/system/salary/input")
@ApiIgnore
public class InputModel {

    @GetMapping("/input")
    public String input(){
        return "system/salary/input/input";
    }

    @GetMapping("/update")
    public String update(){
        return "system/salary/input/update";
    }
}
