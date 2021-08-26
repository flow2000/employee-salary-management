package com.salary.model.system.total;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@RequestMapping("/system/total")
@ApiIgnore
public class TotalModel {

    @GetMapping("/total")
    public String total(){
        return "system/total/total";
    }
}
