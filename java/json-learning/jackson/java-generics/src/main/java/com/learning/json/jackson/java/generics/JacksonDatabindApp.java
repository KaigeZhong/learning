package com.learning.json.jackson.java.generics;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.learning.json.jackson.java.generics.bean.pojo.Person;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public class JacksonDatabindApp {
    public static void main(String[] args) throws Exception {
        testWithOutAnnotaton();
    }

    public static void testWithOutAnnotaton() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

        List<Person> personList = new ArrayList<Person>();
        Person person = new Person();
        person.setpName("davenkin");
        person.setpAddress("");
        personList.add(person);
        System.out.println(objectMapper.writeValueAsString(personList));
//        List persons = objectMapper.readValue("[{\"p_name\":\"davenkin\",\"p_address\":\"\",\"p_mobile\":null}]", List.class);
//        System.out.println(((Person)persons.get(0)).getpName());//会报错，jason将list里面的解析成了map
        List persons = objectMapper.readValue("[{\"p_name\":\"davenkin\",\"p_address\":\"\",\"p_mobile\":null}]", new TypeReference<List<Person>>() { });
        System.out.println(((Person)persons.get(0)).getpName());

        System.out.println(objectMapper.writeValueAsString(person));
        Person person1 = objectMapper.readValue("{\"p_name\":\"davenkin\",\"p_address\":\"\",\"p_mobile\":null}", new TypeReference<Person>() { } );
        System.out.println(person1.getpName());


        System.out.println(((ParameterizedType)new TypeReference<List<Person>>() { }.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }
}
