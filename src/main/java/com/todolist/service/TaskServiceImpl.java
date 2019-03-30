package com.todolist.service;

import com.todolist.domain.Task;
import com.todolist.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author choijaeyong on 30/03/2019.
 * @project todolist
 * @description
 */

@Service
@Log
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService{

  private final TaskRepository taskRepository;

  @Override
  @Transactional
  public Page<Task> findUserTasksFromPrivateBoard(String userEmail , Pageable pageable) {
    Page<Task> tasks = taskRepository.findUserTasksFromPrivateBoard(userEmail,pageable);
    return tasks;
  }

}
