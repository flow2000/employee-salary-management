package com.salary.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.salary.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //跨域请求会首先发一个option请求，直接返回正常状态并通过拦截器
        if(request.getMethod().equals("OPTIONS")){
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        response.setCharacterEncoding("utf-8");
        String token = request.getHeader("token");
        String login_name = request.getHeader("login_name");
        if (token!=null&&login_name!=null){
            boolean result= tokenService.verifyToken(login_name,token);
            if (result){
                System.out.println();
                return true;
            }
        }
        //权限认证，后面再写

        //回到登录界面
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<script>");
        out.println("window.open ('"+request.getContextPath()+"/login','_top')");
        out.println("</script>");
        out.println("</html>");

        return false;
    }
}
