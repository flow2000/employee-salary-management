package com.salary.controller;

import com.salary.service.TestService;
import com.salary.util.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value="TestController",tags="测试功能接口")
@RequestMapping("/api/random")
public class TestController {

    @Autowired
    private TestService testService;


    /**
     * 随机更新用户薪资
     * @return 影响的行数
     */
    @ApiOperation(value = "随机更新用户薪资")
    @ApiImplicitParam()
    @GetMapping("/UpdateUserSalary")
    public AjaxResult UpdateUserSalary(){
        return AjaxResult.returnMessage(testService.UpdateUserSalary());
    }

}
