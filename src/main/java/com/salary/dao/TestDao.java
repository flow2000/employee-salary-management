package com.salary.dao;

import com.salary.entity.Salary;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TestDao {

    int UpdateUserSalary(List<Salary> list);

}
