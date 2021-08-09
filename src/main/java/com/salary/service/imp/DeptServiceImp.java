package com.salary.service.imp;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.salary.dao.DeptDao;
import com.salary.service.DeptService;
import com.salary.util.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class DeptServiceImp implements DeptService {

    @Autowired
    private DeptDao deptDao;

    /**
     * 获取所有部门
     *
     * @return 部门信息
     */
    @Override
    public AjaxResult getAllDept() {
        return AjaxResult.returnMessage(deptDao.getAllDept());
    }

    /**
     * 分页获取部门
     * @param page 页码
     * @param limit 每页数量量
     *
     * @return 部门信息
     */
    @Override
    public AjaxResult getPageDept(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);//开始分页
        List<Map> DeptList = deptDao.getPageDept(page,limit); //拼接sql语句
        return AjaxResult.returnMessage(new PageInfo<>(DeptList)); //将分页结果放入pageDeptList
    }

    /**
     * 获取单个部门信息
     * @param dept_name 部门名称
     *
     * @return 部门信息
     */
    @Override
    public AjaxResult getOneDept(String dept_name) {
        return AjaxResult.returnMessage(deptDao.getOneDept(dept_name));
    }



    /**
     * 添加部门信息
     * @param map 部门信息
     *
     * @return 影响的行数
     */
    @Override
    public AjaxResult insertDept(Map<String, Object> map) {
        return AjaxResult.toAjax(deptDao.insertDept(map));
    }

    /**
     * 修改部门信息
     * @param map 部门信息
     *
     * @return 影响的行数
     */
    @Override
    public AjaxResult updateDept(Map<String, Object> map) {
        return AjaxResult.toAjax(deptDao.updateDept(map));
    }

    /**
     * 删除部门信息
     * @param map 部门信息
     *
     * @return 影响的行数
     */
    @Override
    public AjaxResult deleteDept(Map<String, Object> map) {
        return AjaxResult.toAjax(deptDao.deleteDept(map));
    }

}
