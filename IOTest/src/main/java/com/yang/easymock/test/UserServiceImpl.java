package com.yang.easymock.test;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by yz on 2017/7/25.
 */
public class UserServiceImpl {

    @Getter
    @Setter
    private UserDao dao;

    public User query(String id)
    {
        return dao.getById(id);
    }
}
