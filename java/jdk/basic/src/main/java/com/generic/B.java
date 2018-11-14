package com.generic;

/**
 * 泛型采用的编译器擦除，编译期用Object进行替换。替换后get/set变成了，Object get()/set(Object t)
 *
 * 但在这里我们的get/set的参数为number，所以按理说不能叫做重写，但是实际上就是重写，为啥呢，是因为编译器在编译期间自动给B类补充了一个桥方法，也就是java中的Bridge设计模式。
 *
 * 实际上，如果我们讲这段代码的编译后的代码看一看就会发现，B类中，编译器会帮我们生成两个方法，
 * 一个是：
 *
 *     @Override
 *     public Object get(){
 *         return this.get();//调用返回Number的那个方法
 *     }
 * 还有一个是：
 *
 *     @Override
 *     public void set(Object t ){
 *         this.set((Number)t ); //调用参数是Number的那个方法
 *     }
 */
class B extends A<Number> {
    private Number n;

    @Override
    public Number get() {
        return n;
    }

    @Override
    public void set(Number n) {
        this.n = n;
    }
}