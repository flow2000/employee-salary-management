package com.salary.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigInteger;
import java.util.Date;

/**
 * 用户实体类
 * 创建人 庞海
 * 创建时间 2021/7/26
 * 版本 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class User {
    private BigInteger user_id; //用户id
    private BigInteger dept_id; //部门id
    private String login_name;  //登录名
    private String password;    //登录密码
    private String salt;        //盐加密
    private String user_name;   //昵称
    private int role_id;        //角色权限id
    private String role_key;   //角色权限字符串
    private String role_name;   //角色名称
    private String real_name;   //真实姓名
    private String sex;         //性别
    private String age;         //年龄
    private String email;       //邮箱
    private String phone_number;//手机号
    private String status;      //用户状态
    private String del_flag;    //删除标志
    private String creater;     //创建者
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date create_time;   //创建时间
    private String updater;     //修改者者
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date update_time;   //修改时间
    private String remark;      //备注
}
