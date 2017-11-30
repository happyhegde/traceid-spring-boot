package com.shr.poc.traceid.controller;

import com.shr.poc.traceid.pojo.Response;
import com.shr.poc.traceid.service.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/test")
public class Controller {

    @Autowired
    private Service service;

    @RequestMapping
    public Response handler() throws Exception {
        log.info("In the controller");
        return new Response(service.serviceLog());
    }
}
