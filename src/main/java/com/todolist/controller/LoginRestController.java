package com.todolist.controller;

import java.lang.annotation.Retention;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author choijaeyong on 27/03/2019.
 * @project todolist
 * @description
 */

@RestController
public class LoginRestController {

  @GetMapping("/google")
  public String google() {
    return "google";
  }

  @GetMapping("/kakao")
  public String kakao() {
    return "kakao";
  }

}
