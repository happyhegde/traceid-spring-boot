package com.shr.poc.traceid.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Component
@Slf4j
public class Service {

    @Autowired
    private HttpServletRequest request;

    public String serviceLog() {
        Enumeration<String> enums = request.getHeaderNames();
        while (enums.hasMoreElements()) {
            String header = enums.nextElement();
            log.info(header + ":" + request.getHeader(header));
        }
        return request.getRequestURI();
    }
}
