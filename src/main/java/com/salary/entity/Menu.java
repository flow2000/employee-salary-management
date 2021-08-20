package com.salary.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private String title;    //菜单名称
    private String menu_type;    //菜单类型
    private BigInteger parent_id;//父菜单id
    private String href;          //请求地址
    private String perms;        //权限标识
    private String icon;         //菜单图标
    private String visible;      //菜单状态
    private String target;       //打开方式
    private List<Menu> child=new ArrayList<>();        //子菜单
    private String image;        //图片
    private String creater;      //创建者
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date create_time;    //创建时间
    private String updater;      //修改者者
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date update_time;    //修改时间
    private String remark;       //备注
}
