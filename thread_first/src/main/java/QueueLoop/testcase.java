package QueueLoop;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.NoSuchElementException;

/**
 * Created by yz on 2017/5/28.
 */
public class testcase {

    @Test
    public void test01(){
        QueueLoop queueLoop = new QueueLoop(10);
        Assert.assertEquals(10,queueLoop.length());
        Assert.assertEquals(0,queueLoop.size());

        queueLoop.add(1);
        queueLoop.add(2);

        Assert.assertEquals(2,queueLoop.size());
        Assert.assertEquals(1,queueLoop.getHeadElement());
        Assert.assertEquals(2,queueLoop.getTailElement());
        Assert.assertEquals(1,queueLoop.remove());
        Assert.assertEquals(1,queueLoop.size());
        Assert.assertEquals(2,queueLoop.getTailElement());
    }

    @Test(expected = NoSuchElementException.class)
    public void test(){
        QueueLoop queueLoop = new QueueLoop(10);

        for (int i=0; i < queueLoop.length(); i++){
            queueLoop.add(i);
        }

        queueLoop.add(11);

    }
}
