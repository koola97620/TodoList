package com.todolist.dto;

import com.todolist.domain.Task;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author choijaeyong on 03/04/2019.
 * @project todolist
 * @description
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestDto {
  private Long taskId;
  private String title;
  private String content;
  private LocalDateTime startDate;
  private LocalDateTime expireDate;
  private Integer priority;
  private Boolean completed;

}
