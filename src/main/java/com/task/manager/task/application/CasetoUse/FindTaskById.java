package com.task.manager.task.application.CasetoUse;

import com.task.manager.exception.EntityNotFoundException;
import com.task.manager.task.application.service.FindTaskByIdPort;
import com.task.manager.task.domain.Task;
import com.task.manager.task.infrastructure.dto.TaskOutPutDto;
import com.task.manager.task.infrastructure.dto.mapper.AutoTaskMapper;
import com.task.manager.task.infrastructure.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class FindTaskById implements FindTaskByIdPort {
    @Autowired
    private TaskRepository repository;
  @Override
   public TaskOutPutDto findById(Integer id) {
       Optional<Task> optionalTask = repository.findById(id);
      if(optionalTask.isEmpty()){
          throw new EntityNotFoundException("Task with id :" + id + "does not exist",404,new Date());
      }
      Task task = optionalTask.get();
       return AutoTaskMapper.INSTANCE.mapToTaskToTaskOutputDto(task);
   }
}
