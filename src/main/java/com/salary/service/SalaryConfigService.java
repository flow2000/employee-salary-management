package com.salary.service;

import com.github.pagehelper.PageInfo;
import com.salary.entity.SalaryConfig;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SalaryConfigService {

    List<SalaryConfig> getAllSalaryConfig();

    PageInfo<SalaryConfig> getPageSalaryConfig(Integer page, Integer limit);

    int updateSalaryConfig(SalaryConfig salaryConfig);

    int insertSalaryConfig(SalaryConfig salaryConfig);
}
