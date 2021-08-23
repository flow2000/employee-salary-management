package com.salary.service;

import com.github.pagehelper.PageInfo;
import com.salary.entity.SalaryConfig;
import com.salary.util.AjaxResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SalaryConfigService {

    List<SalaryConfig> getAllSalaryConfig();

    PageInfo<SalaryConfig> getPageSalaryConfig(Integer page, Integer limit);

    int updateSalaryConfig(SalaryConfig salaryConfig);

    AjaxResult getOneSalaryConfig(String login_name);

    AjaxResult searchSalaryConfig(int page, int limit,String login_name, String create_time);

    int insertSalaryConfig(SalaryConfig salaryConfig);
}
