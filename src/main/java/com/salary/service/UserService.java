package com.salary.service;

import com.salary.util.AjaxResult;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Service
public interface UserService {

    void getVerify(HttpServletRequest request, HttpServletResponse response) throws IOException;

    AjaxResult login(HttpServletRequest request, Map<String, Object> map);

    AjaxResult init(HttpServletRequest request);

    AjaxResult getAllUser();

    AjaxResult getPageUser(Integer page, Integer limit);

    AjaxResult getOneUser(String login_name);

    AjaxResult insertUser(Map<String, Object> map);

    AjaxResult updateUser(Map<String, Object> map);

    AjaxResult deleteUser(Map<String, Object> map);

}
