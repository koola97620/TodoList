package com.todolist.controllerTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @author choijaeyong on 10/04/2019.
 * @project todolist
 * @description
 */

@RunWith(SpringRunner.class)
@WebMvcTest
public class test {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void test() {
    
  }

}
