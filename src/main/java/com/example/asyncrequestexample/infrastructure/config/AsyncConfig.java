package com.example.asyncrequestexample.infrastructure.config;

import com.example.asyncrequestexample.infrastructure.support.concurrent.CustomAsyncTaskExecutor;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@AllArgsConstructor
public class AsyncConfig implements WebMvcConfigurer {

    private final CustomAsyncTaskExecutor customAsyncTaskExecutor;

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.setTaskExecutor(customAsyncTaskExecutor);
    }
}
