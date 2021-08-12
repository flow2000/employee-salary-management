package com.salary.controller;

import com.salary.util.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Objects;

@RestController
@Api(value="CacheController",tags="缓存接口")
@RequestMapping("/api/cache")
public class CacheController {

    @Resource
    private CacheManager cacheManager;

    /**
     * 清空所有缓存
     * @return 清除成功消息
     */
    @ApiOperation(value = "清空所有缓存")
    @ApiImplicitParam()
    @GetMapping("/clearAll")
    public AjaxResult clearAll(){
        cacheManager.getCacheNames().forEach(cacheName ->{
            Objects.requireNonNull(cacheManager.getCache(cacheName)).clear();
        });
        return AjaxResult.success("服务端清理缓存成功");
    }

}
