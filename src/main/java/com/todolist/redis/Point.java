package com.todolist.redis;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import org.apache.tomcat.jni.Local;
import org.springframework.data.redis.core.RedisHash;

/**
 * @author choijaeyong on 28/03/2019.
 * @project todolist
 * @description
 *
 * Redis 객체.
 */

@Getter
@RedisHash("point")
public class Point implements Serializable {

  @Id
  private String id;
  private Long amount;
  private LocalDateTime refreshTime;

  @Builder
  public Point(String id, Long amount, LocalDateTime refreshTime) {
    this.id = id;
    this.amount = amount;
    this.refreshTime = refreshTime;
  }

  public void refresh(long amount, LocalDateTime refreshTime) {
    if(refreshTime.isAfter(this.refreshTime)) { // 저장된 데이터보다 최신경우일경우
      this.amount = amount;
      this.refreshTime = refreshTime;
    }
  }




}
