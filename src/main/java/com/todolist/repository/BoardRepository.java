package com.todolist.repository;

import com.todolist.domain.Board;
import com.todolist.domain.Task;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author choijaeyong on 26/03/2019.
 * @project todolist
 * @description
 */
public interface BoardRepository extends JpaRepository<Board,Long> {
  // User 의 Board 목록 불러오기 (No Team)
  @Query(value = "SELECT b FROM Board b where b.user = (:email)")
  List<Task> findAllByUser(@Param("email") String email);

}
