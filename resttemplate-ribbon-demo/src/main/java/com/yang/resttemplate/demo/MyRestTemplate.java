package com.yang.resttemplate.demo;

import com.yang.ribbon.client.HttpResponseAdpater;
import com.yang.ribbon.client.RibbonRouting;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;

/**
 * Created by yz on 2018/4/16.
 */
public class MyRestTemplate extends RestTemplate {
    private static MyRestTemplate restTemplate = new MyRestTemplate();


    public static RestTemplate getInstance() {
        return restTemplate;
    }

    @Override
    protected <T> T doExecute(URI url, HttpMethod method, RequestCallback requestCallback, ResponseExtractor<T> responseExtractor) throws RestClientException {
        if (url.toString().startsWith("rest://")) {
            RibbonRouting routing = new RibbonRouting("microservice-provider-user", url.toString(), method.name());
            try {
                HttpResponseAdpater adpater = new HttpResponseAdpater(routing.execute());
                return responseExtractor.extractData(adpater);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return super.doExecute(url, method, requestCallback, responseExtractor);
    }
}
