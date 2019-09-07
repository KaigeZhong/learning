package com;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class ProxyTest {
    public static void main(String[] args) {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, ProxyTest.class.getClassLoader().getResource("").getFile());//将生成的class落盘
        Target target = new Target();
        target.setName("my name");
//        singleCallBackProxy(target);
        mulCallBackProxy(target);

    }

    /**
     * 当Enhancer.setCallbacks(Callback[])设置多个callback时，必须设置CallbackFilter来确保一个方法只能接受一个拦截。
     * 如果未设置，会报错
     *
     * @param target
     */
    private static void mulCallBackProxy(Target target) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Target.class);
        MethodInterceptor methodInterceptor = new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args,
                                    MethodProxy methodProxy) throws Throwable {
                System.out.println(method.getName() + "执行之前做一些准备工作");
                Object result = method.invoke(target, args);
                System.out.println(method.getName() + "执行之后做一些准备的工作");
                return result;
            }
        };
        MethodInterceptor methodInterceptor2 = new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args,
                                    MethodProxy methodProxy) throws Throwable {
                System.out.println(method.getName() + "执行之前做一些准备工作2");
                Object result = method.invoke(target, args);
                System.out.println(method.getName() + "执行之后做一些准备的工作2");
                return result;
            }
        };
        enhancer.setCallbacks(new MethodInterceptor[] {methodInterceptor, methodInterceptor2});
        //设置CallbackFilter
        enhancer.setCallbackFilter((method) ->{
            String methodName = method.getName();
            if ("method2".equals(methodName)) {
                return 1; // eat()方法使用callbacks[1]对象拦截。
            }
            return 0;  //其他方法使用callbacks[0]对象拦截。
        });
        Target proxy = (Target) enhancer.create();
        proxy.method2();
    }

    private static void singleCallBackProxy(Target target) {

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Target.class);
        MethodInterceptor methodInterceptor = new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args,
                                    MethodProxy methodProxy) throws Throwable {
                System.out.println(method.getName() + "执行之前做一些准备工作");
                //走反射
                Object result = method.invoke(target, args);
                //走fast class索引
//                Object result = methodProxy.invoke(target, args);//invoke只能调用target
//                Object result = methodProxy.invokeSuper(obj, args);//invodeSuper只能调用obj也就是生成的代理
                System.out.println(method.getName() + "执行之后做一些准备的工作");
                return result;
            }
        };
        enhancer.setCallback(methodInterceptor);
        Target proxy = (Target) enhancer.create();
        proxy.method1();
    }
}
