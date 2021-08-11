package com.salary.dao;

import com.salary.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface MenuDao {

    List<Menu> getRoleMenu(int role_id);
}
