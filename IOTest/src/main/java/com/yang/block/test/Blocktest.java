package com.yang.block.test;

import java.util.Scanner;

/**
 * Created by yz on 2018/9/3.
 */
public class Blocktest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] nums = scanner.nextLine().split(" ");
        // read n
        int n = Integer.valueOf(nums[0]);

        int m = Integer.valueOf(nums[1]);

        String str = scanner.nextLine();

        String[] numStr = str.split(" ");

        int[] list = new int[n];

        for (int i=0; i < numStr.length; i++) {
           list[Integer.valueOf(numStr[i])-1]++;
        }

        int max = m;
        for (int j=0; j<n; j++) {
            if (max>list[j]) {
                max = list[j];
            }
        }
        System.out.println("max core: " + max);
    }
}
