package com.yang.powermock.test;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

/**
 * Created by yz on 2018/10/13.
 */
public class VirtualMachineTest {
    public static void main(String[] args) {

        for (VirtualMachineDescriptor descriptor : VirtualMachine.list()) {
            System.out.println("displayName: " + descriptor.displayName() + " id: " + descriptor.id());
        }

    }
}
