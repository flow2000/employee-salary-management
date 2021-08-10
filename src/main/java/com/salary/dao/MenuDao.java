package com.salary.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface MenuDao {

    List<Map> getRoleMenu(int role_id);
}
