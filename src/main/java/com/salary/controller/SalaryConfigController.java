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
