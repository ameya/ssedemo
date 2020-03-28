package com.demo.ssedemo.web.rest;

import com.demo.ssedemo.serviceimpl.SseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/sse/")
public class SseEmitterResource {

    @Autowired
    private SseServiceImpl sseService;

    @GetMapping("subscription")
    public SseEmitter subscribe() throws IOException {

        SseEmitter emitter = new SseEmitter();

        sseService.add(emitter);
        emitter.onCompletion(() -> sseService.remove(emitter));

        return emitter;
    }

    @GetMapping("producer/test/{data}")
    public String produce(@PathVariable String data) {

        sseService.getSsEmitters().forEach((SseEmitter emitter) -> {
            try {
                emitter.send(data, MediaType.APPLICATION_JSON);
            } catch (IOException e) {
                emitter.complete();
                sseService.remove(emitter);
                e.printStackTrace();
            }
        });
        return data;
    }
}
