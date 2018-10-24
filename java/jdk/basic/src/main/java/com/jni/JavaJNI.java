package com.jni;

/**
 * cd /home/k/work/research/learning/java/jdk/basic/src/main/java
 * javac com/jni/JavaJNI.java
 * javah com.jni.JavaJNI
 * make
 * java com.jni.JavaJNI
 */
public class JavaJNI {
    public int age = 20;

    static{
        System.load("/home/kaige/work/research/learning/java/jdk/basic/src/main/java/javajni.so");
    }
    public static void main(String[] args)
    {
        JavaJNI javaJNI = new JavaJNI();
        javaJNI.printstr();
        javaJNI.printstr("wo shi ben di diao yong ce shi");
        javaJNI.changeAge();
        System.out.println("age after changed by native: " + javaJNI.age);
    }
    //native的作用是申明该方法在本地实现的，与Ｃ或Ｃ＋＋中的方法相对应
    native void printstr();
    native void printstr(String str);
    native void changeAge();
}