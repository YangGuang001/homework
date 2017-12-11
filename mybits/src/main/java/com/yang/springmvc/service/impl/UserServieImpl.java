package com.yang.springmvc.service.impl;

import com.yang.springmvc.dao.UserMapper;
import com.yang.springmvc.domain.User;
import com.yang.springmvc.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by yz on 2017/12/11.
 */
@Service("userService")
public class UserServieImpl implements IUserService {

    @Resource
    private UserMapper userMapper;

    public User getUserById(int userId) {
        return this.userMapper.selectByPrimaryKey(userId);
    }
}
