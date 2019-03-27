package com.todolist.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * @author choijaeyong on 25/03/2019.
 * @project todolist
 * @description
 */

@Entity
@Table(name="ROLE")
@Getter
@Setter
public class Role {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

}