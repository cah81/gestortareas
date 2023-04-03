package com.task.manager.task.application.CasetoUse;

import com.task.manager.exception.EntityNotFoundException;
import com.task.manager.task.application.service.DeleteTaskServicePort;
import com.task.manager.task.domain.Task;
import com.task.manager.task.infrastructure.dto.TaskOutPutDto;
import com.task.manager.task.infrastructure.dto.mapper.AutoTaskMapper;
import com.task.manager.task.infrastructure.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class DeleteTask implements DeleteTaskServicePort {
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void deleteById(Integer id) {
        Optional<Task> optionalTask= taskRepository.findById(id);
        if(optionalTask.isEmpty()){
            throw new EntityNotFoundException("Does not exist",404,new Date());
        }
        taskRepository.deleteById(id);

    }
}
