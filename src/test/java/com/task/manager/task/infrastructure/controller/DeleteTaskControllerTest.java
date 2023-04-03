package com.task.manager.task.infrastructure.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.task.manager.task.application.service.CreateTaskServicePort;
import com.task.manager.task.application.service.DeleteTaskServicePort;
import com.task.manager.task.application.service.FindTaskByIdPort;
import com.task.manager.task.domain.Task;
import com.task.manager.task.infrastructure.dto.TaskInputDto;
import com.task.manager.task.infrastructure.dto.TaskOutPutDto;
import com.task.manager.task.infrastructure.dto.mapper.AutoTaskMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {CreateTaskControllerTest.class,
        DeleteTaskController.class})
@ExtendWith(MockitoExtension.class)
@WebMvcTest
public class DeleteTaskControllerTest {
    @MockBean
    private FindTaskByIdPort findTaskByIdPort;

    @InjectMocks
    private DeleteTaskController deleteTaskController;


    @MockBean
    DeleteTaskServicePort deleteTaskServicePort;
    ObjectMapper mapper = new ObjectMapper()
            .registerModule(new ParameterNamesModule())
            .registerModule(new Jdk8Module())
            .registerModule(new JavaTimeModule());


    Task task = null;

    TaskInputDto taskInputDto = null;

    TaskOutPutDto taskOutputDto = new TaskOutPutDto();


    @BeforeEach
    void setUp() {

        taskInputDto = new TaskInputDto("task1", "test task1", LocalDateTime.of(1, 1, 1, 1, 1));

        task = AutoTaskMapper.INSTANCE.mapToTaskInputDtoToTask(taskInputDto);

        taskOutputDto = AutoTaskMapper.INSTANCE.mapToTaskToTaskOutputDto(task);

    }

    /**
     * Method under test: {@link DeleteTaskController#deleteById(Integer)}
     */
    @Test
    void testDeleteById() throws Exception {
        when(findTaskByIdPort.findById((Integer) org.mockito.Mockito.any())).thenReturn(new TaskOutPutDto());
        doNothing().when(deleteTaskServicePort).deleteById((Integer) org.mockito.Mockito.any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/task/delete/{id}", 1);
        MockMvcBuilders.standaloneSetup(deleteTaskController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"task\":{\"id\":null,\"title\":null,\"description\":null,\"createdDate\":null,\"eta\":null,\"finished\":false,"
                                        + "\"taskStatus\":null},\"message\":\"task has been delete  with exit\"}"));
    }

    /**
     * Method under test: {@link DeleteTaskController#deleteById(Integer)}
     */
    @Test
    void testDeleteById2() throws Exception {
        when(findTaskByIdPort.findById((Integer) org.mockito.Mockito.any())).thenReturn(null);
        doNothing().when(deleteTaskServicePort).deleteById((Integer) org.mockito.Mockito.any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/task/delete/{id}", 1);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(deleteTaskController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"message\":\"Does not  exist record  with id:1\"}"));
    }


}
