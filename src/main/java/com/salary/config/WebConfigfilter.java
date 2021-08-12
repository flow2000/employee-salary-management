package com.salary.config;

import com.salary.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * 拦截器配置类
 */
@Configuration
public class WebConfigfilter implements WebMvcConfigurer {

    @Autowired
    private TokenInterceptor tokenInterceptor;

    /**
     * 解决跨域请求
     * @param registry 注册器
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowedOrigins("*")
                .allowCredentials(true);
    }

    /**
     * 异步请求配置
     * @param configurer 配置器
     */
    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.setTaskExecutor(new ConcurrentTaskExecutor(Executors.newFixedThreadPool(3)));
        configurer.setDefaultTimeout(30000);
    }

    /**
     * 配置拦截器、拦截路径
     * 每次请求到拦截的路径，就会去执行拦截器中的方法
     * @param registry 注册器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePath = new ArrayList<>();
        //排除拦截
        excludePath.add("/login");    //登录
        excludePath.add("/index");    //首页
        excludePath.add("/swagger-resources/**");    //swagger资源
        excludePath.add("/swagger-ui.html/**");    //swagger
        excludePath.add("/webjars/**");    //swagger的js
        excludePath.add("/v2/api-docs/**");    //swagger接口
        excludePath.add("/api/getVerify");    //开放验证码接口
        excludePath.add("/api/login");    //开放登录接口
        excludePath.add("/api/*/get**");    //开放get请求接口
        excludePath.add("/system/**");    //开放页面接口
        excludePath.add("/css/**");   //css
        excludePath.add("/img/**");   //img
        excludePath.add("/js/**");    //js
        excludePath.add("/libs/**");  //libs
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(excludePath);
        WebMvcConfigurer.super.addInterceptors(registry);

    }

}
