package com.task.manager.task.infrastructure.dto.mapper;
import java.time.LocalDateTime;
import com.task.manager.task.domain.Task;
import com.task.manager.task.domain.TaskStatus;

import com.task.manager.task.infrastructure.dto.TaskInputDto;
import com.task.manager.task.infrastructure.dto.TaskOutPutDto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(imports = LocalDateTime.class)
public interface AutoTaskMapper {

    AutoTaskMapper INSTANCE = Mappers.getMapper(AutoTaskMapper.class);

    default Task mapToTaskInputDtoToTask(TaskInputDto user) {
        return Task
                .builder()
                .title(user.getTitle())
                .description(user.getDescription())
                .createdDate(LocalDateTime.now())
                .eta(user.getEta())
                .finished(false)
                .taskStatus(TaskStatus.ON_TIME)
                .build();
    }

    TaskOutPutDto mapToTaskToTaskOutputDto(Task task);


}
