package com.todolist.controller;

import com.todolist.annotation.SocialUser;
import com.todolist.domain.Role;
import com.todolist.domain.User;
import com.todolist.oauth2.SecurityUser;
import java.util.Map;
import javax.servlet.http.HttpSession;
import lombok.extern.java.Log;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author choijaeyong on 27/03/2019.
 * @project todolist
 * @description
 */

@Controller
@Log
public class LoginController {

  @GetMapping("/login")
  public String login() {
    return "login";
  }


  /**
    @SocialUser 가 붙은 메소드가 있어야 Argument에 있는 로직이 실행된다.
   */
  @GetMapping("/loginSuccess")
  public String loginComplete(@SocialUser User user) {
    log.info("========== loginComplete ========");
    log.info("user.getEmail() : " + user.getEmail());
    log.info("user.getName() : " + user.getName());
    log.info("user.getPrincipal(): " + user.getPrincipal());



    return "redirect:/main";
  }

  @GetMapping("/afterlogin")
  public String afterLogin(HttpSession session) {
    log.info("========== afterLogin =======");
//    User user = (User)authentication.getPrincipal();  not cast
//    OAuth2AuthenticationToken authentication = (OAuth2AuthenticationToken) SecurityContextHolder
//        .getContext().getAuthentication();
//    Map<String, Object> map = authentication.getPrincipal().getAttributes();

    SecurityUser authuser= null;
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if(authentication != null) {
      log.info("authentication is not null");
      //authuser = (SecurityUser)authentication.getPrincipal();
    }
//    log.info("==== authuser  : " + authuser.getEmail());

    log.info("==============");

//    User user = (User)session.getAttribute("user");
//    System.out.println("====== session 에서 가져와지니? " + user.getName());
//    System.out.println("====== user.getRoleSet() : ");
//    for(Role role : user.getRoleSet()) {
//      System.out.print("--" + role.getName() + "  ");
//    }


    User sessionUser = (User)session.getAttribute("user");
    log.info("==== sessionUser.getName() : " + sessionUser.getName());




    return "afterLogin";
  }

//  @GetMapping(value = "/{google|kakao}/complete")
//  public String loginComplete


}
