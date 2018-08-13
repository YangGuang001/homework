package com.yang.languange.test.abstractfinal;

import org.junit.Test;

/**
 * Created by yz on 2018/7/8.
 */
public class HuaweiPay extends AbstractPay {
    public boolean beforePayCheck() {
        System.out.println("支付前检查");
        return true;
    }

    public boolean doPay() {
        System.out.println("支付过程中");
        return true;
    }

    public boolean afterPayCheck() {
        System.out.println("支付后检查");
        return true;
    }

    public boolean failedBack() {
        System.out.println("回退检查");
        return true;
    }

    @Test
    public void testHuaweiPay() {
        HuaweiPay huaweiPay = new HuaweiPay();
        huaweiPay.pay();
    }
}
