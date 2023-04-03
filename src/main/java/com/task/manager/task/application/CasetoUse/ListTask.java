package com.task.manager.task.application.CasetoUse;

import com.task.manager.task.application.service.ListTaskServicePort;
import com.task.manager.task.domain.Task;
import com.task.manager.task.infrastructure.dto.TaskOutPutDto;
import com.task.manager.task.infrastructure.dto.mapper.AutoTaskMapper;
import com.task.manager.task.infrastructure.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListTask implements ListTaskServicePort {

    @Autowired
    TaskRepository repository;


    @Override
    public List<TaskOutPutDto> findAll() {
        List<Task> taskList= repository.findAll();
        return taskList.stream().map(AutoTaskMapper.INSTANCE::mapToTaskToTaskOutputDto)
                .collect(Collectors.toList());
    }
}
