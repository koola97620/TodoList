package com.todolist.domain;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * @author choijaeyong on 25/03/2019.
 * @project todolist
 * @description
 */

@Entity
@Table(name="BOARD")
@Getter
@Setter
public class Board {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @ManyToOne
  @JoinColumn(name = "user_email")
  private User maker;

  @ManyToOne
  @JoinColumn(name = "group_id")
  private Group groupId;

  private LocalDateTime registerDate;

}
