package com.yang.java8.demo;

import org.junit.Test;

import javax.annotation.Nonnull;
import java.io.UnsupportedEncodingException;
import java.time.Clock;
import java.util.*;
import java.util.stream.Collectors;

public class Java8 {

    @Test
    public void testLambda() {
        String pom = "get result : ";

        Arrays.asList("a", "b", "c").forEach(System.out::println);

        Arrays.asList("a", "b", "c").forEach( e -> {
            System.out.println(pom);
            System.out.println(e);
        } );

        Arrays.asList("a", "b", "c").sort(String::compareTo);

        Java8 java8 = new Java8();

        MathOperation addition = (a, b) -> a + b;

        MathOperation sub = (a, b) -> a - b;



        System.out.println(java8.operation(10, 5, addition));
        System.out.println(java8.operation(10, 5, sub));
    }

    private int operation(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }

    @Test
    public void testMethodReferences() {
        List<String> list = new ArrayList<>();
        list.add("yang ");
        list.add("xin");
        list.forEach(System.out::println);

        String[] arrays = (String[]) list.toArray();
        Arrays.sort(arrays, String::compareToIgnoreCase);
        for (String str : arrays) {
            System.out.println(str);
        }
    }

    @Test
    public void testMethodReferences2() {
        Person[] persons = initPerson();
        for (Person person : persons)
            person.printPersion();

        Arrays.sort(persons, Comparator.comparingInt(Person::getAge));
        for (Person person : persons)
            person.printPersion();
    }

    @Test
    public void testOptional() {
        Integer value1 = null;
        Integer value2 = new Integer(10);
        Optional<Integer> a = Optional.ofNullable(value1);
        Optional<Integer> b = Optional.of(value2);
        System.out.println(new Java8().sum(a, b));
    }

    public Integer sum(Optional<Integer> a, Optional<Integer> b) {
        System.out.println(a.isPresent());
        System.out.println(b.isPresent());

        Integer value1 = a.orElse(new Integer(0));
        Integer value2 = b.get();
        return value1 + value2;
    }

    @Test
    public void testNullable() {
        System.out.println(print(new Person()));
    }

    public String print(@Nonnull Person person) {
        return person.toString();
    }

    private Person[] initPerson() {
        Person[] persons = new Person[3];
        Person person = new Person();
        person.setName("张三");
        person.setAge(10);
        persons[0] = person;

        person = new Person();
        person.setName("李四");
        person.setAge(50);
        persons[1] = person;

        person = new Person();
        person.setName("王五");
        person.setAge(2);
        persons[2] = person;
        return persons;
    }

    @Test
    public void testStream() {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        List<String> list = strings.stream().filter(n -> !"".equals(n)).limit(3).collect(Collectors.toList());
        list.forEach(System.out::println);
    }

    @Test
    public void testTime() {
        Clock clock = Clock.systemUTC();
        System.out.println(clock.instant());
        System.out.println(clock.millis());
    }

    @Test
    public void testBase64() {
        byte[] bytes = "yangxinzhao@huawei.com".getBytes();
        String base64encodedString = Base64.getEncoder().encodeToString(bytes);
        System.out.println("Base64 Encoded String (Basic) :" + base64encodedString);

        byte[] base64decodeBytes = Base64.getDecoder().decode(base64encodedString);
        System.out.println("Original String: " + new String(base64decodeBytes));

        String base64UrlEncodeString = Base64.getUrlEncoder().encodeToString(bytes);
        System.out.println("Base64 Encoded String (URL) :" + base64UrlEncodeString);

        byte[] base64UrldecodeBytes = Base64.getUrlDecoder().decode(base64UrlEncodeString);
        System.out.println("Original String: " + new String(base64UrldecodeBytes));

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            stringBuilder.append(UUID.randomUUID().toString());
        }

        System.out.println(stringBuilder.toString());

        try {
            byte[] mimeBytes = stringBuilder.toString().getBytes("UTF-8");
            String mimeEncodeString = Base64.getMimeEncoder().encodeToString(mimeBytes);
            System.out.println("Base64 Encoded String (MIME) :" + mimeEncodeString);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}

interface MathOperation {
    int operation(int a, int b);
}
