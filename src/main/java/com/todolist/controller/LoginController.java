package com.todolist.controller;

import com.todolist.annotation.SocialUser;
import com.todolist.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author choijaeyong on 27/03/2019.
 * @project todolist
 * @description
 */

@Controller
public class LoginController {

  @GetMapping("/login")
  public String login() {
    return "login";
  }

  @GetMapping("/loginSuccess")
  public String loginComplete(@SocialUser User user) {
    return "redirect:/hello";
  }

//  @GetMapping(value = "/{google|kakao}/complete")
//  public String loginComplete


}
