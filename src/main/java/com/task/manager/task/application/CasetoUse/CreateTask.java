package com.task.manager.task.application.CasetoUse;

import com.task.manager.task.application.service.CreateTaskServicePort;
import com.task.manager.task.domain.Task;

import com.task.manager.task.infrastructure.dto.TaskInputDto;
import com.task.manager.task.infrastructure.dto.TaskOutPutDto;
import com.task.manager.task.infrastructure.dto.mapper.AutoTaskMapper;
import com.task.manager.task.infrastructure.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@AllArgsConstructor
public class CreateTask implements CreateTaskServicePort {

    private TaskRepository taskRepository;

    //private ModelMapper modelMapper;
    @Override
    public TaskOutPutDto createTask(TaskInputDto taskInputDto) {

        Task task = AutoTaskMapper.INSTANCE.mapToTaskInputDtoToTask(taskInputDto);

        Task savedTask = taskRepository.save(task);

        return AutoTaskMapper.INSTANCE.mapToTaskToTaskOutputDto(savedTask);
    }


}
