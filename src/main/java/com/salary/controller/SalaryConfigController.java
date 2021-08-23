package com.salary.controller;

import com.salary.entity.SalaryConfig;
import com.salary.service.SalaryConfigService;
import com.salary.util.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value="SalaryConfigController",tags="薪资参数接口")
@RequestMapping("/api/salaryConfig")
public class SalaryConfigController {

    @Autowired
    private SalaryConfigService salaryConfigService;

    /**
     * 获取所有薪资配置信息
     * @return 所有薪资配置信息
     */
    @ApiOperation(value = "获取所有薪资配置信息")
    @ApiImplicitParam()
    @GetMapping("/getAllSalaryConfig")
    public AjaxResult getAllSalaryConfig(){
        return AjaxResult.returnMessage(salaryConfigService.getAllSalaryConfig());
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
        return AjaxResult.returnMessage(salaryConfigService.getPageSalaryConfig(page,limit));
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
        return salaryConfigService.getOneSalaryConfig(login_name);
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
        return  salaryConfigService.searchSalaryConfig(page,limit,login_name,create_time);
    }

    /**
     * 添加薪资配置信息
     * @return 影响的行数
     */
    @ApiOperation(value = "添加薪资配置信息")
    @ApiImplicitParam()
    @PostMapping("/insertSalaryConfig")
    public AjaxResult insertSalaryConfig(@RequestBody SalaryConfig salaryConfig){
        return AjaxResult.toAjax(salaryConfigService.insertSalaryConfig(salaryConfig));
    }

    /**
     * 修改薪资配置信息
     * @return 影响的行数
     */
    @ApiOperation(value = "修改薪资配置信息")
    @ApiImplicitParam()
    @PostMapping("/updateSalaryConfig")
    public AjaxResult updateSalaryConfig(@RequestBody SalaryConfig salaryConfig){
        return AjaxResult.toAjax(salaryConfigService.updateSalaryConfig(salaryConfig));
    }
}
