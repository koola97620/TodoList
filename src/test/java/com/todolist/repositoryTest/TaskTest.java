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
  public void 개인Board팀Board에있는모든Task출력() {
    Page<Task> tasks = taskRepository.findTasksFromAllBoard("todoadmin@gmail.com",pageable);
    for(Task task : tasks) {
      log.info("id : " + task.getId() + "  priority : " + task.getPriority() +"   title : " + task.getTitle() + "  content : " + task.getTaskContent().getContent());
    }
  }

  @Test
  public void 개인Boards에있는Task출력() {
    Page<Task> tasks = taskRepository
        .findTasksFromPrivateBoard("todoadmin@gmail.com",pageable);
    for(Task task : tasks) {
      log.info("id : " + task.getId() + "  priority : " + task.getPriority() +"   title : " + task.getTitle() + "  content : " + task.getTaskContent().getContent());
    }
  }


  @Test
  public void 개인특정Board에있는모든Task출력() {
    Page<Task> tasks = taskRepository.findTasksFromBoard("todoadmin@gmail.com",1l,pageable);
    for(Task task : tasks) {
      log.info("id : " + task.getId() + "   title : " + task.getTitle() + "  completed : " + task.getCompleted() +"  content : " + task.getTaskContent().getContent());
    }
  }

  @Test
  public void 개인특정Board에있는미완료Task출력() {
    Page<Task> tasks = taskRepository
        .findTasksFromBoardNotCompleted("todoadmin@gmail.com",1l,pageable);
    for(Task task : tasks) {
      log.info("id : " + task.getId() + "   title : " + task.getTitle() + "  completed : " + task.getCompleted() +"  content : " + task.getTaskContent().getContent());
    }
  }

  @Test
  public void 팀의모든Task출력() {
    Page<Task> tasks = taskRepository.findTeamAllTasks(1l,pageable);
    for(Task task : tasks) {
      log.info("id : " + task.getId() + "   title : " + task.getTitle());
    }
  }

  @Test
  public void 특정Board클릭시Task출력() {
    Page<Task> tasks = taskRepository.findParticularBoardTasks(8l,pageable);
    for(Task task : tasks) {
      log.info("id : " + task.getId() + "   title : " + task.getTitle());
    }

    // 삭제 테스트 (성공)
    taskRepository.deleteById(11l);

    tasks = taskRepository.findParticularBoardTasks(8l,pageable);
    for(Task task : tasks) {
      log.info("id : " + task.getId() + "   title : " + task.getTitle());
    }


  }






}
