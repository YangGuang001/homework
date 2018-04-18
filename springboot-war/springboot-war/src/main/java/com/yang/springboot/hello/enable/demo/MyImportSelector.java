package com.yang.springboot.hello.enable.demo;

import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

//@Component
//@Import(value = {MyImportSelector.class})
//@EnableLogInfo(name = "onlySale")
public class MyImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        System.out.println(importingClassMetadata.getAnnotationAttributes(EnableLogInfo.class.getName()).toString().contains("onlySale"));

        //获取自定的@EnableLogInfo 信息 如果包含 onlySale 则只注入Sales class 否则 注入 Sales 和 Market 两个类
        if (importingClassMetadata.getAnnotationAttributes(EnableLogInfo.class.getName()) != null
                && importingClassMetadata.getAnnotationAttributes(EnableLogInfo.class.getName()).toString().contains("onlySale"))
        {
            return new String[]{Sales.class.getName()};
        }

        return new String[]{Market.class.getName(), Sales.class.getName()};
    }
}
