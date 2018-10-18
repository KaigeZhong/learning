package com.learning.json.jackson.databind;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.learning.json.jackson.databind.bean.Student;
import com.learning.json.jackson.databind.bean.Teacher;
import com.learning.json.jackson.databind.bean.pojo.Person;
import com.learning.json.jackson.databind.bean.pojo.PersonWithAnnotation;

public class JacksonDatabindApp {
    public static void main(String[] args) throws Exception {
        testWithOutAnnotaton();
        testWithAnnotaton();
    }

    public static void testWithOutAnnotaton() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        /**
         * 驼峰与下划线互转, 除了设置策略，也可以使用@JsonNaming对当个类执行，还可以在bean使用@JsonProperty(value = "p_name")，但每个字段都要设置
         *
         * 策略类型：
         * KebabCaseStrategy: 肉串策略 - 单词小写，使用连字符'-'连接
         * SnakeCaseStrategy: 蛇形策略 - 单词小写，使用下划线'_'连接；即老版本中的LowerCaseWithUnderscoresStrategy
         * LowerCaseStrategy: 小写策略 - 简单的把所有字母全部转为小写，不添加连接符
         * UpperCamelCaseStrategy: 驼峰策略 - 单词首字母大写其它小写，不添加连接符；即老版本中的PascalCaseStrategy
         */
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        /**
         * 属性为NULL则不参与序列化, 除了设置，同样可以使用注解@JsonInclude(Include.NON_NULL)，该注解既可以在类上也可以在属性上
         * 类型：
         * Include.ALWAYS 默认,总是序列化
         * Include.NON_EMPTY 属性为 空（“”） 或者为 NULL 都不序列化
         * Include.NON_NULL 属性为NULL 不序列化
         */
        Person person = new Person();
        person.setpName("davenkin");
        person.setpAddress("");
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        System.out.println(objectMapper.writeValueAsString(person));
        Person person1 = objectMapper.readValue("{\"p_name\":\"davenkin\",\"p_address\":\"\",\"p_mobile\":null}", Person.class);
        System.out.println(person1.getpName());
    }

    public static void testWithAnnotaton() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        PersonWithAnnotation person = new PersonWithAnnotation();
        person.setpName("davenkinWithAnnotation");
        person.setpAddress("");
        System.out.println(objectMapper.writeValueAsString(person));
        PersonWithAnnotation person1 = objectMapper.readValue("{\"p_name\":\"davenkin\",\"address_withJsonPropertyAnnotation\":\"\",\"p_mobile\":null}", PersonWithAnnotation.class);
        System.out.println(person1.getpName());


        Student student = new Student();
        student.setsId(1);
        student.setsName("student name");
        Teacher teacher = new Teacher();
        teacher.settName("teacher name");
        student.setsTeacher(teacher);
        System.out.println(objectMapper.writeValueAsString(student));
    }

}
