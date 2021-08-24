package com.salary.service.imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.salary.dao.SalaryDao;
import com.salary.entity.Salary;
import com.salary.service.SalaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class SalaryServiceImp implements SalaryService {

    @Autowired
    private SalaryDao salaryDao;

    /**
     * 获取所有薪资信息
     * @return 所有薪资信息
     */
    @Override
    public List<Salary> getAllSalary() {
        return salaryDao.getAllSalary();
    }

    /**
     * 分页获取所有薪资信息
     * @return 分页薪资信息
     */
    @Override
    public PageInfo<Salary> getPageSalary(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<Salary> salaryList = salaryDao.getAllSalary();
        return new PageInfo<>(salaryList);
    }

    /**
     * 获取一个薪资信息
     * @param user_id 用户id
     * @return 薪资信息
     */
    public Salary getOneSalary(BigInteger user_id){
        return salaryDao.getOneSalary(user_id);
    }


    /**
     * 搜索薪资信息
     * @param page 页码
     * @param limit 数量
     * @param login_name 帐号
     * @param create_time 创建时间
     * @return 查询结果
     */
    @Override
    public PageInfo<Salary> searchSalary(int page, int limit, String login_name, String create_time) {
        Map<String,Object> map = new HashMap<>();
        map.put("login_name",login_name);
        map.put("create_time",create_time);
        PageHelper.startPage(page, limit);
        List<Salary> salaryList = salaryDao.searchSalary(map);
        return new PageInfo<>(salaryList);
    }

    /**
     * 奖惩录入
     * @return 影响的行数
     */
    @Override
    public int updateSalaryInput(Salary salary) {
        int row = salaryDao.updateSalaryInput(salary);
        salary = salaryDao.getOneSalary(salary.getUser_id());
        if(salary!=null){
            salary.calculateSalary();//计算工资
            salaryDao.updateTotalSalary(salary);
        }
        return row;
    }

    /**
     * 获取所有薪资配置信息
     * @return 所有薪资配置信息
     */
    @Override
    public List<Salary> getAllSalaryConfig() {
        return salaryDao.getAllSalaryConfig();
    }

    /**
     * 分页获取所有薪资配置信息
     * @return 分页薪资配置信息
     */
    @Override
    public PageInfo<Salary> getPageSalaryConfig(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<Salary> salaryList = salaryDao.getAllSalaryConfig();
        return new PageInfo<>(salaryList);
    }


    /**
     * 修改薪资配置信息
     * @param salary 薪资配置信息
     *
     * @return 影响的行数
     */
    @Override
    public int updateSalaryConfig(Salary salary) {
        int row = salaryDao.updateSalaryConfig(salary);
        salary = salaryDao.getOneSalary(salary.getUser_id());
        if(salary!=null){
            salary.calculateSalary();
            salaryDao.updateTotalSalary(salary);
        }
        return row;
    }

    /**
     * 获取单个薪资配置信息
     * @param login_name 登录名
     * @return 薪资配置信息
     */
    @Override
    public Salary getOneSalaryConfig(String login_name) {
        return null;
    }

    /**
     * 搜索薪资配置信息
     * @param page 页码
     * @param limit 数量
     * @param login_name 帐号
     * @param create_time 创建时间
     * @return 查询结果
     */
    @Override
    public PageInfo<Salary> searchSalaryConfig(int page, int limit,String login_name, String create_time) {
        Map<String,Object> map = new HashMap<>();
        map.put("login_name",login_name);
        map.put("create_time",create_time);
        PageHelper.startPage(page, limit);
        List<Salary> salaryList = salaryDao.searchSalaryConfig(map);
        return new PageInfo<>(salaryList);
    }

    /**
     * 添加薪资配置信息
     * @return 影响的行数
     */
    @Override
    public int insertSalaryConfig(Salary salary) {
        if(salary!=null)
            salary.calculateSalary();
        return salaryDao.insertSalaryConfig(salary);
    }

    @Override
    public int updateSalaryChecked(Salary salary) {
        return salaryDao.updateSalaryChecked(salary);
    }

    /**
     * 获取部门所有薪资信息
     * @return 部门所有薪资信息
     */
    @Override
    public List<Salary> getAllDeptSalary(int dept_id) {
        return salaryDao.getAllDeptSalaryById(dept_id);
    }

    /**
     * 分页获取部门所有薪资信息
     * @return 分页部门所有薪资信息
     */
    @Override
    public PageInfo<Salary> getPageDeptSalary(Integer page, Integer limit, int dept_id) {
        PageHelper.startPage(page, limit);
        List<Salary> deptSalaryList = salaryDao.getAllDeptSalaryById(dept_id);
        return new PageInfo<>(deptSalaryList);
    }


    @Override
    public int deleteSalaryById(Map<String, Object> map) {
        String user_id = (String) map.get("user_id");
        if(user_id!=null) {
            String[] array = user_id.split(";");
            return salaryDao.deleteSalaryById(array);
        }
        return 0;
    }


}
