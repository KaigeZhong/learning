package com.service;

import com.TestMetric;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Queue;

@Component
public class GaugeService implements Runnable {
    @Autowired
    public MetricRegistry metricRegistry;

    static final Queue<String> q = new LinkedList<String>();
    public void run() {
        metricRegistry.register(MetricRegistry.name(TestMetric.class, "queue", "size"),
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
}
