package com.jms.producer.demo.controller;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class WebPageController {

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Map<String, String>> streamEvents() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(sequence -> {
                    Map<String, String> data = new HashMap<>();
                    data.put("id", UUID.randomUUID().toString());
                    data.put("message", "Event " + sequence);
                    return data;
                });
    }
}
