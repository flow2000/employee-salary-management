package com.salary.service.imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.salary.dao.SalaryConfigDao;
import com.salary.entity.SalaryConfig;
import com.salary.service.SalaryConfigService;
import com.salary.util.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class SalaryConfigServiceImp implements SalaryConfigService {

    @Autowired
    private SalaryConfigDao salaryConfigDao;

    @Override
    public List<SalaryConfig> getAllSalaryConfig() {
        return salaryConfigDao.getAllSalaryConfig();
    }

    @Override
    public PageInfo<SalaryConfig> getPageSalaryConfig(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<SalaryConfig> salaryConfigList = salaryConfigDao.getAllSalaryConfig();
        return new PageInfo<>(salaryConfigList);
    }

    @Override
    public int updateSalaryConfig(SalaryConfig salaryConfig) {
        return salaryConfigDao.updateSalaryConfig(salaryConfig);
    }

    @Override
    public AjaxResult getOneSalaryConfig(String login_name) {
        return null;
    }

    @Override
    public AjaxResult searchSalaryConfig(int page, int limit,String login_name, String create_time) {
        Map<String,Object> map = new HashMap<>();
        map.put("login_name",login_name);
        map.put("create_time",create_time);
        PageHelper.startPage(page, limit);
        List<SalaryConfig> salaryConfigList = salaryConfigDao.searchSalaryConfig(map);
        return AjaxResult.returnMessage(new PageInfo<>(salaryConfigList));
    }

    @Override
    public int insertSalaryConfig(SalaryConfig salaryConfig) {
        return salaryConfigDao.insertSalaryConfig(salaryConfig);
    }


}
