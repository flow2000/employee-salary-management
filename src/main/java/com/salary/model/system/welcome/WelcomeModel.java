package com.salary.model.system.welcome;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/system/welcome")
public class WelcomeModel {

    @GetMapping("/welcome")
    public String welcome(){
        return "system/welcome/welcome";
    }
}
