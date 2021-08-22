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
 * 薪资实体类
 * 创建人 庞海
 * 创建时间 2021/7/26
 * 版本 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Salary implements Serializable {
    private BigInteger salary_id;       //工资id
    private BigInteger user_id;         //用户id
    private BigDecimal base_salary;     //基本薪资
    private BigDecimal reward_salary;   //奖励绩效
    private BigDecimal overtime;        //加班薪资
    private BigDecimal percentage;      //提成
    private BigDecimal bonus;           //奖金
    private BigDecimal punish_salary;   //惩罚扣薪
    private BigDecimal leave;           //请假
    private BigDecimal late;            //迟到
    private BigDecimal absence;         //旷工
    private BigDecimal total_salary;    //总计
    private String checked;             //是否已审核
    private String check_result;        //审核结果
    private String fail_cause;          //审核失败原因
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date check_time;            //审核时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date create_time;           //修改时间
    private String remark;              //备注
}
