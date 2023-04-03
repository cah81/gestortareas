package com.task.manager.task.infrastructure.controller;

import com.task.manager.task.application.service.ListTaskStatusServicePort;
import com.task.manager.task.domain.TaskStatus;
import com.task.manager.task.infrastructure.dto.TaskOutPutDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/status")
public class TaskListStatusController {

    @Autowired
    ListTaskStatusServicePort servicePort;

    @GetMapping("/{status}")
    public List<TaskOutPutDto> taskOutPutDtoList(@PathVariable TaskStatus status){
        return servicePort.findAllByTaskStatus(status);
    }
}
