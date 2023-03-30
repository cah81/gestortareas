package com.task.manager.task.infrastructure.controller;

import com.task.manager.task.application.service.CreateTaskServicePort;

import com.task.manager.task.infrastructure.dto.TaskInputDto;
import com.task.manager.task.infrastructure.dto.TaskOutPutDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/tasks")
public class CreateTaskController {
    @Autowired
    CreateTaskServicePort service;

    @PostMapping
    public TaskOutPutDto createTask(@RequestBody TaskInputDto taskInputDto){
        return service.createTask(taskInputDto);
    }



}
