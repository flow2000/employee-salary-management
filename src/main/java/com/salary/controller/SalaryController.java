package com.salary.controller;

import com.salary.entity.Salary;
import com.salary.service.SalaryService;
import com.salary.util.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
     * 搜索薪资信息
     * @param page 页码
     * @param limit 数量
     * @param login_name 帐号
     * @param create_time 创建时间
     * @return 查询结果
     */
    @ApiOperation(value = "搜索薪资信息")
    @ApiImplicitParam
    @GetMapping("/searchSalary")
    public AjaxResult searchSalary(@RequestParam int page,@RequestParam int limit,
                                         @RequestParam String login_name, @RequestParam String create_time){
        return  AjaxResult.returnMessage(salaryService.searchSalary(page,limit,login_name,create_time));
    }

    /**
     * 奖惩录入
     * @return 影响的行数
     */
    @ApiOperation(value = "奖惩录入")
    @ApiImplicitParam()
    @PostMapping("/updateSalaryInput")
    public AjaxResult updateSalaryInput(@RequestBody Salary salary){
        return AjaxResult.toAjax(salaryService.updateSalaryInput(salary));
    }

    /**
     * 获取所有薪资配置信息
     * @return 所有薪资配置信息
     */
    @ApiOperation(value = "获取所有薪资配置信息")
    @ApiImplicitParam()
    @GetMapping("/getAllSalaryConfig")
    public AjaxResult getAllSalaryConfig(){
        return AjaxResult.returnMessage(salaryService.getAllSalaryConfig());
    }

    /**
     * 分页获取所有薪资配置信息
     * @return 分页薪资配置信息
     */
    @ApiOperation(value = "分页获取所有薪资配置信息")
    @ApiImplicitParam()
    @GetMapping("/getPageSalaryConfig")
    public AjaxResult getPageSalaryConfig(
            @RequestParam("page") Integer page,
            @RequestParam("limit") Integer limit){
        return AjaxResult.returnMessage(salaryService.getPageSalaryConfig(page,limit));
    }

    /**
     * 获取单个薪资配置信息
     * @param login_name 登录名
     * @return 薪资配置信息
     */
    @ApiOperation(value = "获取单个薪资配置信息")
    @ApiImplicitParam()
    @GetMapping("/getOneSalaryConfig")
    public AjaxResult getOneSalaryConfig(@RequestParam("login_name") String login_name){
        return AjaxResult.returnMessage(salaryService.getOneSalaryConfig(login_name));
    }

    /**
     * 搜索薪资配置信息
     * @param page 页码
     * @param limit 数量
     * @param login_name 帐号
     * @param create_time 创建时间
     * @return 查询结果
     */
    @ApiOperation(value = "搜索薪资配置信息")
    @ApiImplicitParam
    @GetMapping("/searchSalaryConfig")
    public AjaxResult searchSalaryConfig(@RequestParam int page,@RequestParam int limit,
                                         @RequestParam String login_name, @RequestParam String create_time){
        return  AjaxResult.returnMessage(
                salaryService.searchSalaryConfig(page,limit,login_name,create_time));
    }

    /**
     * 添加薪资配置信息
     * @return 影响的行数
     */
    @ApiOperation(value = "添加薪资配置信息")
    @ApiImplicitParam()
    @PostMapping("/insertSalaryConfig")
    public AjaxResult insertSalaryConfig(@RequestBody Salary salary){
        return AjaxResult.toAjax(salaryService.insertSalaryConfig(salary));
    }

    /**
     * 修改薪资配置信息
     * @return 影响的行数
     */
    @ApiOperation(value = "修改薪资配置信息")
    @ApiImplicitParam()
    @PostMapping("/updateSalaryConfig")
    public AjaxResult updateSalaryConfig(@RequestBody Salary salary){
        return AjaxResult.toAjax(salaryService.updateSalaryConfig(salary));
    }

    /**
     * 审核薪资
     * @return 影响的行数
     */
    @ApiOperation(value = "审核薪资")
    @ApiImplicitParam()
    @PostMapping("/updateSalaryChecked")
    public AjaxResult updateSalaryChecked(@RequestBody Salary salary){
        return AjaxResult.toAjax(salaryService.updateSalaryChecked(salary));
    }

    /**
     * 删除薪资
     * @return 影响的行数
     */
    @ApiOperation(value = "删除薪资")
    @ApiImplicitParam()
    @PostMapping("/deleteSalaryById")
    public AjaxResult deleteSalaryById(@RequestBody Map<String, Object> map){
        return AjaxResult.toAjax(salaryService.deleteSalaryById(map));
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

    /**
     * 搜索薪资信息
     * @param page 页码
     * @param limit 数量
     * @param login_name 帐号
     * @param create_time 创建时间
     * @param dept_id  部门id
     * @return 查询结果
     */
    @ApiOperation(value = "搜索薪资信息")
    @ApiImplicitParam
    @GetMapping("/searchDeptSalary")
    public AjaxResult searchDeptSalary(@RequestParam int page,
                                   @RequestParam int limit,
                                   @RequestParam String login_name,
                                   @RequestParam String create_time,
                                   @RequestParam int dept_id) {
        return AjaxResult.returnMessage(salaryService.searchDeptSalary(page, limit, login_name, create_time, dept_id));
    }

}
