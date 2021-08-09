package com.salary.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface RoleDao {
    
    List<Map> getAllRole();

    List<Map> getPageRole(Integer page, Integer limit);

    List<Map> getOneRole(String role_key);

    int insertRole(Map<String, Object> map);

    int updateRole(Map<String, Object> map);

    int deleteRole(Map<String, Object> map);
}
