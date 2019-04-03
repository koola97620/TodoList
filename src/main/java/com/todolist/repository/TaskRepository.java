package com.todolist.repository;

import com.todolist.domain.Task;
import com.todolist.dto.TaskDto;
import com.todolist.dto.TestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;

/**
 * @author choijaeyong on 26/03/2019.
 * @project todolist
 * @description
 */
public interface TaskRepository extends JpaRepository<Task,Long> {

  // 특정 유저가 작성한 개인Board  모든 Task 리스트 출력 - 유저가 작성한 개인board,팀 board에 있는 모든task출력.
  @Query(value = "SELECT t FROM Task t WHERE t.user.email = (:email) ")
  Page<Task> findTasksFromAllBoard(@Param("email") String userEmail , Pageable pageable);

  // 개인board에 작성한 task만 출력. -- 로그인시 메인페이지에서 사용.
  // todoadmin 이 개인 Board에 쓴 글을 읽어올때.1,7번 게시판에서 읽어와야함. ORDER BY t.priority ASC
  @Query(value = "SELECT t FROM Task t WHERE t.board.id IN (SELECT b.id FROM Board b WHERE b.user.email=(:email) AND b.team IS NULL)")
  Page<Task> findTasksFromPrivateBoard(@Param("email") String userEmail , Pageable pageable);



  // 위에 쿼리문은 Task, TaskContent 쿼리가 따로 발생. 이것은 한방에 해결할 수 있다~
  @Query(value="SELECT new com.todolist.dto.TestDto(t.id,t.title,tc.content,t.startDate,t.expireDate,t.priority,t.completed) FROM Task t JOIN FETCH TaskContent tc ON t.taskContent.id = tc.id WHERE t.board.id IN (SELECT b.id FROM Board b WHERE b.user.email=(:email) AND b.team IS NULL)")
  Page<TestDto> findTasksFromPrivateBoardTest(@Param("email") String userEmail, Pageable pageable);




  // 특정 유저가 작성한 특정한 개인 Board의 모든 Task 리스트 출력 (팀x) - 특정 Board 클릭시 사용.
  @Query(value = "SELECT t FROM Task t where t.user.email = (:email) and t.board.id=(:boardId)")
  Page<Task> findTasksFromBoard(@Param("email") String userEmail,@Param("boardId") Long boardId, Pageable pageable);

  // 특정 유저가 작성한 특정한 개인 Board의 모든 Task 리스트 출력 (팀x) - 특정 Board 클릭시 완료 안된것만 출력.
  @Query(value = "SELECT t FROM Task t where t.user.email = (:email) and t.board.id=(:boardId) and t.completed=false")
  Page<Task> findTasksFromBoardNotCompleted(@Param("email") String userEmail,@Param("boardId") Long boardId, Pageable pageable);


  // 팀의 모든 Board에 있는 Task 리스트 전체 출력 - 팀 선택시 제일 먼저 출력될 내용.
  @Query(value = "SELECT t FROM Task t INNER JOIN Board b on t.board.id=b.id where b.team.id = (:teamId)")
  Page<Task> findTeamAllTasks(@Param("teamId") Long teamId, Pageable pageable);


  // 특정 Board에 있는 Task 리스트 출력 - 팀 선택하고 특정 보드 클릭시 나올 내용.
  @Query(value = "SELECT t FROM Task t where t.board.id = (:boardId)")
  Page<Task> findParticularBoardTasks(@Param("boardId") Long boardId, Pageable pageable);




  //void deleteById();
}
