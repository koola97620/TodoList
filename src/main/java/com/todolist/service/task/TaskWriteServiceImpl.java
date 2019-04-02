package com.todolist.service.task;

import com.todolist.domain.Board;
import com.todolist.domain.Task;
import com.todolist.domain.TaskContent;
import com.todolist.domain.User;
import com.todolist.dto.TaskDto;
import com.todolist.repository.BoardRepository;
import com.todolist.repository.TaskContentRepository;
import com.todolist.repository.TaskRepository;
import com.todolist.repository.UserRepository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author choijaeyong on 02/04/2019.
 * @project todolist
 * @description
 */
@Service
@Log
@RequiredArgsConstructor
public class TaskWriteServiceImpl implements TaskWriteService{

  private final TaskRepository taskRepository;
  private final BoardRepository boardRepository;
  private final TaskContentRepository taskContentRepository;
  private final UserRepository userRepository;

  @Override
  @Transactional
  public Task saveTask(TaskDto taskDto, User user) {
    // 원래는 Controller 에서 전달해주는 session 의 user 를 써야하는데
    // todoadmin@gmail.com 의 샘플데이터로 테스트할 것이기 때문에 일단은 todoadmin@gmail.com User를 불러온다.
    user = getUser();
    log.info("===== user.getEmail() : " + user.getEmail());

    taskDto.setRegisterDate(LocalDateTime.now());
    Task task = StringToLocalDateTime(taskDto);

    log.info("==== task.toString() : " + task.toString());

    String boardName = taskDto.getBoardName();
    Integer priority = Integer.valueOf(taskDto.getPriority());

    Board board = getBoard(boardName, user.getEmail());
    TaskContent taskContent = setTaskContent(taskDto.getContent());

    task = task.builder()
        .board(board)
        .user(user)
        .taskContent(taskContent)
        .title(taskDto.getTitle())
        .priority(priority)
        .build();

    log.info("==== task.toString() : " + task.toString());

    return taskRepository.save(task);
  }

  @Transactional
  public User getUser() {
    return userRepository.getOne("todoadmin@gmail.com");
  }


  private Task StringToLocalDateTime(TaskDto taskDto) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    String regDate = taskDto.getRegisterDate().format(formatter);

    String add = ":00";
    taskDto.setStartDate(taskDto.getStartDate()+add);
    taskDto.setExpireDate(taskDto.getExpireDate()+add);

    log.info("taskDto.getStartDate() : " + taskDto.getStartDate());
    log.info("taskDto.getExpireDate() : " + taskDto.getExpireDate());
    log.info("regDate : " + regDate);

    LocalDateTime registerDateTime = LocalDateTime.parse(regDate,formatter);
    LocalDateTime startDateTime = LocalDateTime.parse(taskDto.getStartDate(), formatter);
    LocalDateTime expireDateTime = LocalDateTime.parse(taskDto.getExpireDate(), formatter);

    Task task = Task.builder()
        .startDate(startDateTime)
        .expireDate(expireDateTime)
        .registerDate(registerDateTime)
        .build();

    return task;
  }


  // 그래도 DB 작업이니 일단 붙여줘보자.
  @Transactional
  public Board getBoard(String boardName, String email) {
    return boardRepository.findBoardByNameAndUser(boardName,email);
  }

  @Transactional
  public TaskContent setTaskContent(String content) {
    TaskContent taskContent = new TaskContent();
    taskContent.setContent(content);
    return taskContentRepository.save(taskContent);
  }

}
