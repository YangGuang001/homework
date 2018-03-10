package com.yang.resttemplate;

import com.netflix.servo.DefaultMonitorRegistry;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.cloud.client.loadbalancer.*;
import org.springframework.cloud.netflix.metrics.DefaultMetricsTagProvider;
import org.springframework.cloud.netflix.metrics.MetricsClientHttpRequestInterceptor;
import org.springframework.cloud.netflix.metrics.MetricsTagProvider;
import org.springframework.cloud.netflix.metrics.servo.ServoMonitorCache;
import org.springframework.cloud.netflix.ribbon.RibbonClientSpecification;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by yz on 2018/3/10.
 */
public class TestRestTemplate {
    public static void main(String[] args) {
        SpringClientFactory factory = new SpringClientFactory();
//        List<RibbonClientSpecification> list = new ArrayList<RibbonClientSpecification>();
//        list.add(new RibbonClientSpecification());
//        factory.setConfigurations(list);

        LoadBalancerClient loadBalanced = new RibbonLoadBalancerClient(factory);
        List<LoadBalancerRequestTransformer> transformers = Collections.emptyList();
        LoadBalancerRequestFactory requestFactory = new LoadBalancerRequestFactory(loadBalanced, transformers);

        final LoadBalancerInterceptor interceptor = new LoadBalancerInterceptor(loadBalanced, requestFactory);
        final RetryLoadBalancerInterceptor retryLoadBalancerInterceptor = new RetryLoadBalancerInterceptor(loadBalanced,
                retryTemplate(), new LoadBalancerRetryProperties(), new LoadBalancedRetryPolicyFactory.NeverRetryFactory(),
                requestFactory);


//        final RestTemplateCustomizer restTemplateCustomizer = new RestTemplateCustomizer() {
//            public void customize(RestTemplate restTemplate) {
//                List<ClientHttpRequestInterceptor> list = new ArrayList<ClientHttpRequestInterceptor>();
//                list.add(retryLoadBalancerInterceptor);
//                restTemplate.setInterceptors(list);
//            }
//        };


//        System.out.println(restTemplateCustomizer.toString());

        RestTemplate restTemplate = new RestTemplate();
        List<ClientHttpRequestInterceptor> list = new ArrayList<ClientHttpRequestInterceptor>();
        list.add(retryLoadBalancerInterceptor);
        restTemplate.setInterceptors(list);
        String string = restTemplate.getForObject("http://localhost:8020/1", String.class);
        System.out.println(string);

    }

    public static RetryTemplate retryTemplate() {
        RetryTemplate template =  new RetryTemplate();
        template.setThrowLastExceptionOnExhausted(true);
        return template;
    }
}
