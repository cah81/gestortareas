package com.task.manager.task.infrastructure.controller;

import com.task.manager.task.application.service.DeleteTaskServicePort;
import com.task.manager.task.application.service.FindTaskByIdPort;
import com.task.manager.task.infrastructure.dto.TaskOutPutDto;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/task")
@AllArgsConstructor
public class DeleteTaskController {

    private FindTaskByIdPort findTaskByIdPort;
    private DeleteTaskServicePort deleteTaskServicePort;

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();
        TaskOutPutDto taskOutPutDtoDelete = findTaskByIdPort.findById(id);
        if (taskOutPutDtoDelete == null) {
            response.put("message", "Does not  exist record  with id:" + id);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
            deleteTaskServicePort.deleteById(id);
            response.put("message", "task has been delete  with exit");
            response.put("task", taskOutPutDtoDelete);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
}
