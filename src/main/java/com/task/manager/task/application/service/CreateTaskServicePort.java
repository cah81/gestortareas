package com.task.manager.task.application.service;
import com.task.manager.task.infrastructure.dto.TaskInputDto;
import com.task.manager.task.infrastructure.dto.TaskOutPutDto;


public interface CreateTaskServicePort {
    public TaskOutPutDto createTask(TaskInputDto taskInputDto);




}
