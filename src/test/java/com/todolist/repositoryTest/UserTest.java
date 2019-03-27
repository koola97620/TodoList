package com.todolist.repositoryTest;

import com.todolist.domain.User;
import com.todolist.repository.UserRepository;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author choijaeyong on 27/03/2019.
 * @project todolist
 * @description
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@Log
public class UserTest {
  @Autowired
  UserRepository userRepository;


  @Test
  public void 유저한명찾기() {
    User user = userRepository.findByEmail("todoadmin@gmail.com");
    log.info("user : " + user.getEmail());
  }
}
