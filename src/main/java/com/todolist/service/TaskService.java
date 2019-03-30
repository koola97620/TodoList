package com.todolist.service;

import com.todolist.domain.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

/**
 * @author choijaeyong on 29/03/2019.
 * @project todolist
 * @description
 */
public interface TaskService {

  Page<Task> findUserTasksFromPrivateBoard(String userEmail , Pageable pageable);


}
