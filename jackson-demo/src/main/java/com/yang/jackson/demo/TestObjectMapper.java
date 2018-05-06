package com.yang.jackson.demo;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yz on 2018/5/4.
 */
public class TestObjectMapper {
    @Test
    public void testObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "{\"name\":\"Mahesh\", \"age\":21}";

        try {
            Student student = mapper.readValue(jsonString, Student.class);
            //write POJO to file
            mapper.writeValue(new File("student.json"), student);
            Student student1 = mapper.readValue(new File("student.json"), Student.class);
            System.out.println(student1);
            jsonString = mapper.writeValueAsString(student);
            System.out.println(jsonString);
            //get root
            JsonNode root = mapper.readTree(jsonString);
            System.out.println(root.at("/name").asText());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMap() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> map = new HashMap<String, Object>();
        String[] list = new String[]{"yang", "xin", "zhao"};
        int[] nums = new int[]{1,2,3,4,5};
        map.put("list", list);
        map.put("nums", nums);
        map.put("string", "String");
        Student student = new Student();
        student.setAge(18);
        student.setName("yang");
        map.put("student", student);

        mapper.writeValue(new File("map.json"), map);
        Map map1 = mapper.readValue(new File("map.json"), Map.class);
        System.out.println(map1.get("list"));
        System.out.println(map1.get("nums"));
        System.out.println(map1.get("string"));
        System.out.println(map1.get("student"));

        Map<String, Student> map2 = new HashMap<String, Student>();
        map2.put("student", student);
        mapper.writeValue(new File("map1.json"), map2);
        Map<String, Student> map3 = mapper.readValue(new File("map1.json"), new TypeReference<Map<String, Student>>() {});
        System.out.println(map3.get("student"));
    }

    @Test
    public void testParseAndGaner() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonFactory factory = mapper.getFactory();
        JsonGenerator generator = factory.createGenerator(new File("parse.json"), JsonEncoding.UTF8);
        generator.writeStartObject();
        generator.writeStringField("message", "yang");
        generator.writeEndObject();
        generator.close();

        JsonParser parser = factory.createParser(new File("parse.json"));
        JsonToken token = parser.nextToken();
        System.out.println(token);
        token = parser.nextToken();
        System.out.println(token);
        token = parser.nextToken();
        System.out.println(token);
        String msg = parser.getText();
        System.out.println(msg);
        parser.close();
    }
}
