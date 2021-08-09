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
 * 创建时间 2021/7/29
 * 版本 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Dept {
    private BigInteger dept_id; //部门id
    private String dept_name;   //部门名称
    private String leader;      //负责人
    private String phone;       //联系电话
    private String email;       //部门邮箱
    private String status;      //部门状态
    private String del_flag;    //删除标志
    private String creater;     //创建者
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date create_time;   //创建时间
    private String updater;     //修改者者
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date update_time;   //修改时间
    private String remark;      //备注
}
