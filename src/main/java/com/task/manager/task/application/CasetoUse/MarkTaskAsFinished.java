package com.task.manager.task.application.CasetoUse;

import com.task.manager.exception.EntityNotFoundException;
import com.task.manager.task.application.service.MarkTaskAsFinishedServicePort;
import com.task.manager.task.domain.Task;
import com.task.manager.task.infrastructure.dto.TaskOutPutDto;
import com.task.manager.task.infrastructure.dto.mapper.AutoTaskMapper;
import com.task.manager.task.infrastructure.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

import java.util.Optional;

@Service
public class MarkTaskAsFinished implements MarkTaskAsFinishedServicePort {

    @Autowired
    public TaskRepository repository;



    @Transactional
    public TaskOutPutDto updateTaskAsFinished(Integer id) {
        Optional<Task> optionalTask = repository.findById(id);
        if (optionalTask.isEmpty()) {
            throw new EntityNotFoundException("Task not found", 404, new Date());
        }
        Task task = optionalTask.get();
        task.setFinished(true);
        return  AutoTaskMapper.INSTANCE.mapToTaskToTaskOutputDto(repository.save(task));

    }
}
