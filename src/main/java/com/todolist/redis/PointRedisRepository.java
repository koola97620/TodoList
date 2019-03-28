package com.todolist.redis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * @author choijaeyong on 28/03/2019.
 * @project todolist
 * @description
 */
public interface PointRedisRepository extends JpaRepository<Point,String> {

}
