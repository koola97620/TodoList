package com.todolist.controller;

import com.todolist.domain.User;
import javax.servlet.http.HttpSession;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author choijaeyong on 29/03/2019.
 * @project todolist
 * @description
 */

@Controller
@Log
public class MainController {

  @GetMapping("/userMain")
  public String userMain(HttpSession session, Model model) {

    User user = (User)session.getAttribute("user");
    log.info("=== user.getName() : " + user.getName());
    log.info("=== user.getEmail() : " + user.getEmail());

    model.addAttribute("userName",user.getName());
    model.addAttribute("userEmail",user.getEmail());
    // 이 유저 정보를 가지고 Board, Task 목록 출력하기.

    return "user/main";
  }

}
