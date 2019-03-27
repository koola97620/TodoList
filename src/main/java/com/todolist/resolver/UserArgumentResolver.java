package com.todolist.resolver;

import static com.todolist.domain.enums.SocialType.GOOGLE;
import static com.todolist.domain.enums.SocialType.KAKAO;

import com.todolist.annotation.SocialUser;
import com.todolist.domain.User;
import com.todolist.domain.enums.SocialType;
import com.todolist.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.core.MethodParameter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author choijaeyong on 27/03/2019.
 * @project todolist
 * @description
 */

@Component
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

  private UserRepository userRepository;

  public UserArgumentResolver(UserRepository userRepository) {
    this.userRepository = userRepository;
  }


  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return parameter.getParameterAnnotation(SocialUser.class) != null &&
          parameter.getParameterType().equals(User.class);
  }

  @Override
  public Object resolveArgument(MethodParameter methodParameter,
      ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest,
      WebDataBinderFactory webDataBinderFactory) throws Exception {

    HttpSession session = ((ServletRequestAttributes) RequestContextHolder
        .currentRequestAttributes()).getRequest().getSession();
    User user = (User)session.getAttribute("user");
    return getUser(user,session);
  }


  private User getUser(User user, HttpSession session) {
    if(user==null) {
      try {
        OAuth2AuthenticationToken authentication = (OAuth2AuthenticationToken) SecurityContextHolder
            .getContext().getAuthentication();

        Map<String, Object> map = authentication.getPrincipal().getAttributes();

        User convertUser = convertUser(authentication.getAuthorizedClientRegistrationId(),map);

        user = userRepository.findByEmail(convertUser.getEmail());
        if(user == null) { user = userRepository.save(convertUser);}

        setRoleIfNotSame(user,authentication,map);
        session.setAttribute("user",user);

      } catch(ClassCastException e) {
        return user;
      }
    }
    return user;
  }

  private User convertUser(String autority, Map<String, Object> map) {
    if(GOOGLE.getValue().equals(autority))
      return getModernUser(GOOGLE,map);
    else if(KAKAO.getValue().equals(autority))
      return getKakaoUser(map);

    return null;
  }

  private User getModernUser(SocialType socialType, Map<String,Object> map) {
    System.out.println(map);
    return User.builder()
        .name(String.valueOf(map.get("name")))
        .email(String.valueOf(map.get("email")))
        .principal(String.valueOf(map.get("sub")))
        .socialType(socialType)
        .createdDate(LocalDateTime.now())
        .build();
  }

  private User getKakaoUser(Map<String,Object> map) {
    HashMap<String, String> propertyMap = (HashMap<String,String>)map.get("properties");
    HashMap<String, String> kakaoMap = (HashMap<String,String>)map.get("kakao_account");

    System.out.println("-------------------------------");
    System.out.println(propertyMap);
    System.out.println("-------------------------------");
    System.out.println(kakaoMap);

    return User.builder()
        .name(propertyMap.get("nickname"))
        .email(String.valueOf(kakaoMap.get("email")))
        .principal(String.valueOf(map.get("id")))
        .socialType(KAKAO)
        .createdDate(LocalDateTime.now())
        .build();
  }

  private void setRoleIfNotSame(User user, OAuth2AuthenticationToken authentication , Map<String,Object> map) {
    if(!authentication.getAuthorities().contains(
        new SimpleGrantedAuthority(user.getSocialType().getRoleType()))) {
      SecurityContextHolder.getContext().setAuthentication(new
          UsernamePasswordAuthenticationToken(map,"N/A",
          AuthorityUtils.createAuthorityList(user.getSocialType().getRoleType())));
    }
  }


}