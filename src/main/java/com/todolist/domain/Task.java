package com.todolist.domain;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * @author choijaeyong on 25/03/2019.
 * @project todolist
 * @description
 */

@Entity
@Table(name="TASK")
@Getter
@Setter
public class Task {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="user")
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="board")
  private Board board;

//  @ManyToOne(fetch = FetchType.LAZY)
//  @JoinColumn(name="team_id")
//  private Team team;

  private LocalDateTime registerDate;

  private LocalDateTime startDate;
  private LocalDateTime expireDate;

  private Integer priority;

  private Boolean completed;

  private String title;

  @OneToOne
  @JoinColumn(name="task_content_id")
  private TaskContent taskContent;



}
