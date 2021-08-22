package com.salary.service.imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.salary.dao.SalaryDao;
import com.salary.entity.Salary;
import com.salary.service.SalaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SalaryServiceImp implements SalaryService {

    @Autowired
    private SalaryDao salaryDao;

    @Override
    public List<Salary> getAllSalary() {
        return salaryDao.getAllSalary();
    }

    @Override
    public PageInfo<Salary> getPageSalary(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<Salary> salaryList = salaryDao.getAllSalary();
        return new PageInfo<>(salaryList);
    }

    @Override
    public List<Salary> getAllDeptSalary(int dept_id) {
        return salaryDao.getAllDeptSalaryById(dept_id);
    }

    @Override
    public PageInfo<Salary> getPageDeptSalary(Integer page, Integer limit, int dept_id) {
        PageHelper.startPage(page, limit);
        List<Salary> deptSalaryList = salaryDao.getAllDeptSalaryById(dept_id);
        return new PageInfo<>(deptSalaryList);
    }
}
