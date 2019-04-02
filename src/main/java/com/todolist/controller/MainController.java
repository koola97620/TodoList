package com.todolist.controller;

import com.todolist.domain.Board;
import com.todolist.domain.Task;
import com.todolist.domain.Team;
import com.todolist.domain.User;
import com.todolist.service.BoardService;
import com.todolist.service.task.TaskReadService;
import com.todolist.service.TeamService;
import java.util.List;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
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
@RequiredArgsConstructor
public class MainController {

  private final BoardService boardService;
  private final TaskReadService taskReadService;
  private final TeamService teamService;


  @GetMapping("/main")
  public String userMain(HttpSession session, Model model,
      @PageableDefault(sort="priority",direction = Direction.ASC) Pageable pageable) {

    User user = (User)session.getAttribute("user");
    log.info("=== user.getName() : " + user.getName());
    log.info("=== user.getEmail() : " + user.getEmail());

    // 테스트할땐 user.getEmail() 대신 "todoadmin@gmail.com"
    List<Board> boardList = boardService.findAllBoardsByUser("todoadmin@gmail.com");
    Page<Task> tasks = taskReadService.findTasksFromPrivateBoard("todoadmin@gmail.com",pageable);
    List<Team> teams = teamService.findTeamsOfUser("todoadmin@gmail.com");

//    List<Board> boardList = boardService.findAllBoardsByUser(user.getEmail());
//    Page<Task> tasks = taskReadService.findUserTasksFromPrivateBoard(user.getEmail(),pageable);
//    List<Team> teams = teamService.findTeamsOfUser(user.getEmail());



    log.info("==========  boardList.size() : " + boardList.size());
    log.info("==========  tasks.getTotalElements() : " + tasks.getTotalElements());

    model.addAttribute("user",user);
    model.addAttribute("boardList", boardList);
    model.addAttribute("tasks",tasks);
    model.addAttribute("teamList",teams);
//    model.addAttribute("userName",user.getName());
//    model.addAttribute("userEmail",user.getEmail());
    // 이 유저 정보를 가지고 Board, Task 목록 출력하기.

    return "main";
  }

}
