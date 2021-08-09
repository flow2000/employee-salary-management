package com.salary.util;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 验证码工具类
 */
public class SaveCaptcha {

    public static int MaxImgCodeActiveTime = 600; 	//验证码有效期：600秒
    public static int ImgWidth = 100; 				//图片宽度
    public static int ImgHeight = 40; 				//图片高度
    public static int CodeCount = 4; 				//验证码字符数
    public static int LineWidth = 2; 				//干扰线宽度

    /**
     * 生成一张验证码图片
     *
     * @return 验证码图片
     */
    public static ShearCaptcha getCaptcha(){
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(
                ImgWidth,   //图片宽度
                ImgHeight,  //图片高度
                CodeCount,  //验证码字符数
                LineWidth); //干扰线宽度
        return captcha;
    }

    /**
     * 将正确的验证码保存到session中并设置生存时间
     *
     * @param request 请求
     * @param captcha 验证码
     */
    public static void saveSession(HttpServletRequest request,ShearCaptcha captcha){
        HttpSession session = request.getSession(true);//获得session对象
        String key = session.getId();       //获得sessionId
        session.setAttribute(key,captcha.getCode());      //验证码保存到session中
        session.setMaxInactiveInterval(MaxImgCodeActiveTime); //设置生存时间
    }
}
