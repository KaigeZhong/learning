package com.assertL;

public class AssertL {
    //By default, Java ignores assertions. You can enable assertions by passing the option -ea to the JVM

    /**
     * 1）assert [boolean 表达式]
     *
     * 如果[boolean表达式]为true，则程序继续执行。
     * 如果为false，则程序抛出AssertionError，并终止执行。
     * （2）assert[boolean 表达式 : 错误表达式 （日志）]
     * 如果[boolean表达式]为true，则程序继续执行。
     * 如果为false，则程序抛出java.lang.AssertionError，输出[错误信息]。
     */

    /**
     * 1、assert关键字需要在运行时候显式开启才能生效，否则你的断言就没有任何意义。而现在主流的Java IDE工具默认都没有开启-ea断言检查功能。这就意味着你如果使用IDE工具编码，调试运行时候会有一定的麻烦。并且，对于Java Web应用，程序代码都是部署在容器里面，你没法直接去控制程序的运行，如果一定要开启-ea的开关，则需要更改Web容器的运行配置参数。这对程序的移 植和部署都带来很大的不便。
     *
     * 2、用assert代替if是陷阱之二。assert的判断和if语句差不多，但两者的作用有着本质的区别：assert关键字本意上是为测试 调试程序时使用的，但如果不小心用assert来控制了程序的业务流程，那在测试调试结束后去掉assert关键字就意味着修改了程序的正常的逻辑。
     */
    public static void main(String[] args) {
        assert false : "false";
        System.out.println("done");
    }
}
