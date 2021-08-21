package com.salary.service;

import com.salary.entity.Menu;
import com.salary.entity.Ztree;
import com.salary.util.AjaxResult;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public interface MenuService {

    List<Menu> getAllMenu();

    AjaxResult getRoleMenu(int role_id);

    Map<String, Object> getMenu(int role_id);

    List<Ztree> getRoleMenuTree(int role_id,int user_id);

    Set<String> selectPermsByUserId(BigInteger user_id);
}
