package com.todolist.service;

import com.todolist.domain.Board;
import java.util.List;

/**
 * @author choijaeyong on 30/03/2019.
 * @project todolist
 * @description
 */
public interface BoardService {

  List<Board> findAllBoardsByUser(String email);

}
