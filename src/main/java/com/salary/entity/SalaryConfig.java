package com.salary.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * 薪资配置实体类
 * 创建人 庞海
 * 创建时间 2021/7/26
 * 版本 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SalaryConfig implements Serializable {

    private int config_id;          //薪资配置id
    private BigInteger user_id;     //用户id
    private BigDecimal base_salary; //基本薪资
    private int late_count;         //迟到
    private int absence_count;      //旷工
    private int overtime_count;     //加班
    private String creater;         //创建者
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date create_time;       //创建时间
    private String updater;         //修改者
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date update_time;       //修改时间
    private String remark;          //备注
}
