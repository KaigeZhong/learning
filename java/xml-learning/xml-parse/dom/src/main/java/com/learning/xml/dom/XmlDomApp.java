package com.learning.xml.dom;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class XmlDomApp {
    public static void main(String[] args) throws Exception {
        // step 1:获得DOM解析器工厂
        // 工厂的作用是创建具体的解析器
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        // step 2：获得具体的dom解析器
        DocumentBuilder db = dbf.newDocumentBuilder();

        // step 3:解析一个xml文档，获得Document对象（根节点）
        Document document = db.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream("bookstore.xml"));

        // 获取root node, 也就是bookstore
        Element root = document.getDocumentElement();
        System.out.println(root.getTagName());

        /**
         * 根据标签访问节点
         */
        NodeList list = document.getElementsByTagName("book");
        // 遍历每一个节点
        for (int i = 0; i < list.getLength(); ++i)
        {
            Element element = (Element) list.item(0);
            System.out.println(element);
        }

        /**
         * 获得元素节点
         */
        System.out.println("######### 元素 #########");
        Element element = (Element) list.item(0);
        System.out.println(element.getNodeType());// Node.ELEMENT_NODE
        System.out.println(element.getNodeName());
        System.out.println(element.getTagName());//与getNodeName一样
        System.out.println(element.getNodeValue());//XML中，只有 属性、备注、文本，这几种节点才有nodeValue. element节点不具备node value
        System.out.println(element.getTextContent());

        /**
         * 获取属性节点
         */
        System.out.println("######### 属性 #########");
        Attr attr = element.getAttributeNode("category");
        System.out.println(attr.getNodeType());//Node.ATTRIBUTE_NODE
        System.out.println(attr.getNodeName());
        System.out.println(attr.getNodeValue());//属性节点具备nodevalue
        System.out.println(attr.getValue());//与getNodeValue一样
        System.out.println(element.getAttribute("category"));//与getNodeValue一样
        System.out.println(attr.getTextContent());

        /**
         * 获取文本节点
         */
        System.out.println("######### 文本 #########");
        Element titleElement = (Element) element.getElementsByTagName("title").item(0);
        Text text = (Text) titleElement.getFirstChild();
        System.out.println(text.getNodeType());//Node.TEXT_NODE
        System.out.println(text.getNodeName());
        System.out.println(text.getNodeValue());//文本节点具备nodevalue
        System.out.println(text.getTextContent());
    }
}
