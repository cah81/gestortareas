package com.task.manager.task.application.service;

import com.task.manager.task.domain.TaskStatus;
import com.task.manager.task.infrastructure.dto.TaskOutPutDto;

import java.util.List;

public interface ListTaskStatusServicePort {
    public List<TaskOutPutDto> findAllByTaskStatus(TaskStatus status);
}
