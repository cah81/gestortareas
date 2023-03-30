package com.task.manager.task.application.service;

import com.task.manager.task.infrastructure.dto.TaskOutPutDto;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;


public interface MarkTaskAsFinishedServicePort {

    public TaskOutPutDto updateTaskAsFinished(Integer id);
}
