package com.shr.poc.traceid.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@Slf4j
public class Service {

    @Autowired
    private HttpServletRequest request;

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

    public String serviceNew() throws Exception {

        log.info("Original span");
        log.info("Auth type: " + request.getPathInfo());
        log.info("Context pah: " + request.getContextPath());
        log.info("Servlet path: " + request.getServletPath());

        return "New span ended";
    }

}
