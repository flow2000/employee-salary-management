package com.salary.service.imp;

import com.salary.dao.MenuDao;
import com.salary.entity.Menu;
import com.salary.service.MenuService;
import com.salary.util.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class MenuServiceImp implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Override
    public AjaxResult getRoleMenu(int role_id) {
        return AjaxResult.returnMessage(menuDao.getRoleMenu(role_id));
    }

    @Override
    public Map<String, Object> getMenu(int role_id) {
        Map<String,Object> resultMap = new HashMap<>();
        List<Menu> menuList = menuDao.getRoleMenu(role_id);
        Menu menu = null;

        List<Object> list = new ArrayList<>();
        menu = new Menu();
        menu.setTitle(" ");
        menu.setIcon("fa fa-address-book");
        menu.setHref("#");
        menu.setTarget("_self");
        menu.setChild(menuList);
        list.add(menu);
        resultMap.put("menuInfo", list);

        menu = new Menu();
        menu.setTitle("首页");
        menu.setHref("system/welcome/welcome");
        menu.setTarget("_self");
        resultMap.put("homeInfo", menu);

        menu = new Menu();
        menu.setTitle("工资管理系统");
        menu.setImage("img/logo.png");
        menu.setHref("index");
        resultMap.put("logoInfo", menu);

        return resultMap;
    }


}
