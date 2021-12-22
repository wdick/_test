package com.example.demo.web;

import com.example.demo.service.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class DemoResource {

    private static final Logger log = LoggerFactory.getLogger(DemoResource.class);

    private final DemoService service;

    public DemoResource(DemoService service) {
        this.service = service;
    }

    @GetMapping(path = "/count")
    public ResponseEntity<Integer> getCount() {
        log.info("REST request to GET current count");

        int count = service.incAndGetCount();
        log.info("current count: {}", count);

        return ResponseEntity.ok(count);
    }

}
