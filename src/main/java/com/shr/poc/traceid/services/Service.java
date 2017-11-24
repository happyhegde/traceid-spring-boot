package com.shr.poc.traceid.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Service {

    @Autowired
    private Tracer tracer;

    public String serviceLog() {
        log.info("Service layer: " + Thread.activeCount());
        return "service";
    }

    public String serviceSame(Long t) throws InterruptedException {
//        Thread.sleep(t);
        log.info("Same span service");
        log.info(log.getClass().getName());
        return "Same span ended";
    }

    public String serviceNew() throws InterruptedException {

        log.info("Original span");
        Span newSpan = tracer.createSpan("NewSpan");
        log.info(tracer.getCurrentSpan().getProcessId() + ":" + tracer.getCurrentSpan().getSpanId());

        Thread.sleep(3000L);
        log.info("Doing the work in a different span");
        tracer.close(newSpan);
        log.info("Back to original span");

        return "New span ended";
    }

}
