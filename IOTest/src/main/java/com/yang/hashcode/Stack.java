package com.yang.hashcode;


import java.util.Arrays;

/**
 * Created by yz on 2018/1/14.
 */
public class Stack implements Cloneable{
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack() {
        this.elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void pop(Object element) {
        ensureCapacity();
        elements[size++] = element;
    }

    public Object push() throws Exception {
        if (0 == size) {
            throw new Exception("no elements in stack");
        }
        Object element = elements[--size];
        elements[size] = null;
        return element;
    }

    private void ensureCapacity() {
        if (elements.length == size) {
            //noinspection Since15
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }

    @Override
    protected Stack clone() throws CloneNotSupportedException {
        Stack stack = (Stack) super.clone();
        stack.elements = elements.clone();
        return stack;
    }

    public static void main(String[] args) throws Exception {
        Stack stack = new Stack();
        stack.pop("yang");

        Stack cloneStack = (Stack) stack.clone();
        System.out.println(cloneStack.push());
        System.out.println(stack.push());
    }
}
