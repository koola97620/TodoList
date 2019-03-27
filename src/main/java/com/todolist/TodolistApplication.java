package com.todolist;

import com.todolist.resolver.UserArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication
public class TodolistApplication {

  //private static final String PROPERTIES = "spring.config.location=classpath:/google.properties";


  public static void main(String[] args) {
    SpringApplication.run(TodolistApplication.class, args);
//    new SpringApplicationBuilder(TodolistApplication.class)
//        .properties(PROPERTIES)
//        .run(args);
  }


}
