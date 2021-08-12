package com.salary.model.system.welcome;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/system/welcome")
@Api(value="WelcomeModel",tags="欢迎界面")
public class WelcomeModel {

    @GetMapping("/welcome")
    public String welcome(){
        return "system/welcome/welcome";
    }
}
