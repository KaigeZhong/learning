package com.reflect.bean;

import jdk.nashorn.internal.runtime.logging.Logger;

import javax.annotation.Resource;
import java.util.List;

@Logger
public class ReflectBean<T> {
    @Resource
    private String name;
    /**
     * TypeVariable
     *
     * T的getType类型为: Object, 泛型采用的编译器擦除，用Object进行替换
     * T的getGenericType泛型类型为: TypeVariable
     */
    private T t;
    /**
     * ParameterizedType
     *
     * List<Child>的getType类型为: List
     * List<Child>的getGenericType泛型类型为: ParameterizedType, 其中getActualTypeArguments为String类,getRawType为List
     */
    private List<String> children;
    /**
     * ParameterizedType + TypeVariable
     */
    private List<T> ts;

    /**
     * ParameterizedType + WildcardType
     *
     * ？其实就是Object, 不过？可以用extend和super
     */
    private List<?> objects;
    private List<? extends Number> numbers;

    public ReflectBean() {

    }

    public String getName() {
        return name;

    }

    private ReflectBean(String name) {
        this.name = name;
    }

    public static class ReflectInnerBean {
       private String innerName;

        public String getInnerName() {
            return innerName;
        }
    }

}
