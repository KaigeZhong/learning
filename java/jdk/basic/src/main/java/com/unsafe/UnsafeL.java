package com.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeL {
    public static void main(String[] args) throws Exception {
        Unsafe unsafe = getUnsafe();
        /**
         * park()
         * 第一个参数isAbsolute，false代表相对时间，true代表绝对时间
         * 第二个参数代表过期时间(相对时间是纳秒，绝对时间是毫秒)
         */
        unsafe.park(false, 5000000);
        System.out.println("isAbsolute false");
        unsafe.park(true, System.currentTimeMillis()+2000);
        System.out.println("isAbsolute true");
        /**
         * 永久阻塞
         */
//        unsafe.park(false, 0);

        /**
         * objectFieldOffset() 获取字段的相对偏移量
         * 一个java对象可以看成是一段内存，各个字段都得按照一定的顺序放在这段内存里，同时考虑到对齐要求，可能这些字段不是连续放置的，
         * 用这个方法能准确地告诉你某个字段相对于对象的起始内存地址的字节偏移量，因为是相对偏移量，所以它其实跟某个具体对象又没什么
         * 太大关系，跟class的定义和虚拟机的内存模型的实现细节更相关。
         */
        long parkBlockerOffset = unsafe.objectFieldOffset
                (Thread.class.getDeclaredField("parkBlocker"));
        /**
         * putObject（obj, filedOffset, arg）
         * 通过字段偏移量给obj对象的filed字段赋值为arg
         */
        unsafe.putObject(Thread.currentThread(), parkBlockerOffset, new Object());
    }

    private static Unsafe getUnsafe() throws Exception {
        /*Unsafe.getUnsafe普通的调用的话，它会抛出一个SecurityException异常，可以通过反射获取*/
//        Unsafe unsafe = Unsafe.getUnsafe();
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);
        return unsafe;
    }
}
