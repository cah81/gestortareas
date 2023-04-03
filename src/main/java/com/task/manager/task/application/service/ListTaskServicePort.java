package com.task.manager.task.application.service;

import com.task.manager.task.infrastructure.dto.TaskOutPutDto;

import java.util.List;

public interface ListTaskServicePort {
    public List<TaskOutPutDto> findAll();
}
