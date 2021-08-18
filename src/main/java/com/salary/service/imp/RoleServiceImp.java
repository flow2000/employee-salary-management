package com.salary.service.imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.salary.dao.RoleDao;
import com.salary.service.RoleService;
import com.salary.util.AjaxResult;
import com.salary.util.StringUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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
     * 搜索角色
     * @param page 页码
     * @param limit 数量
     * @param searchKey 字段
     * @param searchValue 字段值
     * @return 查询结果
     */
    @Override
    public AjaxResult searchRole(int page, int limit, String searchKey, String searchValue) {
        PageHelper.startPage(page, limit); //开始分页
        List<Map> queryList = StringUtils.strToMapList(searchKey,searchValue);
        List<Map> roleList = roleDao.searchRole(queryList); //拼接sql语句
        return AjaxResult.returnMessage(new PageInfo<>(roleList)); //将分页结果放入pageUserList
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
     * 修改角色状态
     * @param map 角色信息
     * @return 成功或者失败消息
     */
    @Override
    public AjaxResult changeRoleStatus(Map<String, Object> map) {
        if(hasUserRole(map)){
            return AjaxResult.error("尚有用户使用该角色,不可修改");
        }
        return AjaxResult.toAjax(roleDao.changeRoleStatus(map));
    }

    /**
     * 删除角色信息
     * @param map 角色信息
     *
     * @return 影响的行数
     */
    @Override
    public AjaxResult deleteRole(Map<String, Object> map) {
        if(hasUserRole(map)){
            return AjaxResult.error("尚有用户使用该角色,不可删除");
        }
        String role_id = (String) map.get("role_id");
        if(role_id!=null){
            String[] array = role_id.split(";");
            return AjaxResult.toAjax(roleDao.deleteRole(array));
        }
        return AjaxResult.error("删除失败");
    }

    /**
     * 判断是否还有用户使用该角色
     * @param map
     * @return
     */
    private boolean hasUserRole(Map<String, Object> map){
        String status = (String) map.get("status");
        if(!"0".equals(status)){
            String role_id = (String) map.get("role_id");
            if(role_id!=null){
                String[] array = role_id.split(";");
                List<Map> list = roleDao.getUserRole(array);
                if(list!=null&&list.size()>0){
                    return true;
                }
            }
        }
        return false;
    }

}
