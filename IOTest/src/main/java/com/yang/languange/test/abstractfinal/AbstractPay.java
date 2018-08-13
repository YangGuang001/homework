package com.yang.languange.test.abstractfinal;

/**
 * Created by yz on 2018/7/8.
 */
public abstract class AbstractPay implements Pay {
    public final boolean pay() {
        if (!beforePayCheck()) {
            return false;
        }

        if (!doPay()) {
            return failedBack();
        }

        return afterPayCheck();
    }

    /**
     * 开始支付前检查
     * @return
     */
    public abstract boolean beforePayCheck();

    /**
     * 开始支付
     * @return
     */
    public abstract  boolean doPay();

    /**
     * 支付后检查
     * @return
     */
    public abstract boolean afterPayCheck();

    /**
     * 支付失败后，进行回退检查
     * @return
     */
    public abstract boolean failedBack();
}
