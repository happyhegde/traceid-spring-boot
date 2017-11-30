package com.shr.poc.traceid.controller;

import com.shr.poc.traceid.Response;
import com.shr.poc.traceid.service.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Slf4j
@RestController
@RequestMapping("/test")
public class Controller {

    @Autowired
    private Service service;

    @RequestMapping
    public Response handler() throws Exception {
        return new Response(service.serviceLog());
    }
}
