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


/*
    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId).get();
        //return UserMapper.mapToUserDto(user);
        //return modelMapper.map(user, UserDto.class);
        return AutoUserMapper.MAPPER.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
//        return users.stream().map(UserMapper::mapToUserDto)
//                .collect(Collectors.toList());

//        return users.stream().map((user) -> modelMapper.map(user, UserDto.class))
//                .collect(Collectors.toList());

        return users.stream().map(AutoUserMapper.MAPPER::mapToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto user) {
        User existingUser = userRepository.findById(user.getId()).get();

        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmailAddress());
        User updatedUser = userRepository.save(existingUser);
        //return UserMapper.mapToUserDto(updatedUser);
        //return modelMapper.map(updatedUser, UserDto.class);
        return AutoUserMapper.MAPPER.mapToUserDto(updatedUser);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }*/
}
