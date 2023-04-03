package com.task.manager.task.infrastructure.repository;

import com.task.manager.task.domain.Task;
import com.task.manager.task.domain.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    public List<Task> findAllByTaskStatus(TaskStatus status);


    @Modifying
    @Query(value = "UPDATE TASKS SET FINISHED=true WHERE ID=:id",nativeQuery = true)
    public void markTaskAsFinished(Integer id);


}
