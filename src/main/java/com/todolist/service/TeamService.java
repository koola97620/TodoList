package com.todolist.service;

import com.todolist.domain.Team;
import java.util.List;

/**
 * @author choijaeyong on 31/03/2019.
 * @project todolist
 * @description
 */
public interface TeamService {
  List<Team> findTeamsOfUser(String userEmail);

}
