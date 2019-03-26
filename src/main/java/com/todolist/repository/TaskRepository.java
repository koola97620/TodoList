package com.todolist.repository;

import com.todolist.domain.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author choijaeyong on 26/03/2019.
 * @project todolist
 * @description
 */
public interface TaskRepository extends JpaRepository<Task,Long> {

  // 개인의 모든 Board에 있는 Task 리스트 전체 출력
  @Query(value = "SELECT t FROM Task t where t.user.email = (:email)")
  Page<Task> findAllUserTasksFromAllBoard(@Param("email") String userEmail , Pageable pageable);

  // 팀의 모든 Board에 있는 Task 리스트 전체 출력
//  Page<Task> findAllTeamTasks(Pageable pageable);

  // 특정 Board에 있는 Task 리스트 출력


}
