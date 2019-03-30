package com.todolist.redisTest;

import static org.assertj.core.api.Assertions.assertThat;

import com.todolist.redis.Point;
import com.todolist.redis.PointRedisRepository;
import java.time.LocalDateTime;
import lombok.extern.java.Log;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author choijaeyong on 28/03/2019.
 * @project todolist
 * @description
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Log
@ComponentScan("com.todolist.redis")
public class RedisTest {

  @Autowired
  private PointRedisRepository pointRedisRepository;

  @After
  public void tearDown() throws Exception{
    pointRedisRepository.deleteAll();
  }


  @Test
  public void 기본등록조회기능() {
    // given
    String id = "jdragon";
    LocalDateTime refreshTime = LocalDateTime.of(2019,3,28,0,0);
    Point point = Point.builder()
        .id(id)
        .amount(1000l)
        .refreshTime(refreshTime)
        .build();

    // when
    pointRedisRepository.save(point);

    log.info("==================== point.getAmount() : " + point.getAmount());



    // then
    Point savedPoint = pointRedisRepository.findById(id).get();
    assertThat(savedPoint.getAmount()).isEqualTo(1000l);
    assertThat(savedPoint.getRefreshTime()).isEqualTo(refreshTime);
  }


  @Test
  public void 수정기능() {
    // given
    String id = "jdragon";
    LocalDateTime refreshTime = LocalDateTime.of(2019, 3, 26, 0, 0);
    pointRedisRepository.save(Point.builder()
        .id(id)
        .amount(1000L)
        .refreshTime(refreshTime)
        .build());

    //when
    Point savedPoint = pointRedisRepository.findById(id).get();
    savedPoint.refresh(2000L, LocalDateTime.of(2019,3,28,0,0));
    pointRedisRepository.save(savedPoint);

    //then
    Point refreshPoint = pointRedisRepository.findById(id).get();
    assertThat(refreshPoint.getAmount()).isEqualTo(2000L);
  }



}
