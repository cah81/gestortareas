package com.task.manager.task.application.service;

import com.task.manager.task.infrastructure.dto.TaskOutPutDto;

public interface FindTaskByIdPort {

   public TaskOutPutDto findById (Integer id);
}
