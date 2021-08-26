package com.salary.service.imp;

import com.salary.dao.SalaryDao;
import com.salary.dao.TestDao;
import com.salary.entity.Salary;
import com.salary.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
public class TestServiceImp implements TestService {

    @Autowired
    private SalaryDao salaryDao;

    @Autowired
    private TestDao testDao;

    /**
     * 测试功能
     * 随机更新用户薪资情况
     * @return 影响的行数
     */
    @Override
    public int UpdateUserSalary() {
        List<Salary> list = salaryDao.getAllSalary();
        for (int i = 0;i<list.size();i++){
            Salary salary = list.get(i);
            salary.setBase_salary(new BigDecimal(createRandomNumber(2000,15000).toString()));
            salary.setWeek_time(addDeviation(4,0));
            salary.setFestival_time(addDeviation(4,1));
            salary.setPercentage(new BigDecimal(createRandomNumber(300,1500).toString()));
            salary.setBonus(new BigDecimal(createRandomNumber(600,3000).toString()));
            salary.setAbsence_count(addDeviation(4,1));
            salary.setLate_count(addDeviation(4,1));
            salary.setLeave_count(addDeviation(4,1));
            salary.calculateSalary();
            list.set(i,salary);
        }
        return testDao.UpdateUserSalary(list);
    }

    /**
     * 随机生成min~max范围的随机数
     * @param min 最小数
     * @param max 最大数
     * @return double型随机数
     */
    public static Object createRandomNumber(double min,double max){
        DecimalFormat df = new DecimalFormat("#");
        double m = Math.random() * max;
        while(m<min){
            m = Math.random() * max ;
        }
        return m<0?0:df.format(m);
    }

    /**
     * 生成含有偏差值的随机数
     * @param max 最大值
     * @param deviate 偏差
     * @return 随机数
     */
    public static int addDeviation(int max,int deviate){
        Random random = new Random();
        int num = random.nextInt(max)-deviate;
        return Math.max(num, 0);
    }
}
