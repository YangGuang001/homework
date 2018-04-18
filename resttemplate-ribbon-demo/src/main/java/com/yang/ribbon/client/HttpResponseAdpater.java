package com.yang.ribbon.client;

import com.netflix.client.http.HttpResponse;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.AbstractClientHttpResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by yz on 2018/4/16.
 */
public class HttpResponseAdpater extends AbstractClientHttpResponse {
    private HttpResponse httpResponse;

    public HttpResponseAdpater(HttpResponse httpResponse) {
        this.httpResponse = httpResponse;
    }

    public int getRawStatusCode() throws IOException {
        return httpResponse.getStatus();
    }

    public String getStatusText() throws IOException {
        return httpResponse.getStatusLine();
    }

    public void close() {
        httpResponse.close();
    }

    public InputStream getBody() throws IOException {
        return httpResponse.getInputStream();
    }

    public HttpHeaders getHeaders() {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (Map.Entry<String, Collection<String>> entry : httpResponse.getHeaders().entrySet()) {
            map.put(entry.getKey(), new ArrayList<String>(entry.getValue()));
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.putAll(map);
        return httpHeaders;
    }
}
