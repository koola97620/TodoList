package com.todolist.repositoryTest;

import com.todolist.domain.Board;
import com.todolist.repository.BoardRepository;
import java.util.List;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author choijaeyong on 30/03/2019.
 * @project todolist
 * @description
 */

@RunWith(SpringRunner.class)
@DataJpaTest
@Log
public class BoardTest {

  @Autowired
  BoardRepository boardRepository;

  @Test
  public void 개인Boards출력하기() {
    List<Board> boards = boardRepository.findAllBoardsByUser("todoadmin@gmail.com");
    for(Board board : boards) {
      log.info("==== " + board.getName());
    }

  }

}
