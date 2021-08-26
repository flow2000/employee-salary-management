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

    private final static int week_multiple=2;          //周末加班倍数
    private final static int festival_multiple=3;      //节日加班倍数

    private BigInteger salary_id;       //工资id
    private BigInteger user_id;         //用户id
    private String login_name;          //用户帐号
    private String dept_name;           //部门名称
    private BigDecimal base_salary;     //基本薪资
    private int week_time;              //周末加班时长
    private int festival_time;          //节日加班时长
    private BigDecimal overtime;        //加班薪资标准
    private BigDecimal percentage;      //提成
    private BigDecimal bonus;           //奖金
    private int leave_count;            //请假次数
    private int late_count;             //迟到次数
    private int absence_count;          //旷工次数
    private BigDecimal leave;           //请假扣薪标准
    private BigDecimal late;            //迟到扣薪标准
    private BigDecimal absence;         //旷工扣薪标准
    private BigDecimal total_salary;    //总计
    private String checked;             //是否已审核
    private String check_result;        //审核结果
    private String fail_cause;          //审核失败原因
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date check_time;            //审核时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date create_time;           //创建时间
    private String updater;             //修改人
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date update_time;           //修改时间
    private String remark;              //备注
    private Boolean requireCalculate=true;  //是否需要计算(默认需要=true)
    public Salary calculateSalary(){
        BigDecimal total_salary = new BigDecimal("0");
        BigDecimal base_salary = init(getBase_salary());
        String week = init(getWeek_time(),week_multiple);
        String festival = init(getFestival_time(),festival_multiple);
        BigDecimal overtime = init(getOvertime());
        BigDecimal percentage = init(getPercentage());
        BigDecimal bonus = init(getBonus());
        String leave_count = init(getLeave_count());
        String late_count = init(getLate_count());
        String absence_count = init(getAbsence_count());
        BigDecimal leave = init(getLeave());
        BigDecimal late = init(getLate());
        BigDecimal absence = init(getAbsence());

        total_salary = total_salary.add(base_salary);
        total_salary = total_salary.add(overtime.multiply(new BigDecimal(week)));
        total_salary = total_salary.add(overtime.multiply(new BigDecimal(festival)));
        total_salary = total_salary.add(percentage);
        total_salary = total_salary.add(bonus);
        total_salary = total_salary.subtract(leave.multiply(new BigDecimal(leave_count)));
        total_salary = total_salary.subtract(late.multiply(new BigDecimal(late_count)));
        total_salary = total_salary.subtract(absence.multiply(new BigDecimal(absence_count)));
        if(getTotal_salary()!=null){
            requireCalculate = total_salary.compareTo(getTotal_salary()) != 0;
        }
        this.setTotal_salary(total_salary);
        return this;
    }

    public BigDecimal init(BigDecimal bigDecimal){
        return bigDecimal==null?new BigDecimal("0"):bigDecimal;
    }

    public String init(int v,int multiple){
        return v==0?"0":String.valueOf(v*multiple);
    }

    public String init(int v){
        return v==0?"0":String.valueOf(v);
    }
}
