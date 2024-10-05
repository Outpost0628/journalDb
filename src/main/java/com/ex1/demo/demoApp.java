package com.ex1.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
public class demoApp {
  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(demoApp.class, args);
    ConfigurableEnvironment env1 = context.getEnvironment();
    System.out.println(env1.getActiveProfiles()[0]); // get profileName
  }
}
