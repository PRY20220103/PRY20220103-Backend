package com.pry20220103.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Pry20220103BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(Pry20220103BackendApplication.class, args);
    }

}
