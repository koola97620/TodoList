package com.todolist.repository;

import com.todolist.domain.TaskContent;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author choijaeyong on 26/03/2019.
 * @project todolist
 * @description
 */
public interface TaskContentRepository extends JpaRepository<TaskContent,Long> {

}
