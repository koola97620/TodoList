package com.todolist.dto;

import com.todolist.domain.Task;
import com.todolist.domain.TaskContent;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author choijaeyong on 02/04/2019.
 * @project todolist
 * @description
 */
@Getter
@Setter
public class TaskDto {
  private String boardName;
  private String title;
  private String content;
  private Integer priority;

  private String startDate;
  private String expireDate;

  private LocalDateTime registerDate;
}
