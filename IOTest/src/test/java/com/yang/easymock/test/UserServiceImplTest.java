package com.yang.easymock.test;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by yz on 2017/7/26.
 */
public class UserServiceImplTest
{
    @Test
    public void testQuery()
    {
        User expectedUser = new User();

        UserDao mock = EasyMock.createMock(UserDao.class);
        EasyMock.expect(mock.getById("1001")).andReturn(expectedUser);
        EasyMock.replay(mock);

        UserServiceImpl service = new UserServiceImpl();
        service.setDao(mock);
        User user = service.query("1001");
        Assert.assertEquals(expectedUser, user);
        EasyMock.verify(mock);
    }
}
