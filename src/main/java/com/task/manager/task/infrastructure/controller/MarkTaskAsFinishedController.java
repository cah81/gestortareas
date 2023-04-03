package com.task.manager.task.infrastructure.controller;

import com.task.manager.task.application.service.FindTaskByIdPort;
import com.task.manager.task.application.service.MarkTaskAsFinishedServicePort;

import com.task.manager.task.infrastructure.dto.TaskOutPutDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/markTaskAsFinished/")
@AllArgsConstructor
public class MarkTaskAsFinishedController {
    @Autowired
    private MarkTaskAsFinishedServicePort servicePort;
    @Autowired
    private FindTaskByIdPort findTaskByIdPort;


    @PatchMapping("/{id}")
    public ResponseEntity<TaskOutPutDto> markTaskAsFinished(@PathVariable Integer id) {

        servicePort.updateTaskAsFinished(id);
        TaskOutPutDto taskUpdate = findTaskByIdPort.findById(id);

        return new ResponseEntity<>(taskUpdate, HttpStatus.OK);

    }
}
