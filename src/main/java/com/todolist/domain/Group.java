package com.todolist.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name="GROUP")
@Getter
@Setter
public class Group {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String maker;

  @OneToMany(mappedBy = "groupId")
  private Set<Board> boardSet = new HashSet<Board>();

}
