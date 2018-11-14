package com.reflect;

import com.reflect.bean.ReflectBean;
import jdk.nashorn.internal.runtime.logging.Logger;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.sql.Ref;
import java.util.Arrays;

public class ReflectL {
    public static void main(String[] args) throws Exception {

        System.out.println("################# 构造器 ################");
        Constructor<ReflectBean> defaultDeclaredConstructor = ReflectBean.class.getDeclaredConstructor();
        ReflectBean reflectBeanByDefaultConstructor = defaultDeclaredConstructor.newInstance();
        System.out.println(reflectBeanByDefaultConstructor);



        System.out.println("################## field ###############");
        Field[] declaredAllFiled = ReflectBean.class.getDeclaredFields();
        System.out.println(Arrays.toString(declaredAllFiled));


        System.out.println("################## class ##############");
        Class<?>[] declaredClasses = ReflectBean.class.getDeclaredClasses();
        System.out.println(Arrays.toString(declaredClasses));


        System.out.println("################## annotation ##########");
        Logger declaredAnnotation = ReflectBean.class.getDeclaredAnnotation(Logger.class);
        System.out.println(declaredAnnotation);
        Annotation[] declaredAnnotations = ReflectBean.class.getDeclaredAnnotations();
        System.out.println(Arrays.toString(declaredAnnotations));
        //重复注解
        ReflectBean.class.getDeclaredAnnotationsByType(Logger.class);
        //方法
        Field name = ReflectBean.class.getDeclaredField("name");
        Resource resource = name.getDeclaredAnnotation(Resource.class);
        System.out.println(resource);

        System.out.println("################## GenericType 泛型类型 ###############");
        //TypeVariable: T t
        System.out.println(ReflectBean.class.getDeclaredField("t").getGenericType());
        System.out.println(ReflectBean.class.getDeclaredField("t").getGenericType().getClass());
        System.out.println(ReflectBean.class.getDeclaredField("t").getType());
        //ParameterizedType: List<Child> children
        System.out.println(ReflectBean.class.getDeclaredField("children").getGenericType());
        System.out.println(ReflectBean.class.getDeclaredField("children").getGenericType().getClass());
        System.out.println(((ParameterizedTypeImpl)ReflectBean.class.getDeclaredField("children").getGenericType()).getActualTypeArguments()[0]);
        System.out.println(((ParameterizedTypeImpl)ReflectBean.class.getDeclaredField("children").getGenericType()).getRawType());
        System.out.println(ReflectBean.class.getDeclaredField("children").getType());
        //WildcardType
        System.out.println(ReflectBean.class.getDeclaredField("numbers").getGenericType());
        System.out.println(ReflectBean.class.getDeclaredField("numbers").getGenericType().getClass());
        System.out.println(((ParameterizedTypeImpl)ReflectBean.class.getDeclaredField("numbers").getGenericType()).getActualTypeArguments()[0]);
        System.out.println(((WildcardType)((ParameterizedTypeImpl)ReflectBean.class.getDeclaredField("numbers").getGenericType()).getActualTypeArguments()[0]).getUpperBounds()[0]);




        System.out.println("############## 分别获取public和private对象 ########");
        //所有accessible public fields
        Field[] fields = ReflectBean.class.getFields();
        System.out.println(Arrays.toString(fields));
        //所有字段包括私有的private
        Field[] declaredFieldsWithPrivate = ReflectBean.class.getDeclaredFields();
        System.out.println(Arrays.toString(declaredFieldsWithPrivate));



        System.out.println("############## 获取修饰符 #####################");
        //构造器
        Constructor<ReflectBean> declaredConstructorToModifer = ReflectBean.class.getDeclaredConstructor();
        int modifiers = declaredConstructorToModifer.getModifiers();
        System.out.println(Modifier.isPublic(modifiers));
        //class本身
        int modifiersClass = ReflectBean.class.getModifiers();
        System.out.println(Modifier.isPublic(modifiersClass));
        //内部class
        Class<?>[] declaredClassesInner = ReflectBean.class.getDeclaredClasses();
        Method getInnerName = declaredClassesInner[0].getDeclaredMethod("getInnerName");
        System.out.println(Modifier.isPublic(getInnerName.getModifiers()));
        System.out.println(getInnerName.isAccessible());


        System.out.println("############## 通过反射设置private对象的accessible并调用 #########*");
        //field
        Field declaredName = ReflectBean.class.getDeclaredField("name");
        ReflectBean reflectBean = new ReflectBean();
        declaredName.setAccessible(true);
        declaredName.set(reflectBean, "myNameByPrivateSetMethod");
        System.out.println(reflectBean.getName());
        //constructor
        Constructor<ReflectBean> declaredConstructor = ReflectBean.class.getDeclaredConstructor(String.class);
        declaredConstructor.setAccessible(true);
        ReflectBean myNameByPrivateConstructor = declaredConstructor.newInstance("myNameByPrivateConstructor");
        System.out.println(myNameByPrivateConstructor.getName());

    }
}
