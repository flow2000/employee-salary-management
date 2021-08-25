package com.salary.model.system.watch;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@RequestMapping("/system/watch/all")
@ApiIgnore
public class AllSalaryModel {

    @GetMapping("/allSalary")
    public String all(){
        return "system/watch/all/allSalary";
    }

    @GetMapping("/more")
    public String more(){
        return "system/watch/all/more";
    }
}
