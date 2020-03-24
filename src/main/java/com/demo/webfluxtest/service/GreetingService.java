package com.demo.webfluxtest.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class GreetingService {
    @PreAuthorize("hasRole('Admin')")
    public Mono<String> greet(){
        return Mono.just("Hello from Service!");
    }
}
