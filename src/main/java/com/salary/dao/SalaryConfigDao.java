package com.salary.dao;

import com.salary.entity.SalaryConfig;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SalaryConfigDao {

    List<SalaryConfig> getAllSalaryConfig();

    int updateSalaryConfig(SalaryConfig salaryConfig);

    int insertSalaryConfig(SalaryConfig salaryConfig);
}
