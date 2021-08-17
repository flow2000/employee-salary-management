package com.salary.dao;

import com.salary.entity.Menu;
import com.salary.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface UserDao {

    List<Map> getAllUser();

    List<Map> getPageUser(Integer page, Integer limit);

    User getOneUser(String login_name);

    int updatePerson(Map<String, Object> map);

    int updatePersonPassword(Map<String, Object> map);

    int insertUser(Map<String, Object> map);

    List<Map> searchUser(List<Map> queryList);

    int changeUserStatus(Map<String, Object> map);

    int deleteUser(String[] array);

    List<Menu> getUserPerms(BigInteger user_id);

    int updateUser(Map<String, Object> map);

    int updateUserRole(Map<String, Object> map);

    int insertUserRole(Map<String, Object> map);

    int resetUserPassword(Map<String, Object> map);
}
