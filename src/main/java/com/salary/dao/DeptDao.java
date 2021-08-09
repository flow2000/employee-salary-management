package com.salary.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface DeptDao {

    List<Map> getAllDept();

    List<Map> getPageDept(Integer page, Integer limit);

    List<Map> getOneDept(String dept_name);

    int insertDept(Map<String, Object> map);

    int updateDept(Map<String, Object> map);

    int deleteDept(Map<String, Object> map);
    
}
