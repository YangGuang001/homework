package com.yang.spring.conditional;

/**
 * Created by yz on 2017/12/21.
 */
public class WindowsListService implements ListService {
    public String showListCmd() {
        return "dir";
    }
}
