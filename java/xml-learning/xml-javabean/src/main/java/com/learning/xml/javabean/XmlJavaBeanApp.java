package com.learning.xml.javabean;

import com.learning.xml.javabean.domain.Student;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.io.StringWriter;

public class XmlJavaBeanApp {

    public static void main(String[] args) throws Exception {
        Student student=new Student();
        student.setAddress("中国");
        student.setName("Kevin");
        student.setAge(18);
        String xml = modelToStringXML(student);
        System.out.println(xml);
        Student s = (Student) documentToModel(Student.class, Thread.currentThread().getContextClassLoader().getResourceAsStream("student.xml"));
        System.out.println(s.getAge());

    }

    /**
     * 将报文节点反序列化为实体类
     */
    public static final Object documentToModel(Class clazz, InputStream inputStream) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        //得到反序列化实例Unmarshaller
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return unmarshaller.unmarshal(inputStream);
    }

    /**
     * 将实体类转序列化为对应String类型xml节点
     */
    public static final String modelToStringXML(Object obj) throws Exception {
        StringWriter writer = new StringWriter();
        JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = jaxbContext.createMarshaller();
        //设置序列化的编码格式
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        //设置格式化输出
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(obj, writer);

        return writer.toString();

    }
}
