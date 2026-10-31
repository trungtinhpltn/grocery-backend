package com.example.grocery_backend.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class SwaggerLogConfig {

    private final Environment environment;
    public SwaggerLogConfig(Environment environment){this.environment = environment;}

    @EventListener(ApplicationContext.class)
    public void printSwaggerUrl() {
        String port = environment.getProperty("server.port", "8080");
        String contextPath = environment.getProperty("server.serverlet.context-path");
        String url = "http://localhost:" + port + contextPath + "/swagger-ui/index.html";

        System.out.println("\tSwagger UI is ready to use!");
        System.out.println("------------------------------------------------------------\n");

    }
}
