package com.yang.work.lession2;

/**
 * Created by yz on 2017/9/11.
 */
public class MMSCInfo {
    private final String device;

    private final String url;

    private final int maxAttachmentSizeInByte;

    public MMSCInfo(String device, String url, int maxAttachmentSizeInByte) {
        this.device = device;
        this.url = url;
        this.maxAttachmentSizeInByte = maxAttachmentSizeInByte;
    }

    public MMSCInfo(MMSCInfo prototype) {
        this.device = prototype.device;
        this.url = prototype.url;
        this.maxAttachmentSizeInByte = prototype.maxAttachmentSizeInByte;
    }

    public String getDevice() {
        return device;
    }

    public String getUrl() {
        return url;
    }

    public int getMaxAttachmentSizeInByte() {
        return maxAttachmentSizeInByte;
    }
}
