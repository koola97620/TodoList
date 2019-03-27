package com.todolist.config;

import com.todolist.domain.User;
import com.todolist.repository.UserRepository;
import com.todolist.resolver.UserArgumentResolver;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author choijaeyong on 27/03/2019.
 * @project todolist
 * @description
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {
  @Autowired
  private UserArgumentResolver userArgumentResolver;

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    resolvers.add(userArgumentResolver);
  }



  // was 실행하고 아래 값을 db에 insert 해주네?
  @Bean
  public CommandLineRunner runner(UserRepository userRepository) throws Exception {
    return (args -> {
      User user = userRepository.save(User.builder()
        .name("havi")
        .password("test")
        .email("havi@gmail.com")
        .createdDate(LocalDateTime.now())
        .build());
    });
  }

}
