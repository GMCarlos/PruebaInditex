package com.inditex.prueba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.inditex.prueba")
@EnableJpaRepositories
@Configuration
public class PruebaApplication {

  public static void main(String[] args) {
    SpringApplication.run(PruebaApplication.class, args);
  }
}
