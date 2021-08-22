package com.salary.dao;

import com.salary.entity.Salary;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SalaryDao {

    List<Salary> getAllSalary();

    List<Salary> getAllDeptSalaryById(int dept_id);
}
