package com.task.manager.task.infrastructure.controller;

import com.task.manager.exception.EntityNotFoundException;
import com.task.manager.task.application.service.CreateTaskServicePort;
import com.task.manager.task.application.service.FindTaskByIdPort;
import com.task.manager.task.application.service.MarkTaskAsFinishedServicePort;

import com.task.manager.task.infrastructure.dto.TaskOutPutDto;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/markTaskAsFinished/")
@AllArgsConstructor
public class MarkTaskAsFinishedController {

    private MarkTaskAsFinishedServicePort servicePort;

    private FindTaskByIdPort findTaskByIdPort;

    private CreateTaskServicePort createTaskServicePort;



    @PatchMapping("/{id}")
    public ResponseEntity<?> markTaskAsFinished(@PathVariable Integer  id){
        TaskOutPutDto taskOutPutDto = findByIdIntoDataBase(id);
        Map<String,Object>  response =new HashMap<>();
        try{
            servicePort.updateTaskAsFinished(id);
        }catch (DataAccessException  e){
            response.put("message", "Error with the database");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        TaskOutPutDto taskUpdate = findByIdIntoDataBase(id);
        response.put("message","task has been update to finished with exit");
        response.put("task",taskUpdate);
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
    }



    private TaskOutPutDto findByIdIntoDataBase(Integer id){
        TaskOutPutDto taskOutPutDtoUpdate = findTaskByIdPort.findById(id);

        if(taskOutPutDtoUpdate==null){
           throw new EntityNotFoundException("Does not exist",404,new Date());

        }
        return taskOutPutDtoUpdate;
    }
}
