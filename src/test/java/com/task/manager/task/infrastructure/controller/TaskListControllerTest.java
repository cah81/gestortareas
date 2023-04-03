package com.task.manager.task.infrastructure.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.task.manager.task.application.service.DeleteTaskServicePort;
import com.task.manager.task.application.service.FindTaskByIdPort;
import com.task.manager.task.application.service.ListTaskServicePort;
import com.task.manager.task.domain.Task;
import com.task.manager.task.infrastructure.dto.TaskInputDto;
import com.task.manager.task.infrastructure.dto.TaskOutPutDto;
import com.task.manager.task.infrastructure.dto.mapper.AutoTaskMapper;

import java.util.ArrayList;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {TaskListControllerTest.class})
@ExtendWith(MockitoExtension.class)
@WebMvcTest
public class TaskListControllerTest {

    @MockBean
    private FindTaskByIdPort findTaskByIdPort;

    @InjectMocks
    private TaskListController taskListController;


    @MockBean
    ListTaskServicePort listTaskServicePort;
    ObjectMapper mapper = new ObjectMapper()
            .registerModule(new ParameterNamesModule())
            .registerModule(new Jdk8Module())
            .registerModule(new JavaTimeModule());


    Task task = null;
    Task task2 = null;
    TaskInputDto taskInputDto = null;

    TaskInputDto taskInputDto2 = null;

    TaskOutPutDto taskOutputDto = new TaskOutPutDto();

    TaskOutPutDto taskOutputDto2 = new TaskOutPutDto();


    @BeforeEach
    void setUp() {

        taskInputDto = new TaskInputDto("task1", "test task1", LocalDateTime.of(1, 1, 1, 1, 1));
        taskInputDto2 = new TaskInputDto("task2", "test task2", LocalDateTime.of(1, 1, 1, 1, 1));
        task = AutoTaskMapper.INSTANCE.mapToTaskInputDtoToTask(taskInputDto);
        task2 = AutoTaskMapper.INSTANCE.mapToTaskInputDtoToTask(taskInputDto2);

        taskOutputDto = AutoTaskMapper.INSTANCE.mapToTaskToTaskOutputDto(task);
        taskOutputDto2 = AutoTaskMapper.INSTANCE.mapToTaskToTaskOutputDto(task2);
        List<TaskOutPutDto> taskOutPutDtoList = new ArrayList<>();
        taskOutPutDtoList.add(taskOutputDto);
        taskOutPutDtoList.add(taskOutputDto2);

    }


    @Test
    void testTaskList() throws Exception {
            //give

            List<TaskOutPutDto> taskOutPutDtoList = new ArrayList<>();
            taskOutPutDtoList.add(taskOutputDto);
            taskOutPutDtoList.add(taskOutputDto2);
            when(listTaskServicePort.findAll()).thenReturn(taskOutPutDtoList);
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/tasks");
            ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(taskListController)
                    .build()
                    .perform(requestBuilder)
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(content().contentType("application/json"))
                    .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));


        }
}
