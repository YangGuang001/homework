package com.yang.resttemplate;



import net.sf.json.JSONObject;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class TestRestTemplate {
    @Test
    public void testHttpEntity() {
        RestTemplate restTemplate = new RestTemplate();
        User user = new User();
        user.setAge(18);
        user.setId(1111L);
        user.setName("yang");
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());

        JSONObject jsonObject = JSONObject.fromObject(user);

        HttpEntity<String> formEntity = new HttpEntity<String>(jsonObject.toString(), headers);
        ResponseEntity<String> result = restTemplate.postForEntity("http://localhost:8020/1", formEntity, String.class);
        if (result.getStatusCode().is2xxSuccessful()) {
            System.out.println(result.getBody());
        }
    }


    @Test
    public void testGetForObject() {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> params = new HashMap<>();
        params.put("age", "26");
        params.put("name", "lee");
        User result = restTemplate.getForObject("http://localhost:8020/params?name={name}&age={age}", User.class, params);
        System.out.println(result.toString());
    }
}
