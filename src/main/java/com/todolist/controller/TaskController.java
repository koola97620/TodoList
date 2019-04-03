package com.todolist.controller;

import com.todolist.domain.Board;
import com.todolist.domain.Task;
import com.todolist.domain.User;
import com.todolist.dto.TaskDto;
import com.todolist.service.BoardService;
import com.todolist.service.task.TaskReadService;
import com.todolist.service.task.TaskWriteService;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author choijaeyong on 02/04/2019.
 * @project todolist
 * @description
 */

@Controller
@Log
@RequiredArgsConstructor
public class TaskController {

  private final BoardService boardService;
  private final TaskReadService taskReadService;
  private final TaskWriteService taskWriteService;

  @GetMapping("/task/writeForm")
  public String taskForm(Model model, TaskDto taskDto) {
//    User user = (User)session.getAttribute("user");
//    model.addAttribute("user",user);

    List<Board> boardList = boardService.findAllBoardsByUser("todoadmin@gmail.com");

    model.addAttribute("taskDto", taskDto);
    model.addAttribute("boardList", boardList);

    return "task/writeForm";
  }

  @PostMapping("/task/write")
  public String taskWrite(@ModelAttribute(value = "taskDto") TaskDto taskDto, HttpSession session) {
    // @Valid, BindingResult 나중에.
    User user = (User)session.getAttribute("user");

    Task newTask = taskWriteService.saveTask(taskDto,user);

    return "redirect:/main";
  }

}
