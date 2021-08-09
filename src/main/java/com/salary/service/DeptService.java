package com.salary.service;

import com.salary.util.AjaxResult;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface DeptService {

    AjaxResult getAllDept();

    AjaxResult getPageDept(Integer page, Integer limit);

    AjaxResult getOneDept(String dept_name);

    AjaxResult insertDept(Map<String, Object> map);

    AjaxResult updateDept(Map<String, Object> map);

    AjaxResult deleteDept(Map<String, Object> map);
}
