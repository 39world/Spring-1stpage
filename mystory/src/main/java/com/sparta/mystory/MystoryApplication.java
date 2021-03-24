package com.sparta.mystory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MystoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(MystoryApplication.class, args);
    }

}
