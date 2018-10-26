package apache.learning.commons.lang;

import org.apache.commons.lang3.time.StopWatch;

import java.util.Random;

public class StopWatchL {
    public static void main(String[] args) throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Thread.sleep(new Random().nextInt(3) * 1000);
        stopWatch.stop();
        System.out.println("time: " + stopWatch.getTime() / 1000 + "s");
    }
}
