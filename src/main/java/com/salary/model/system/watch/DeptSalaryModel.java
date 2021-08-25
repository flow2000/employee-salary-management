package com.salary.model.system.watch;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@RequestMapping("/system/watch/dept")
@ApiIgnore
public class DeptSalaryModel {

    @GetMapping("/deptSalary")
    public String dept(){
        return "system/watch/dept/deptSalary";
    }

    @GetMapping("/more")
    public String more(){
        return "system/watch/dept/more";
    }
}
