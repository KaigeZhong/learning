package com;

import com.codahale.metrics.MetricRegistry;
import com.service.TimerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Executors;

@SpringBootApplication
@RestController
public class MetricsApp {

    MetricRegistry metricRegistry;

    TimerService metricService;

    @Autowired
    public MetricsApp(MetricRegistry metricRegistry, TimerService metricService) {
        this.metricRegistry = metricRegistry;
        this.metricService = metricService;
        Executors.newSingleThreadExecutor().execute(metricService);
    }
    public static void main(String[] args) {
        SpringApplication.run(MetricsApp.class, args);
    }

    @RequestMapping("/metrics")
    public MetricRegistry getMetricRegistry() {
        return metricRegistry;
    }
}
