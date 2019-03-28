package com.todolist.oauth2;

import java.io.Serializable;
import java.util.Collection;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * @author choijaeyong on 28/03/2019.
 * @project todolist
 * @description
 */
@Getter
@Setter
public class SecurityUser extends User implements Serializable {

  private String nickname;
  private String email;
  private com.todolist.domain.User user;

  public SecurityUser(String username, String password,
      Collection<? extends GrantedAuthority> authorities,
      String nickname, String email, com.todolist.domain.User user) {
    super(username, password, authorities);
    this.nickname = nickname;
    this.email = email;
    this.user = user;
  }
}
