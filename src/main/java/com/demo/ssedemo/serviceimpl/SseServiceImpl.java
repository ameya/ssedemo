package com.demo.ssedemo.serviceimpl;

import com.demo.ssedemo.service.SseService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ameya Shetti
 */

@Service
public class SseServiceImpl implements SseService {

    private final List<SseEmitter> emitters = new ArrayList<SseEmitter>();

    @Override
    public boolean add(SseEmitter sseEmitter) {
        return this.emitters.add(sseEmitter);
    }

    @Override
    public boolean remove(SseEmitter sseEmitter) {
        return this.emitters.remove(sseEmitter);
    }

    @Override
    public List<SseEmitter> getSsEmitters() {
        return this.emitters;
    }

}
