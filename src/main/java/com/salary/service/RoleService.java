package com.salary.service;

import com.salary.util.AjaxResult;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface RoleService {

    AjaxResult getAllRole();

    AjaxResult getPageRole(Integer page, Integer limit);

    AjaxResult getOneRole(String login_name);

    AjaxResult insertRole(Map<String, Object> map);

    AjaxResult updateRole(Map<String, Object> map);

    AjaxResult deleteRole(Map<String, Object> map);
}
