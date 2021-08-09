package com.salary.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigInteger;
import java.util.Date;

/**
 * 菜单实体类
 * 创建人 庞海
 * 创建时间 2021/7/26
 * 版本 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Menu {
    private BigInteger menu_id;  //菜单id
    private String menu_name;    //登录名
    private BigInteger parent_id;//父菜单id
    private String url;          //请求地址
    private String perms;        //权限标识
    private String icon;         //菜单图标
    private String visible;      //菜单状态
    private String creater;      //创建者
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date create_time;    //创建时间
    private String updater;      //修改者者
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date update_time;    //修改时间
    private String remark;       //备注
}
