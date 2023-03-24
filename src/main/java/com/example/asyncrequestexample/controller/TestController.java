package com.example.asyncrequestexample.controller;

import com.example.asyncrequestexample.infrastructure.support.concurrent.CustomAsyncTaskExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;
import java.util.concurrent.RejectedExecutionException;

@RestController
@RequestMapping("test")
@RequiredArgsConstructor
public class TestController {
    private final CustomAsyncTaskExecutor customAsyncTaskExecutor;

    @GetMapping
    public Callable<String> error() {
        customAsyncTaskExecutor.setFull(false);
        customAsyncTaskExecutor.setException(false);
        return this::doSomething;
    }

    @GetMapping("full")
    public Callable<String> doubleError() {
        customAsyncTaskExecutor.setFull(true);
        customAsyncTaskExecutor.setString(false);
        return this::doSomething;
    }

    @GetMapping("full/string")
    public Callable<String> doubleErrorString() {
        customAsyncTaskExecutor.setFull(true);
        customAsyncTaskExecutor.setString(true);
        return this::doSomething;
    }

    @GetMapping("timeout")
    public Callable<String> exception() {
        customAsyncTaskExecutor.setFull(false);
        customAsyncTaskExecutor.setException(true);
        return this::doSomething;
    }

    private String doSomething() {
        throw new RejectedExecutionException("get test");
    }
}
