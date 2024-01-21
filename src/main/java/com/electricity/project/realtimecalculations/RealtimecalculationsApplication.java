package com.electricity.project.realtimecalculations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class RealtimecalculationsApplication {

    public static void main(String[] args) {
        SpringApplication.run(RealtimecalculationsApplication.class, args);
    }

}
