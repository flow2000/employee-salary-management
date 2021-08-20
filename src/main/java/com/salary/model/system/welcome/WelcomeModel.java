package com.salary.model.system.welcome;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@RequestMapping("/system/welcome")
@ApiIgnore
public class WelcomeModel {

    @GetMapping("/welcome")
    public String welcome(){
        return "system/welcome/welcome";
    }
}
