package com.todolist.repositoryTest;

import com.todolist.domain.Task;
import com.todolist.repository.TaskRepository;
import lombok.extern.java.Log;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertThat;

/**
 * @author choijaeyong on 26/03/2019.
 * @project todolist
 * @description
 */


@RunWith(SpringRunner.class)
@DataJpaTest
@Log
public class TaskTest {

  @Autowired
  TaskRepository taskRepository;

  Pageable pageable;

  @Before
  public void pageable생성하기() {
    pageable = PageRequest.of(0,10);
  }


  @Test
  public void 개인모든Board에있는Task출력() {
    Page<Task> tasks = taskRepository.findAllUserTasksFromAllBoard("todoadmin@gmail.com",pageable);
    log.info("tasks.getTotalElements() : " + tasks.getTotalElements());

  }



}
