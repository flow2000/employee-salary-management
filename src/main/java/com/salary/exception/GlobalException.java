package com.salary.exception;

import com.alibaba.fastjson.JSONObject;
import com.salary.util.AjaxResult;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class GlobalException {

    /**
     * 权限异常
     *
     * @param request 请求
     * @param response 响应
     * @return json字符
     */
    @ExceptionHandler({ UnauthorizedException.class, AuthorizationException.class })
    public String authorizationException(HttpServletRequest request, HttpServletResponse response) {
        writeJson(AjaxResult.error("无权限"), response);
        return null;
    }

    private void writeJson(Map map, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            out = response.getWriter();
            String json = JSONObject.toJSONString(map);
            out.write(json);
        } catch (IOException e) {
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
