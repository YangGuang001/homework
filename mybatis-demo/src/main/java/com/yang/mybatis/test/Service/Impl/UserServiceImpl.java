package com.yang.mybatis.test.Service.Impl;

import com.github.pagehelper.PageHelper;
import com.yang.mybatis.test.Service.UserService;
import com.yang.mybatis.test.mapper.UserMapper;
import com.yang.mybatis.test.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yz on 2018/7/26.
 */
@Service(value = "userSerivce")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int addUser(User user) {
        return userMapper.insertSelective(user);
    }

    @Override
    public List<User> findAllUser(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return userMapper.selectAllUser();
    }
}
