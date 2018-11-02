package jmx;

import javax.management.*;

public class DemoMBeanServerAgent  implements NotificationListener {
    private MBeanServer mbs;

    public DemoMBeanServerAgent() {
        this.mbs = MBeanServerFactory.createMBeanServer("HelloAgent");

        DemoMBeanImp hw = new DemoMBeanImp();
        ObjectName helloWorldName = null;
        try{
            helloWorldName = new ObjectName("HelloAgent:name=helloWorld");
            mbs.registerMBean(hw, helloWorldName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        startHtmlAdaptorServer();
    }

    public void startHtmlAdaptorServer(){
//        HtmlAdaptorServer htmlAdaptorServer = new HtmlAdaptorServer();
//        ObjectName adapterName = null;
//        try {
//            // 多个属性使用,分隔
//            adapterName = new ObjectName("HelloAgent:name=htmladapter,port=9092");
//            htmlAdaptorServer.setPort(9092);
//            mbs.registerMBean(htmlAdaptorServer, adapterName);
//            htmlAdaptorServer.start();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public static void main(String args[]){
        System.out.println(" hello agent is running");
        DemoMBeanServerAgent agent = new DemoMBeanServerAgent();
    }

    @Override
    public void handleNotification(Notification notification, Object handback) {

    }
}
