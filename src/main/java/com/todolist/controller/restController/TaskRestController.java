package com.todolist.controller.restController;

import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author choijaeyong on 02/04/2019.
 * @project todolist
 * @description
 */

@RestController
@Log
public class TaskRestController {

  @PostMapping("/task/update")
  public ResponseEntity taskUpdate() {


    return null;
  }


}
