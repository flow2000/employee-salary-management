package com.salary.service.imp;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.salary.dao.DeptDao;
import com.salary.entity.Dept;
import com.salary.service.DeptService;
import com.salary.util.AjaxResult;
import com.salary.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
     * 搜索部门
     * @param page 页码
     * @param limit 数量
     * @param searchKey 字段
     * @param searchValue 字段值
     * @return 查询结果
     */
    @Override
    public AjaxResult searchDept(int page, int limit, String searchKey, String searchValue) {
        PageHelper.startPage(page, limit); //开始分页
        List<Map> queryList = StringUtils.strToMapList(searchKey,searchValue);
        List<Map> roleList = deptDao.searchDept(queryList); //拼接sql语句
        return AjaxResult.returnMessage(new PageInfo<>(roleList));
    }

    /**
     * 添加部门信息
     * @param map 部门信息
     *
     * @return 影响的行数
     */
    @Override
    public AjaxResult insertDept(Map<String, Object> map) {
        String dept_name = (String) map.get("dept_name");
        Dept dept = deptDao.getOneDept(dept_name);
        if(dept!=null){
            return AjaxResult.error("部门已存在");
        }
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
        String dept_name = (String) map.get("dept_name");
        Integer dept_id = Integer.valueOf( map.get("dept_id").toString());
        Dept dept = deptDao.getOneDept(dept_name);
        if(dept!=null&&dept.getDept_id().intValue()!=dept_id){
            return AjaxResult.error("部门已存在");
        }
        return AjaxResult.toAjax(deptDao.updateDept(map));
    }

    /**
     * 修改部门状态
     * @param map 部门信息
     *
     * @return 成功或者失败消息
     */
    @Override
    public AjaxResult changeDeptStatus(Map<String, Object> map) {
        String dept_id = (String) map.get("dept_id");
        if(dept_id==null){
            return AjaxResult.error("修改失败");
        }
        String[] array = dept_id.split(";");
        if(hasUserDept(array)){
            return AjaxResult.error("尚有用户在部门旗下,不可删除");
        }
        return AjaxResult.toAjax(deptDao.changeDeptStatus(map));
    }

    private boolean hasUserDept(String[] array) {
        List<Dept> list = deptDao.getUserDept(array);
        if(list.size()>0){
            return true;
        }
        return false;
    }

    /**
     * 删除部门信息
     * @param map 部门信息
     *
     * @return 影响的行数
     */
    @Override
    public AjaxResult deleteDept(Map<String, Object> map) {
        String dept_id = (String) map.get("dept_id");
        if(dept_id!=null){
            String[] array = dept_id.split(";");
            if(hasUserDept(array)){
                return AjaxResult.error("尚有用户在部门旗下,不可删除");
            }
            return AjaxResult.toAjax(deptDao.deleteDept(array));
        }
        return AjaxResult.error("删除失败");
    }

    /**
     * 合并部门信息
     * @param map 部门信息
     *
     * @return 影响的行数
     */
    @Override
    public AjaxResult mergeDept(Map<String, Object> map) {
        String dept_name = (String) map.get("dept_name");
        Dept dept = deptDao.getOneDept(dept_name);
        if(dept!=null){
            return AjaxResult.error("部门已存在");
        }
        String p_dept_id = (String) map.get("dept_id"); //待合并部门的id

        deptDao.insertDept(map);//添加部门
        Integer n_dept_id = Integer.valueOf(map.get("dept_id").toString()) ;//新添加部门的id
        System.out.println(n_dept_id);
        if(p_dept_id!=null){
            String[] array = p_dept_id.split(";");
            Map<String,Object> param = new HashMap<>();
            param.put("array",array);
            param.put("dept_id",n_dept_id);
            //修改用户部门相关
            deptDao.updateUserDept(param);
            //删除部门
            return AjaxResult.toAjax(deptDao.deleteDept(array));
        }
        return AjaxResult.error("合并失败");
    }

}
