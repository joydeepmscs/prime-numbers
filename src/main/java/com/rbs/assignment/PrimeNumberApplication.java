package com.rbs.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * A Configuration class that can declare one or more @Bean methods and trigger auto-configuration and component scanning.
 * This class launches a Spring Application from Java main method.
 */
@SpringBootApplication
public class PrimeNumberApplication {
    public static void main(String[] args) {
        SpringApplication.run(PrimeNumberApplication.class, args);
    }
}

