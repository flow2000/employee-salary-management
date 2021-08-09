package com.salary.controller;

import com.salary.service.DeptService;
import com.salary.util.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/dept")
@Api(value="DeptController",tags="部门接口")
public class DeptController {

    @Autowired
    private DeptService deptService;

    /**
     * 获取所有部门信息
     * @return 所有部门信息
     */
    @ApiOperation(value = "获取所有的部门信息")
    @ApiImplicitParam()
    @GetMapping("/getAllDept")
    public AjaxResult getAllDept(){
        return deptService.getAllDept();
    }

    /**
     * 分页获取部门信息
     * @param page 页码
     * @param limit 每页数量量
     * @return 部门信息
     */
    @ApiOperation(value = "分页获取部门信息")
    @ApiImplicitParam()
    @GetMapping("/getPageDept")
    public AjaxResult getPageDept(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
        return deptService.getPageDept(page,limit);
    }

    /**
     * 获取单个部门信息
     * @param dept_name 部门名称
     * @return 部门信息
     */
    @ApiOperation(value = "获取单个部门信息")
    @ApiImplicitParam()
    @GetMapping("/getOneDept")
    public AjaxResult getOneDept(@RequestParam("dept_name") String dept_name){
        return deptService.getOneDept(dept_name);
    }



    /**
     * 添加部门信息
     * @param map 部门信息
     * @return 成功或者失败消息
     */
    @ApiOperation(value = "获取单个部门信息")
    @ApiImplicitParam()
    @GetMapping("/insertDept")
    public AjaxResult insertDept(@RequestBody Map<String, Object> map){
        return deptService.insertDept(map);
    }


    /**
     * 修改部门信息
     * @param map 部门信息
     * @return 成功或者失败消息
     */
    @ApiOperation(value = "修改部门信息")
    @ApiImplicitParam()
    @GetMapping("/updateDept")
    public AjaxResult updateDept(@RequestBody Map<String, Object> map){
        return deptService.updateDept(map);
    }

    /**
     * 删除部门信息
     * @param map 部门信息
     * @return 成功或者失败消息
     */
    @ApiOperation(value = "删除部门信息")
    @ApiImplicitParam()
    @GetMapping("/deleteDept")
    public AjaxResult deleteDept(@RequestBody Map<String, Object> map){
        return deptService.deleteDept(map);
    }
}
