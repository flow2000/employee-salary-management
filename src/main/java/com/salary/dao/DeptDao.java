package com.salary.dao;

import com.salary.entity.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface DeptDao {

    List<Map> getAllDept();

    List<Map> getPageDept(Integer page, Integer limit);

    Dept getOneDept(String dept_name);

    List<Map> searchDept(List<Map> queryList);

    int insertDept(Map<String, Object> map);

    int updateDept(Map<String, Object> map);

    int changeDeptStatus(Map<String, Object> map);

    int deleteDept(String[] array);

    List<Dept> getUserDept(String[] array);

    int updateUserDept(Map<String, Object> map);
}
