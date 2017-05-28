package QueueLoop;

import java.util.NoSuchElementException;

/**
 * Created by yz on 2017/5/28.
 */
public class QueueLoop {
    private int[] elements;
    private int head;
    private int tail;

    QueueLoop(int size){
        this.elements = new int[size];
    }

    public boolean add(int e) throws NoSuchElementException{
        return true;
    }

    public int remove() throws NoSuchElementException{
        return 0;
    }

    public int getHeadElement() throws NoSuchElementException{
        return 0;
    }

    public int getTailElement() throws NoSuchElementException{
        return 0;
    }

    /**
     * 元素实际个数
     * @return 里面存储的元素的个数
     */
    public int size(){
        return 0;
    }

    /**
     * 分配实际的空间大小
     * @return 实际分配的空间大小
     */
    public int length(){
        return elements.length;
    }
}
