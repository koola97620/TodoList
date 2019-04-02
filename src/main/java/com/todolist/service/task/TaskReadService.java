package com.todolist.service.task;

import com.todolist.domain.Task;
import com.todolist.dto.TaskDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

/**
 * @author choijaeyong on 29/03/2019.
 * @project todolist
 * @description
 */
public interface TaskReadService {

  Page<Task> findTasksFromPrivateBoard(String userEmail , Pageable pageable);




}
