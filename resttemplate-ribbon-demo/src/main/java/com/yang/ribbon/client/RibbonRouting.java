package com.yang.ribbon.client;

import com.netflix.client.AbstractLoadBalancerAwareClient;
import com.netflix.client.ClientFactory;
import com.netflix.client.IClient;
import com.netflix.client.http.HttpRequest;
import com.netflix.client.http.HttpResponse;
import com.netflix.niws.client.http.RestClient;
import com.netflix.zuul.dependency.ribbon.hystrix.RibbonCommand;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import javax.ws.rs.core.MultivaluedMap;
import java.io.InputStream;
import java.net.URISyntaxException;

import static com.netflix.client.http.HttpRequest.Verb;

/**
 * Created by yz on 2018/4/16.
 */
public class RibbonRouting {
    private String serviceName;

    private String url;

    private String method;

    private MultivaluedMap<String, String> headers;

    private MultivaluedMap<String, String> params;

    private InputStream requestEntity;

    public RibbonRouting(String serviceName, String url, String method) {
        this(serviceName, url, method, new MultivaluedMapImpl(), new MultivaluedMapImpl(), null);
    }

    public RibbonRouting(String serviceName, String url, String method, MultivaluedMap<String, String> headers, MultivaluedMap<String, String> params, InputStream requestEntity) {
        this.serviceName = serviceName;
        this.url = url;
        this.method = method;
        this.headers = headers;
        this.params = params;
        this.requestEntity = requestEntity;
    }

    public HttpResponse execute() {
        IClient restClient = ClientFactory.getNamedClient(serviceName);
        return forward((RestClient)restClient, getVerb(method), url, headers, params, requestEntity);
    }

    private HttpResponse forward(RestClient restClient, Verb verb, String url, MultivaluedMap<String, String> headers, MultivaluedMap<String, String> params, InputStream requestEntity) {
        try {
            RibbonCommand<RestClient> command = new RibbonCommand<RestClient>(restClient, verb, url, headers, params, requestEntity);
            return command.execute();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    Verb getVerb(String sMethod) {
        if (sMethod == null) return Verb.GET;
        sMethod = sMethod.toLowerCase();
        if (sMethod.equals("post")) return Verb.POST;
        if (sMethod.equals("put")) return Verb.PUT;
        if (sMethod.equals("delete")) return Verb.DELETE;
        if (sMethod.equals("options")) return Verb.OPTIONS;
        if (sMethod.equals("head")) return Verb.HEAD;
        return Verb.GET;
    }
}
