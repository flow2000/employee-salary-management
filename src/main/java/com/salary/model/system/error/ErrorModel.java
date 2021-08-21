package com.salary.model.system.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@RequestMapping("/error")
@ApiIgnore
public class ErrorModel {

    @GetMapping("/noPermission")
    public String dept(){
        return "error/noPermission";
    }

}
