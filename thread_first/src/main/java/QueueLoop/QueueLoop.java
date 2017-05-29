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

    /**
     * 队列满,或者添加的元素的索引失败,都会导致NoSuchElementException
     * @param e 添加的元素
     * @return 添加成功与否
     * @throws NoSuchElementException
     */
    public boolean add(int e) throws NoSuchElementException{
        if (head > tail || tail - head == elements.length ){
            throw new NoSuchElementException("添加操作，tail >= head 索引异常!!");
        }
        int index = (tail++)%elements.length;
        elements[index] = e;
        return true;
    }

    /**
     * 队列为空，或者索引失败，都会导致NoSuchElementException
     * @return
     * @throws NoSuchElementException
     */
    public int remove() throws NoSuchElementException{
        if (head >= tail){
            throw new NoSuchElementException("删除元素失败，队列为空，或者索引异常!!!");
        }
        int index = head++%elements.length;
        return elements[index];
    }

    public int getHeadElement() throws NoSuchElementException{
        if (head >= tail){
            throw new NoSuchElementException("队列为空，或者索引异常!!!");
        }
        int index = head%elements.length;
        return elements[index];
    }

    public int getTailElement() throws NoSuchElementException{
        if (head >= tail){
            throw new NoSuchElementException("队列为空,或者索引异常!!!");
        }
        int index = tail%elements.length - 1;
        return elements[index];
    }

    public boolean queryElements(int e){
        if (head >= tail){
            return false;
        }
        int start = head;
        int end = tail;
        while (start < end){
            int index = start++ % elements.length;
            if (elements[index] == e){
                return true;
            }
        }
        return false;
    }

    /**
     * 元素实际个数
     * @return 里面存储的元素的个数
     */
    public int size(){
        return tail - head;
    }

    /**
     * 分配实际的空间大小
     * @return 实际分配的空间大小
     */
    public int length(){
        return elements.length;
    }
}
