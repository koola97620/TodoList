package com.todolist.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * @author choijaeyong on 25/03/2019.
 * @project todolist
 * @description
 */

@Entity
@Table(name = "USER")
@Getter
@Setter
public class User {

  @Id
  private String email;

  private String name;

  @ManyToMany
  @JoinTable(name = "user_role",
    joinColumns = @JoinColumn(name="user_email", referencedColumnName = "email"),
    inverseJoinColumns = @JoinColumn(name="role_id", referencedColumnName = "id"))
  private Set<Role> roles = new HashSet<>();

  @OneToMany(mappedBy = "maker")
  private Set<Board> boardSet = new HashSet<>();


}
