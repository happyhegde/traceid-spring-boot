package com.shr.poc.traceid.config;

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

    /**
     * The doFilterInternal method takes every incoming http request
     * and adds a filter to it
     * Here the filter is applied to the ensure that
     * the response has the new trace id, which is a random UUID
     * The user / developer will be able to access "X-TRACE-ID",
     * which will be present in the response headers
     * The X-TRACE-ID generated will be put into MDC "xtraceid",
     * which will help modify the logs to include trace id
     *
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            // adding x-trace-id to the response headers
            response.addHeader("X-TRACE-ID", UUID.randomUUID().toString());

            // replacing xtraceid in MDC with the trace-id of each request
            MDC.put("xtraceid", response.getHeader("X-TRACE-ID"));

            // logging each API call
            log.info("API call: " + request.getMethod() + ": " + request.getRequestURI());

            // executing the request once the headers and trace-id modifications are done
            filterChain.doFilter(request, response);
        } finally {
            // clearing the MDC once the request is complete
            MDC.clear();
        }
    }

}
