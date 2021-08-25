package com.salary.service;

import com.github.pagehelper.PageInfo;
import com.salary.entity.Salary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface SalaryService {

    List<Salary> getAllSalary();

    PageInfo<Salary> getPageSalary(Integer page, Integer limit);

    PageInfo<Salary> searchSalary(int page, int limit, String login_name, String create_time);

    int updateSalaryInput(Salary salary);

    List<Salary> getAllSalaryConfig();

    PageInfo<Salary> getPageSalaryConfig(Integer page, Integer limit);

    int updateSalaryConfig(Salary salary);

    Salary getOneSalaryConfig(String login_name);

    PageInfo<Salary> searchSalaryConfig(int page, int limit,String login_name, String create_time);

    int insertSalaryConfig(Salary salary);

    int updateSalaryChecked(Salary salary);

    List<Salary> getAllDeptSalary(int dept_id);

    PageInfo<Salary> getPageDeptSalary(Integer page, Integer limit, int dept_id);

    int deleteSalaryById(Map<String, Object> map);

    PageInfo<Salary> searchDeptSalary(int page, int limit, String login_name, String create_time, int dept_id);
}
