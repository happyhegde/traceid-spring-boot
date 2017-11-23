package com.shr.poc.traceid.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Service {

    public String serviceLog() {
        log.info("Service layer: " + Thread.activeCount());
        return "service";
    }

}
