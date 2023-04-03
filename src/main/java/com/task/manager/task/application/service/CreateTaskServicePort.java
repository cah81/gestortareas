package com.task.manager.task.application.service;
import com.task.manager.task.infrastructure.dto.TaskInputDto;
import com.task.manager.task.infrastructure.dto.TaskOutPutDto;


public interface CreateTaskServicePort {
    public TaskOutPutDto createTask(TaskInputDto taskInputDto);

   // public UserOutPutDto getUserById(Long userId);

  //  public List<UserOutPutDto> getAllUsers();

 //   public UserOutPutDto updateUser(UserInputDto userInputDto);

 //   public void deleteUser(Long userId);


}
