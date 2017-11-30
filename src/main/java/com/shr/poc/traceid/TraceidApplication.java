package com.shr.poc.traceid;

import com.shr.poc.traceid.pojo.Response;
import com.shr.poc.traceid.service.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@Slf4j
@RestController
@RequestMapping("/test")
public class TraceidApplication {

    @Autowired
    private Service service;

    public static void main(String[] args) {

        SpringApplication.run(TraceidApplication.class, args);
    }

    @RequestMapping
    public Response handler() throws Exception {
        log.info("In the controller");
        return new Response(service.serviceLog());
    }
}
