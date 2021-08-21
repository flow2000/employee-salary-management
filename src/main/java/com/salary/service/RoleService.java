package com.salary.service;

import com.salary.util.AjaxResult;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Map;
import java.util.Set;

@Service
public interface RoleService {

    AjaxResult getAllRole();

    AjaxResult getPageRole(Integer page, Integer limit);

    AjaxResult getOneRole(String type,String content);

    AjaxResult searchRole(int page, int limit, String searchKey, String searchValue);

    AjaxResult insertRole(Map<String, Object> map);

    AjaxResult updateRole(Map<String, Object> map);

    AjaxResult changeRoleStatus(Map<String, Object> map);

    AjaxResult deleteRole(Map<String, Object> map);

    Set<String> selectRoleKeys(BigInteger user_id);
}
