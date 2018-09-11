package com;

import com.bean.ReflectBean;

import java.lang.reflect.Field;
import java.util.Arrays;

public class ReflectL {
    public static void main(String[] args) throws Exception {
        //accessible public fields
        Field[] fields = ReflectBean.class.getFields();
        System.out.println(Arrays.toString(fields));
        //所有字段包括私有的private
        Field[] declaredFields = ReflectBean.class.getDeclaredFields();
        System.out.println(Arrays.toString(declaredFields));
        /*will throw NoSuchFieldException*/
//        Field name = ReflectBean.class.getField("name");
//        System.out.println(name);
        Field declaredName = ReflectBean.class.getDeclaredField("name");
        System.out.println(declaredName);
        //通过反射给private字段赋值
        ReflectBean reflectBean = new ReflectBean();
        declaredName.setAccessible(true);
        declaredName.set(reflectBean, "myName");
        System.out.println(reflectBean.getName());
    }
}
