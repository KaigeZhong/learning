package com;

import com.codahale.metrics.*;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TestMetric {
    static final MetricRegistry registry = new MetricRegistry();

    public static void main(String[] args) {
//        testGauge();
//        testCounter();
//        testMeters();
//        testHistogram();
        testTimer();
    }

    static final Queue<String> q = new LinkedList<String>();

    public static void testGauge() {
        ConsoleReporter reporter = ConsoleReporter.forRegistry(registry).build();
        reporter.start(3, TimeUnit.SECONDS);
        registry.register(MetricRegistry.name(TestMetric.class, "queue", "size"),
                new Gauge<Integer>() {

                    public Integer getValue() {
                        return q.size();
                    }
                });

        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            q.add("Job-xxx");
        }
    }


    public static Counter pendingJobs;

    public static Random random = new Random();

    public static void testCounter() {
        MetricRegistry registry = new MetricRegistry();
        ConsoleReporter reporter = ConsoleReporter.forRegistry(registry).build();
        reporter.start(3, TimeUnit.SECONDS);

        pendingJobs = registry.counter(MetricRegistry.name(Queue.class, "pending-jobs", "size"));

        int num = 1;
        while (true) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
            }
            if (random.nextDouble() > 0.7) {
                String job = takeJob();
                System.out.println("take job : " + job);
            } else {
                String job = "Job-" + num;
                addJob(job);
                System.out.println("add job : " + job);
            }
            num++;
        }
    }

    public static void addJob(String job) {
        pendingJobs.inc();
        q.offer(job);
    }

    public static String takeJob() {
        pendingJobs.dec();
        return q.poll();
    }


    public static void testMeters() {
        MetricRegistry registry = new MetricRegistry();
        ConsoleReporter reporter = ConsoleReporter.forRegistry(registry).build();
        reporter.start(3, TimeUnit.SECONDS);

        Meter meterTps = registry.meter(MetricRegistry.name(TestMetric.class, "request", "tps"));

        while (true) {
            request(meterTps, random.nextInt(5));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void request(Meter meter) {
        meter.mark();
    }

    public static void request(Meter meter, int n) {
        while (n > 0) {
            request(meter);
            n--;
        }
    }

    public static void testHistogram() {
        MetricRegistry registry = new MetricRegistry();
        ConsoleReporter reporter = ConsoleReporter.forRegistry(registry).build();
        reporter.start(1, TimeUnit.SECONDS);

        Histogram histogram = new Histogram(new ExponentiallyDecayingReservoir());
        registry.register(MetricRegistry.name(TestMetric.class, "request", "histogram"), histogram);

        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
            histogram.update(random.nextInt(100000));
        }

    }

    public static void testTimer() {
        MetricRegistry registry = new MetricRegistry();
        ConsoleReporter reporter = ConsoleReporter.forRegistry(registry).build();
        reporter.start(1, TimeUnit.SECONDS);

        Timer timer = registry.timer(MetricRegistry.name(TestMetric.class, "get-latency"));

        Timer.Context ctx;

        while (true) {
            ctx = timer.time();
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
            }
            ctx.stop();
        }

    }
}
