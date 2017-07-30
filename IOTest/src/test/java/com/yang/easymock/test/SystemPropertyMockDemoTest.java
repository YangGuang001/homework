package com.yang.easymock.test;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Created by yz on 2017/7/26.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({SystemPropertyMockDemo.class})
public class SystemPropertyMockDemoTest {
    @Test
    public void demo() throws Exception{
        PowerMock.mockStatic(System.class);//Mock静态方法
        EasyMock.expect(System.getProperty("property")).andReturn("my property");//录制Mock对象的静态方法
        PowerMock.replayAll();//重放Mock对象
        Assert.assertEquals("my property",
                new SystemPropertyMockDemo().getSystemProperty());
        PowerMock.verifyAll();//验证Mock对象
    }
}
