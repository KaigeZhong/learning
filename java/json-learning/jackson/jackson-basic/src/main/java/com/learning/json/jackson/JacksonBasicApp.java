package com.learning.json.jackson;

import com.fasterxml.jackson.core.*;
import com.learning.json.jackson.bean.TwitterEntry;

import java.io.ByteArrayOutputStream;


public class JacksonBasicApp {
    public static void main(String[] args) throws Exception {
        testUnmarshalling();
        testMarshalling();
    }



    public static void testUnmarshalling() throws Exception {
        JsonFactory jsonF = new JsonFactory();

        String jsonStr = "{\n" +
                "  \"id\":123456789,\n" +
                "  \"text\":\"我是杨正，我在http://www.dubby.cn\",\n" +
                "  \"fromUserId\":123456, \n" +
                "  \"toUserId\":789,\n" +
                "  \"languageCode\":\"zh\"\n" +
                "}";
        JsonParser jp = jsonF.createParser(jsonStr);
        TwitterEntry entry = unmarshalling(jp);
        System.out.println(entry.toString());
    }

    public static void testMarshalling() throws Exception {
        JsonFactory jsonF = new JsonFactory();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        JsonGenerator jg = jsonF.createGenerator(byteArrayOutputStream, JsonEncoding.UTF8);
        jg.useDefaultPrettyPrinter();
        TwitterEntry entry = new TwitterEntry();
        entry.setId(1);
        entry.setFromUserId(2);
        entry.setLanguageCode("en");
        entry.setText("...");
        entry.setToUserId(3);
        marshalling(jg, entry);
        System.out.println(byteArrayOutputStream.toString());
    }

    private static TwitterEntry unmarshalling(JsonParser jp) throws Exception {
        // 检查是否是JSON
        if (jp.nextToken() != JsonToken.START_OBJECT) {
            throw new Exception("Expected data to start with an Object");
        }
        TwitterEntry result = new TwitterEntry();
        // 遍历属性，并一个一个的赋值
        while (jp.nextToken() != JsonToken.END_OBJECT) {
            String fieldName = jp.getCurrentName();
            jp.nextToken();
            switch (fieldName) {
                case "id":
                    result.setId(jp.getLongValue());
                    break;
                case "text":
                    result.setText(jp.getText());
                    break;
                case "fromUserId":
                    result.setFromUserId(jp.getIntValue());
                    break;
                case "toUserId":
                    result.setToUserId(jp.getIntValue());
                    break;
                case "languageCode":
                    result.setLanguageCode(jp.getText());
                    break;
                default:
                    throw new Exception("Unrecognized field '" + fieldName + "'");
            }
        }
        //关闭 parser
        jp.close();
        return result;
    }

    private static void marshalling(JsonGenerator jg, TwitterEntry entry) throws Exception {
        jg.writeStartObject();
        jg.writeNumberField("id", entry.getId());
        jg.writeStringField("text", entry.getText());
        jg.writeNumberField("fromUserId", entry.getFromUserId());
        jg.writeNumberField("toUserId", entry.getToUserId());
        jg.writeStringField("langugeCode", entry.getLanguageCode());
        jg.writeEndObject();
        jg.close();
    }
}
