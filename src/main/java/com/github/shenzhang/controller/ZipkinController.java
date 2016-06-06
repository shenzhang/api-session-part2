package com.github.shenzhang.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.google.common.collect.Maps.newHashMap;

/**
 * User: Zhang Shen
 * Date: 5/28/16
 * Time: 10:12 PM.
 */
@RestController
public class ZipkinController {
    private static final Log LOG = LogFactory.getLog(ZipkinController.class);
    private Random random = new Random();

    @Value("${zipkin.service.port}")
    private int port;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private Tracer tracer;

    @RequestMapping(value = "/zipkin", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> zipkin() {
        LOG.info("zipkin");

        restTemplate.getForObject(endpoint("zipkin-service1"), String.class);
        process();
        restTemplate.getForObject(endpoint("zipkin-service2"), String.class);

        Map<String, String> response = newHashMap();
        response.put("status", "SUCCESS");
        return response;
    }

    @RequestMapping(value = "/zipkin-service1", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String zipkinService1() {
        randomSleep();
        randomError();

        return "SUCCESS";
    }

    @RequestMapping(value = "/zipkin-service2", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String zipkinService2() {
        randomSleep();
        randomError();

        return "SUCCESS";
    }

    private void process() {
        Span span = tracer.createSpan("internal-process");
        randomSleep();
        tracer.close(span);
    }

    private String endpoint(String service) {
        return String.format("http://localhost:%s/%s", port, service);
    }

    private void randomSleep() {
        try {
            TimeUnit.MILLISECONDS.sleep(random.nextInt(1000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void randomError() {
        if (random.nextInt(10) < 3) {
//            throw new RuntimeException("Error");
        }
    }
}
