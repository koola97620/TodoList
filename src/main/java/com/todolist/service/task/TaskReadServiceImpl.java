package com.todolist.service.task;

import com.todolist.domain.Task;
import com.todolist.dto.TaskDto;
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
public class TaskReadServiceImpl implements TaskReadService {

  private final TaskRepository taskRepository;

  @Override
  @Transactional
  public Page<Task> findTasksFromPrivateBoard(String userEmail , Pageable pageable) {
    Page<Task> tasks = taskRepository.findTasksFromPrivateBoard(userEmail,pageable);
    return tasks;
  }


}
