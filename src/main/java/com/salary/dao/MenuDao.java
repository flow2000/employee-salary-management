package com.salary.dao;

import com.salary.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;

@Mapper
@Repository
public interface MenuDao {

    List<Menu> getAllMenu();

    List<Menu> getRoleMenu(int role_id);

    List<Menu> getAllUserMenu(int user_id);

    List<String> getRoleMenuPerms(int role_id);

    Set<String> selectPermsByUserId(BigInteger user_id);
}
