package com.todolist.config;

import static com.todolist.domain.enums.SocialType.GOOGLE;
import static com.todolist.domain.enums.SocialType.KAKAO;

import com.todolist.oauth2.CustomOAuth2Provider;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * @author choijaeyong on 27/03/2019.
 * @project todolist
 * @description
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


  /*
  @Configuration 으로 등록되어 있는 클래스에서 @Bean으로 등록된 메서드의 파라미터로 지정된 객체들은
  오토와이어링 할 수 있다. OAuth2ClientProperties에는 구글의 정보가 들어있고
  카카오는 따로 등록했기 때문에 @Value 어노테이션을 사용하여 수동으로 불러온다.
   */
  @Bean
  public ClientRegistrationRepository clientRegistrationRepository(
      OAuth2ClientProperties oAuth2ClientProperties, @Value("${custom.oauth2.kakao.client-id}")String kakaoClientId) {

    List<ClientRegistration> registrations = oAuth2ClientProperties.getRegistration().keySet().stream()
        .map(client -> getRegistration(oAuth2ClientProperties, client))
        .filter(Objects::nonNull)
        .collect(Collectors.toList());

    registrations.add(CustomOAuth2Provider.KAKAO.getBuilder("kakao")
      .clientId(kakaoClientId)
      .clientSecret("test")    // 필요없는 값이지만 null이면 실행이 안되므로 임시값 추가.
      .jwkSetUri("test")  // 필요없는 값이지만 null이면 실행이 안되므로 임시값 추가.
      .build());

    return new InMemoryClientRegistrationRepository(registrations);
  }

  private ClientRegistration getRegistration(OAuth2ClientProperties clientProperties, String client) {

    if("google".equals(client)) {
      OAuth2ClientProperties.Registration registration = clientProperties.getRegistration().get("google");
      return CommonOAuth2Provider.GOOGLE.getBuilder(client)
            .clientId(registration.getClientId())
            .clientSecret(registration.getClientSecret())
            .scope("email","profile")
            .build();
    }
    return null;

  }





  @Override
  protected void configure(HttpSecurity http) throws Exception {
    CharacterEncodingFilter filter = new CharacterEncodingFilter();

    http
        // 인증 매커니즘을 요청한 HttpServletRequest 기반으로 설정한다.
        .authorizeRequests()
          //.antMatchers("/","/oauth2/**","/login/**","/css/**","/task/**","/images/**","/js/**","/console/**").permitAll()
          .antMatchers("/","/oauth2/**","/login/**","/task/**").permitAll()
          .antMatchers("/google").hasAuthority(GOOGLE.getRoleType())
          .antMatchers("/kakao").hasAuthority(KAKAO.getRoleType())
//          .antMatchers("/task/**").hasAuthority("ROLE_USER")
          .anyRequest().permitAll()
        .and()
          .oauth2Login()
          .defaultSuccessUrl("/loginSuccess")
          .failureUrl("/loginFailure")
        .and()
          .headers().frameOptions().disable()
        .and()
          .exceptionHandling()
          // 인증의 진입지점. 인증되지 않은 사용자가 허용되지 않은 경로로 요청할 경우 /login으로 이동된다.
          .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))
//        .and()
//          .formLogin()
//          .successForwardUrl("/hello")
        .and()
          .logout()
          .logoutUrl("/logout")
          .logoutSuccessUrl("/")
          .deleteCookies("JSESSIONID")
          .invalidateHttpSession(true)
        .and()
          // filter 보다 csrffilter 을 먼저 실행하게 설정.
          .addFilterBefore(filter, CsrfFilter.class)
          .csrf().disable();


  }





}
