package QueueLoop;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by yz on 2017/5/28.
 */
public class testcase {

    @Test
    public void test01(){
        QueueLoop queueLoop = new QueueLoop(10);
        Assert.assertEquals(10,queueLoop.length());
        Assert.assertEquals(0,queueLoop.size());


    }
}
