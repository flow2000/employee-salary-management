package com.salary.schedule;

import com.salary.dao.SalaryDao;
import com.salary.dao.UserDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class CreateSalary {
    private static final Logger log = LoggerFactory.getLogger(CreateSalary.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private SalaryDao salaryDao;

    /**
     * 每个月定时为每个用户创建薪资数据
     *
     */
    @Scheduled(cron = "0 0 0 1 * ?")//每月1号凌晨0点执行一次
    public void insertSalary() {
        log.info("定时处理创建薪资任务 处理开始");
        List<Map> list = userDao.getAllUser();
        salaryDao.insertSalaryByArray(list);
        log.info("处理创建薪资任务 处理结束");
    }

    /**
     * 测试
     */
    @Scheduled(fixedRate = 1000*60*60)
    public void test() {
        insertSalary();
    }

}
