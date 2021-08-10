package com.salary.service;

import com.salary.util.AjaxResult;
import org.springframework.stereotype.Service;

@Service
public interface MenuService {

    AjaxResult getRoleMenu(int role_id);
}
