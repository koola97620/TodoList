package com.todolist.repositoryTest;

import com.todolist.domain.Team;
import com.todolist.repository.TeamRepository;
import java.util.List;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author choijaeyong on 31/03/2019.
 * @project todolist
 * @description
 */

@RunWith(SpringRunner.class)
@DataJpaTest
@Log
public class TeamTest {
  @Autowired
  TeamRepository teamRepository;

  @Test
  public void 유저의팀목록가져오기() {
    List<Team> teamList = teamRepository.findAllTeamOfUser("todoadmin@gmail.com");
    log.info("===== teamList.getClass().getName() : " + teamList.getClass().getName());
    for(Team team : teamList) {
      log.info("id : " + team.getId() + "  name : " + team.getName());
    }
  }

}
