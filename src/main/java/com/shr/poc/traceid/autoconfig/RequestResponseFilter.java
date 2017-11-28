package com.shr.poc.traceid.autoconfig;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Configuration
@Slf4j
public class RequestResponseFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            response.addHeader("X-TRACE-ID", UUID.randomUUID().toString());
            MDC.put("logLevelPattern", response.getHeader("X-TRACE-ID"));
            filterChain.doFilter(request, response);
        } finally {
            MDC.clear();
        }
    }
}
