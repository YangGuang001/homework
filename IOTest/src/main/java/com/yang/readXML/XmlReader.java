package com.yang.readXML;


import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by yz on 2018/2/1.
 */
public class XmlReader {
    private static final Logger logger = Logger.getLogger(XmlReader.class);

    private Document doc;
    public XmlReader(String xmlFile) {
//        DocumentBuilderFactory document = DocumentBuilderFactory.newInstance();
//        try {
//            PropertySourcesPlaceholder placeholderConfigurer = new PropertySourcesPlaceholderConfigurer();
//            placeholderConfigurer.get
//            DocumentBuilder builder = document.newDocumentBuilder();
//            doc = builder.parse(xmlFile);
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        }
    }
}
