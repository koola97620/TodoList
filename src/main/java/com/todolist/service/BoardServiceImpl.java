package com.todolist.service;

import com.todolist.domain.Board;
import com.todolist.repository.BoardRepository;
import java.util.List;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author choijaeyong on 30/03/2019.
 * @project todolist
 * @description
 */
@Service
@Log
public class BoardServiceImpl implements BoardService{

  @Autowired
  BoardRepository boardRepository;


  @Override
  @Transactional
  public List<Board> findAllBoardsByUser(String email) {
    List<Board> boardList = boardRepository.findAllBoardsByUser(email);

    return boardList;
  }

}
