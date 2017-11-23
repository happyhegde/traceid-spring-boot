package com.shr.poc.traceid.controller;

import com.shr.poc.traceid.services.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Slf4j
@RestController
@RequestMapping("/log")
public class Controller {

    @Autowired
    private Service service;

    @RequestMapping
    public String handle(HttpServletRequest request) throws Exception {
        Enumeration<String> enums = request.getHeaderNames();
        while (enums.hasMoreElements()) {
            String header = enums.nextElement();
            log.info(header + ":" + request.getHeader(header));
        }
        return service.serviceLog();
    }
}
