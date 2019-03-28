package com.todolist.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.util.StringUtils;
import redis.embedded.RedisServer;

/**
 * @author choijaeyong on 28/03/2019.
 * @project todolist
 * @description
 * 지정된 Redis Port 로 실행중인 프로세스가 있다면, 다른 포트로 내장 Redis를 실행시키고
 * 없다면 지정된 Port로 내장 Redis를 실행시키는 것.
 */

//@PropertySource("classpath:application.properties")
@Log
@Profile("local")
@Configuration
@EnableRedisHttpSession
public class EmbeddedRedisConfig {

  @Value("${spring.redis.port}")
  private int redisPort;

  private RedisServer redisServer;

  @PostConstruct
  public void redisServer() throws IOException {
    int port = isRedisRunning()? findAvailablePort() : redisPort;
    log.info("embeddedRedis start");
    redisServer = new RedisServer(port);
    redisServer.start();
  }

  @PreDestroy
  public void stopRedis() {
    if (redisServer != null) {
      log.info("embeddedRedis stop");
      redisServer.stop();
    }
  }

  /**
      임베디드 Redis 가 현재 실행중인지 확인
   */
  private boolean isRedisRunning() throws IOException {
    return isRunning(executeGrepProcessCommand(redisPort));
  }


  /**
   *  현재 PC/서버에서 사용가능한 포트 조회
   */
  public int findAvailablePort() throws IOException {
    for(int port = 10000; port <= 65535 ; port++){
      Process process = executeGrepProcessCommand(port);
      if(!isRunning(process)) {
        return port;
      }
    }
    throw new IllegalArgumentException("Not Found Available port : 10000 ~ 65535");
  }


  /**
   * 해당 port를 사용중인 프로세스 확인하는 sh 실행.
   */
  private Process executeGrepProcessCommand(int port) throws IOException {
    String command = String.format("netstat -nat | grep LISTEN|grep %d",port);
    String[] shell = {"/bin/sh", "-c", command};
    return Runtime.getRuntime().exec(shell);
  }


  private boolean isRunning(Process process) {
    String line;
    StringBuilder pidInfo = new StringBuilder();

    try(BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
      while((line = input.readLine()) != null) {
        pidInfo.append(line);
      }
    } catch(Exception e) {

    }
    return !StringUtils.isEmpty(pidInfo.toString());
  }










}
