package com.service;

import com.TestMetric;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class MetricService implements Runnable {
    @Autowired
    public MetricRegistry metricRegistry;

    public static Random random = new Random();

    public void run() {
        Timer timer = metricRegistry.timer(MetricRegistry.name(TestMetric.class, "get-latency"));

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
