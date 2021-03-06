package com.excercise.feed;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.config.EnableIntegration;

@EnableBatchProcessing
@EnableIntegration
@SpringBootApplication
public class ProcessorService
{
    public static void main( String[] args )
    {
        SpringApplication.run(ProcessorService.class, args);
    }
}
