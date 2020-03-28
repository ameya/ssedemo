package com.demo.ssedemo.service;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

/**
 * @author Ameya Shetti
 */

public interface SseService {

    boolean add(SseEmitter sseEmitter);

    boolean remove(SseEmitter sseEmitter);

    List<SseEmitter> getSsEmitters();

}
