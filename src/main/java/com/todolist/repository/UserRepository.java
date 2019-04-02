package com.todolist.repository;

import com.todolist.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author choijaeyong on 26/03/2019.
 * @project todolist
 * @description
 */
public interface UserRepository extends JpaRepository<User, String> {

  @Query(value = "SELECT u FROM User u where u.email = (:email)")
  User findByEmail(@Param("email") String email);

}
