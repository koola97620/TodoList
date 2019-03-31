package com.todolist.repository;

import com.todolist.domain.Team;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author choijaeyong on 26/03/2019.
 * @project todolist
 * @description
 */
public interface TeamRepository extends JpaRepository<Team,Long> {


  @Query(value = "SELECT t from Team t LEFT OUTER JOIN TeamUser tu ON t.id=tu.team.id WHERE tu.user.email=(:email)")
  List<Team> findAllTeamOfUser(@Param("email") String userEmail);

}
