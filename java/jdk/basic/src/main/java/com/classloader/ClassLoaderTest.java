package com.classloader;


public class ClassLoaderTest {

  public static void main(String[] args) throws ClassNotFoundException {

    FileClassLoader loader1 = new FileClassLoader("");

    System.out.println("自定义类加载器的父加载器: "+loader1.getParent());
    System.out.println("系统默认的AppClassLoader: "+ClassLoader.getSystemClassLoader());
    System.out.println("AppClassLoader的父类加载器: "+ClassLoader.getSystemClassLoader().getParent());
    System.out.println("ExtClassLoader的父类加载器: "+ClassLoader.getSystemClassLoader().getParent().getParent());

    /**
     输出结果:
     自定义类加载器的父加载器: sun.misc.Launcher$AppClassLoader@29453f44
     系统默认的AppClassLoader: sun.misc.Launcher$AppClassLoader@29453f44
     AppClassLoader的父类加载器: sun.misc.Launcher$ExtClassLoader@6f94fa3e
     ExtClassLoader的父类加载器: null
     */

  }

  static class FileClassLoader extends  ClassLoader{
    private String rootDir;

    public FileClassLoader(String rootDir) {
      super(null);
      this.rootDir = rootDir;
    }
    // 编写获取类的字节码并创建class对象的逻辑
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
      //...省略逻辑代码
      return null;
    }
    //编写读取字节流的方法
    private byte[] getClassData(String className) {
      // 读取类文件的字节
      //省略代码....
      return new byte[0];
    }
  }
}
