package com.example.grocery_backend.config;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class SwaggerLogConfig {
    private final Environment environment;

    public SwaggerLogConfig(Environment environment) {
        this.environment = environment;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void printSwaggerUrl() {
        String port = environment.getProperty("server.port", "8080");
        String contextPath = environment.getProperty("server.servlet.context-path", "");

        String url = "http://localhost:" + port + contextPath + "/swagger-ui/index.html";

        System.out.println("\n------------------------------------------------------------");
        System.out.println("\tSwagger UI is ready to use!");
        System.out.println("\tLocal URL: \t" + url);
        System.out.println("------------------------------------------------------------\n");

    }
}