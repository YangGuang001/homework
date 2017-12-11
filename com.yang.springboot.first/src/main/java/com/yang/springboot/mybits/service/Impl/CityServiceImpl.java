package com.yang.springboot.mybits.service.Impl;

import com.github.pagehelper.PageHelper;
import com.yang.springboot.mybits.entity.City;
import com.yang.springboot.mybits.mapper.CityMapper;
import com.yang.springboot.mybits.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CityServiceImpl implements CityService{
    @Autowired
    private CityMapper cityDao;

    public List<City> getAll() {
        PageHelper.startPage(1,1);
        return cityDao.selectAll();
    }
}
