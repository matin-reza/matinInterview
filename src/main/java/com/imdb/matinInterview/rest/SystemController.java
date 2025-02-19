package com.imdb.matinInterview.rest;

import com.imdb.matinInterview.config.ReqCountingInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system/health")
@RequiredArgsConstructor
public class SystemController {

    private final ReqCountingInterceptor interceptor;

    @GetMapping
    public ResponseEntity getRequestCount() {
        return ResponseEntity.ok(interceptor.getRequestCount());
    }
}
