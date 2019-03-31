package com.todolist.service;

import com.todolist.domain.Team;
import com.todolist.repository.TeamRepository;
import java.util.List;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

/**
 * @author choijaeyong on 31/03/2019.
 * @project todolist
 * @description
 */
@Service
@Log
public class TeamServiceImpl implements TeamService {
  @Autowired
  TeamRepository teamRepository;

  @Override
  public List<Team> findTeamsOfUser(String userEmail) {
    List<Team> teams = teamRepository.findAllTeamOfUser(userEmail);
    return teams;
  }
}
