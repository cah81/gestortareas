package com.task.manager.task.infrastructure.controller;



import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.task.manager.task.application.service.CreateTaskServicePort;
import com.task.manager.task.domain.Task;
import com.task.manager.task.infrastructure.dto.TaskInputDto;
import com.task.manager.task.infrastructure.dto.TaskOutPutDto;
import com.task.manager.task.infrastructure.dto.mapper.AutoTaskMapper;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;


import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;


import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.context.ContextConfiguration;

import org.springframework.test.web.servlet.MockMvc;


import com.fasterxml.jackson.databind.ObjectMapper;



import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.mockito.Mockito.when;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



import java.time.LocalDateTime;

@ContextConfiguration(classes = {CreateTaskControllerTest.class})
@ExtendWith(MockitoExtension.class)
@WebMvcTest
public class CreateTaskControllerTest {



    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private CreateTaskController createTaskController;


    @MockBean
    CreateTaskServicePort createTaskServicePortService;
    ObjectMapper mapper = new ObjectMapper()
            .registerModule(new ParameterNamesModule())
            .registerModule(new Jdk8Module())
            .registerModule(new JavaTimeModule());


    Task task = null;

    TaskInputDto taskInputDto = null;

    TaskOutPutDto taskOutputDto = new TaskOutPutDto();



    @BeforeEach
    void setUp() {

        taskInputDto = new TaskInputDto("task1","test task1", LocalDateTime.of(1, 1, 1, 1, 1));

        task = AutoTaskMapper.INSTANCE.mapToTaskInputDtoToTask(taskInputDto);

        taskOutputDto = AutoTaskMapper.INSTANCE.mapToTaskToTaskOutputDto(task);

   }
    @Test
    public void testCreateTask () throws Exception{

        when(createTaskServicePortService.createTask(ArgumentMatchers.any())).thenReturn(taskOutputDto);
        String json = mapper.writeValueAsString(taskInputDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/tasks")
                .contentType(MediaType.APPLICATION_JSON).content(json);
        MockMvcBuilders.standaloneSetup(createTaskController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.equalTo("task1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", Matchers.equalTo("test task1")))
                .andDo(print());

    }




}
