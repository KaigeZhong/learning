package com.generic;

/**
 * 泛型T采用的编译器擦除，编译期用Object进行替换
 * 替换后就变成了
 * public class A{
 *     private Object t;
 *     public Object get(){
 *         return t;
 *     }
 *     public void set(Object t){
 *         this.t=t;
 *     }
 * }
 */
public class A<T>{
    private T t;
    public T get(){
        return t;
    }
    public void set(T t){
        this.t=t;
    }
}