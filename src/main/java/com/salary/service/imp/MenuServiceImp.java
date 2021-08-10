package com.salary.service.imp;

import com.salary.dao.MenuDao;
import com.salary.service.MenuService;
import com.salary.util.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MenuServiceImp implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Override
    public AjaxResult getRoleMenu(int role_id) {
        return AjaxResult.returnMessage(menuDao.getRoleMenu(role_id));
    }
}
