package com.todolist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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

}
