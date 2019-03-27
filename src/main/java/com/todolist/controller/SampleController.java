package com.todolist.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author choijaeyong on 25/03/2019.
 * @project todolist
 * @description
 */

@Controller
public class SampleController {

  @GetMapping("/hello")
  public String hell() {
    return "index";
  }

//  @RequestMapping("/")
//  public String login() {
//    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//    System.out.println(auth.getPrincipal());
//    return "/index";
//  }
//
//  @RequestMapping("/callback")
//  public String callback() {
//    System.out.println("redirecting to home page");
//    return "/home";
//  }



}
