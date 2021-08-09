package com.salary.service.imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.salary.dao.RoleDao;
import com.salary.service.RoleService;
import com.salary.util.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class RoleServiceImp implements RoleService {

    @Autowired
    private RoleDao roleDao;

    /**
     * 获取所有角色
     *
     * @return 角色信息
     */
    @Override
    public AjaxResult getAllRole() {
        return AjaxResult.returnMessage(roleDao.getAllRole());
    }

    /**
     * 分页获取角色
     * @param page 页码
     * @param limit 每页数量量
     *
     * @return 角色信息
     */
    @Override
    public AjaxResult getPageRole(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);//开始分页
        List<Map> RoleList = roleDao.getPageRole(page,limit); //拼接sql语句
        return AjaxResult.returnMessage(new PageInfo<>(RoleList)); //将分页结果放入pageRoleList
    }

    /**
     * 获取单个角色信息
     * @param role_key 角色权限字符串
     *
     * @return 角色信息
     */
    @Override
    public AjaxResult getOneRole(String role_key) {
        return AjaxResult.returnMessage(roleDao.getOneRole(role_key));
    }



    /**
     * 添加角色信息
     * @param map 角色信息
     *
     * @return 影响的行数
     */
    @Override
    public AjaxResult insertRole(Map<String, Object> map) {
        return AjaxResult.toAjax(roleDao.insertRole(map));
    }

    /**
     * 修改角色信息
     * @param map 角色信息
     *
     * @return 影响的行数
     */
    @Override
    public AjaxResult updateRole(Map<String, Object> map) {
        return AjaxResult.toAjax(roleDao.updateRole(map));
    }

    /**
     * 删除角色信息
     * @param map 角色信息
     *
     * @return 影响的行数
     */
    @Override
    public AjaxResult deleteRole(Map<String, Object> map) {
        return AjaxResult.toAjax(roleDao.deleteRole(map));
    }

}
