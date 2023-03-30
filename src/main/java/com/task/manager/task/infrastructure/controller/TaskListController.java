package com.task.manager.task.infrastructure.controller;

import com.task.manager.task.application.service.ListTaskServicePort;
import com.task.manager.task.infrastructure.dto.TaskOutPutDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskListController {

    @Autowired
    ListTaskServicePort servicePort;
    @GetMapping
    public List<TaskOutPutDto> taskList(){
        return servicePort.findAll();
    }
}
