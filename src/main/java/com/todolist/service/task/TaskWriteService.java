package com.todolist.service.task;

import com.todolist.domain.Task;
import com.todolist.domain.User;
import com.todolist.dto.TaskDto;

/**
 * @author choijaeyong on 02/04/2019.
 * @project todolist
 * @description
 */
public interface TaskWriteService {

  Task saveTask(TaskDto taskDto, User user);

}
