package com.salary.controller;

import com.salary.service.SalaryService;
import com.salary.util.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value="SalaryController",tags="薪资接口")
@RequestMapping("/api/salary")
public class SalaryController {

    @Autowired
    private SalaryService salaryService;

    /**
     * 获取所有薪资信息
     * @return 所有薪资信息
     */
    @ApiOperation(value = "获取所有薪资信息")
    @ApiImplicitParam()
    @GetMapping("/getAllSalary")
    public AjaxResult getAllSalary(){
        return AjaxResult.returnMessage(salaryService.getAllSalary());
    }

    /**
     * 分页获取所有薪资信息
     * @return 分页薪资信息
     */
    @ApiOperation(value = "分页获取所有薪资信息")
    @ApiImplicitParam()
    @GetMapping("/getPageSalary")
    public AjaxResult getPageSalary(
            @RequestParam("page") Integer page,
            @RequestParam("limit") Integer limit){
        return AjaxResult.returnMessage(salaryService.getPageSalary(page,limit));
    }

    /**
     * 获取部门所有薪资信息
     * @return 部门所有薪资信息
     */
    @ApiOperation(value = "获取部门所有薪资信息")
    @ApiImplicitParam()
    @GetMapping("/getAllDeptSalary")
    public AjaxResult getAllDeptSalary(int dept_id){
        return AjaxResult.returnMessage(salaryService.getAllDeptSalary(dept_id));
    }

    /**
     * 分页获取部门所有薪资信息
     * @return 分页部门所有薪资信息
     */
    @ApiOperation(value = "分页部门所有薪资信息")
    @ApiImplicitParam()
    @GetMapping("/getPageDeptSalary")
    public AjaxResult getPageDeptSalary(
            @RequestParam("page") Integer page,
            @RequestParam("limit") Integer limit,
            @RequestParam("dept_id") int dept_id){
        return AjaxResult.returnMessage(salaryService.getPageDeptSalary(page,limit,dept_id));
    }
}
