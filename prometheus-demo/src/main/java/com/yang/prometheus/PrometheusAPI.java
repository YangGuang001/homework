package com.yang.prometheus;

import io.prometheus.client.Counter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class PrometheusAPI {
    private static final Random random = new Random(System.currentTimeMillis());

    private static final Counter counter = Counter.build()
            .name("demo")
            .namespace("Prometheus")
            .labelNames("status")
            .help("http status")
            .register();


    @GetMapping("/prometheusapi")
    public void request() {
        if (0 == random.nextInt() % 2) {
            counter.inc();
        }
    }

}
