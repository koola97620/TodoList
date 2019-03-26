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

  // 특정 유저가 작성한 개인Board , 팀 Board에 있는 모든 Task 리스트 출력 - 로그인시 메인페이지에 사용
  @Query(value = "SELECT t FROM Task t where t.user.email = (:email)")
  Page<Task> findUserTasksFromAllBoard(@Param("email") String userEmail , Pageable pageable);

  // 특정 유저가 작성한 특정한 개인 Board의 모든 Task 리스트 출력 (팀x) - 특정 Board 클릭시 사용.
  @Query(value = "SELECT t FROM Task t where t.user.email = (:email) and t.board.id=(:boardId)")
  Page<Task> findUserTasksFromBoard(@Param("email") String userEmail,@Param("boardId") Long boardId, Pageable pageable);

  // 특정 유저가 작성한 특정한 개인 Board의 모든 Task 리스트 출력 (팀x) - 특정 Board 클릭시 완료 안된것만 출력.
  @Query(value = "SELECT t FROM Task t where t.user.email = (:email) and t.board.id=(:boardId) and t.completed=false")
  Page<Task> findUserTasksFromBoardNotCompleted(@Param("email") String userEmail,@Param("boardId") Long boardId, Pageable pageable);


  // 팀의 모든 Board에 있는 Task 리스트 전체 출력 - 팀 선택시 제일 먼저 출력될 내용.
  @Query(value = "SELECT t FROM Task t INNER JOIN Board b on t.board.id=b.id where b.team.id = (:teamId)")
  Page<Task> findTeamAllTasks(@Param("teamId") Long teamId, Pageable pageable);


  // 특정 Board에 있는 Task 리스트 출력 - 팀 선택하고 특정 보드 클릭시 나올 내용.
  @Query(value = "SELECT t FROM Task t where t.board.id = (:boardId)")
  Page<Task> findParticularBoardTasks(@Param("boardId") Long boardId, Pageable pageable);


  //void deleteById();
}
