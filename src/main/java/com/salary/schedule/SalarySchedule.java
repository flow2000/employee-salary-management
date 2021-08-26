package com.salary.schedule;

import com.salary.dao.SalaryDao;
import com.salary.entity.Salary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SalarySchedule {

    private static final Logger log = LoggerFactory.getLogger(SalarySchedule.class);

    @Autowired
    private SalaryDao salaryDao;

    /**
     * 每个月定时为每个用户创建薪资数据
     *
     */
    @Scheduled(cron = "0 0 0 1 * ?")//每月1号凌晨0点执行一次
    public void insertSalary() {
        log.info("定时处理创建薪资任务 处理开始");
        salaryDao.insertSQLSalary();
        log.info("处理创建薪资任务 处理结束");
    }

//    /**
//     * 测试
//     */
//    @Scheduled(fixedRate = 1000*60*60)
//    public void test() {
//        insertSalary();
//    }

    /**
     * 每天定时为每个用户计算薪资数据
     *
     */
    @Scheduled(cron = "0 0 1 * * ?")//每天0点执行一次
    public void calculateSalary(){
        log.info("定时计算薪资数据任务 处理开始");
        List<Salary> list = salaryDao.getAllSalary();
        for (int i=0;i<list.size();i++){
            Salary salary = list.get(i).calculateSalary();
            if(salary.getRequireCalculate()){
                list.set(i,salary);
            }else{
                list.remove(i);
                i--;
            }
        }
        if(list.size()!=0){
            salaryDao.updateSQLSalary(list);
        }
        log.info("处理计算薪资数据任务 处理结束");
    }

//    /**
//     * 测试
//     */
//    @Scheduled(fixedRate = 1000*60*60)
//    public void calculateSalaryTest() {
//        calculateSalary();
//    }

}
