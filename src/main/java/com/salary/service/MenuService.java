package com.salary.service;

import com.salary.util.AjaxResult;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface MenuService {

    AjaxResult getRoleMenu(int role_id);

    Map<String, Object> getMenu(int role_id);
}
