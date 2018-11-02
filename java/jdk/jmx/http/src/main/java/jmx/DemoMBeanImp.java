package jmx;

public class DemoMBeanImp implements DemoMBean {
    private String greeting;

    public DemoMBeanImp(String greeting) {
        this.greeting = greeting;
    }
    public DemoMBeanImp() {
        this("hello world!");
    }
    public String getGreeting() {
        return greeting;
    }
    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }
    public void printGreeting() {
        System.out.println(greeting);
    }
}
