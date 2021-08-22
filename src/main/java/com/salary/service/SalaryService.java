package com.salary.service;

import com.github.pagehelper.PageInfo;
import com.salary.entity.Salary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SalaryService {

    List<Salary> getAllSalary();

    PageInfo<Salary> getPageSalary(Integer page, Integer limit);

    List<Salary> getAllDeptSalary(int dept_id);

    PageInfo<Salary> getPageDeptSalary(Integer page, Integer limit, int dept_id);
}
