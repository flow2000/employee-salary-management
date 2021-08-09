package com.salary.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigInteger;
import java.util.Date;

/**
 * 角色实体类
 * 创建人 庞海
 * 创建时间 2021/7/29
 * 版本 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Role {
    private BigInteger role_id; //角色id
    private String role_key;    //角色权限字符串
    private String role_name;   //角色名称
    private String status;      //角色状态
    private String del_flag;    //删除标志
    private String creater;     //创建者
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date create_time;   //创建时间
    private String updater;     //修改者者
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date update_time;   //修改时间
    private String remark;      //备注
}
