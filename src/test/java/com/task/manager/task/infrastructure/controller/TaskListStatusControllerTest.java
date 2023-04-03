package com.task.manager.task.infrastructure.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.sun.security.auth.UserPrincipal;
import com.task.manager.task.application.service.FindTaskByIdPort;
import com.task.manager.task.application.service.ListTaskServicePort;
import com.task.manager.task.application.service.ListTaskStatusServicePort;
import com.task.manager.task.domain.Task;
import com.task.manager.task.domain.TaskStatus;
import com.task.manager.task.infrastructure.dto.TaskInputDto;
import com.task.manager.task.infrastructure.dto.TaskOutPutDto;
import com.task.manager.task.infrastructure.dto.mapper.AutoTaskMapper;
import org.hamcrest.Matchers;
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
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = {TaskListStatusControllerTest.class,})
@ExtendWith(MockitoExtension.class)
@WebMvcTest
public class TaskListStatusControllerTest {

    @MockBean
    private ListTaskStatusServicePort listTaskStatusServicePort;



    @InjectMocks
    private TaskListStatusController taskListStatusController;


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


    @Tag("testShowByStatus")
    @DisplayName("testShowByStatus")
    @Test
    void testShowByStatus() throws Exception {

        //give

        List<TaskOutPutDto> taskOutPutDtoList = new ArrayList<>();
        taskOutPutDtoList.add(taskOutputDto);
        taskOutPutDtoList.add(taskOutputDto2);
        when(listTaskStatusServicePort.findAllByTaskStatus((TaskStatus) org.mockito.Mockito.any())).thenReturn(taskOutPutDtoList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/status/{status}",
                "ON_TIME");
        MockMvcBuilders.standaloneSetup(taskListStatusController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2))).andDo(print());
        ;
        ;
    }

    /**
     * Method under test: {@link TaskListStatusController#taskOutPutDtoList(TaskStatus)}
     */
    @Test
    void testTaskOutPutDtoList() throws Exception {
        when(listTaskStatusServicePort.findAllByTaskStatus((TaskStatus) org.mockito.Mockito.any()))
                .thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/status/{status}", TaskStatus.ON_TIME);
        MockMvcBuilders.standaloneSetup(taskListStatusController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link TaskListStatusController#taskOutPutDtoList(TaskStatus)}
     */
    @Test
    void testTaskOutPutDtoList2() throws Exception {
        ArrayList<TaskOutPutDto> taskOutPutDtoList = new ArrayList<>();
        taskOutPutDtoList.add(new TaskOutPutDto());
        when(listTaskStatusServicePort.findAllByTaskStatus((TaskStatus) org.mockito.Mockito.any()))
                .thenReturn(taskOutPutDtoList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/status/{status}", TaskStatus.ON_TIME);
        MockMvcBuilders.standaloneSetup(taskListStatusController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":null,\"title\":null,\"description\":null,\"createdDate\":null,\"eta\":null,\"finished\":false,\"taskStatus"
                                        + "\":null}]"));
    }

    /**
     * Method under test: {@link TaskListStatusController#taskOutPutDtoList(TaskStatus)}
     */
    @Test
    void testTaskOutPutDtoList3() throws Exception {
        ArrayList<TaskOutPutDto> taskOutPutDtoList = new ArrayList<>();
        taskOutPutDtoList.add(new TaskOutPutDto());
        taskOutPutDtoList.add(new TaskOutPutDto());
        when(listTaskStatusServicePort.findAllByTaskStatus((TaskStatus) org.mockito.Mockito.any()))
                .thenReturn(taskOutPutDtoList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/status/{status}", TaskStatus.ON_TIME);
        MockMvcBuilders.standaloneSetup(taskListStatusController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":null,\"title\":null,\"description\":null,\"createdDate\":null,\"eta\":null,\"finished\":false,\"taskStatus"
                                        + "\":null},{\"id\":null,\"title\":null,\"description\":null,\"createdDate\":null,\"eta\":null,\"finished\":false,"
                                        + "\"taskStatus\":null}]"));
    }

    /**
     * Method under test: {@link TaskListStatusController#taskOutPutDtoList(TaskStatus)}
     */
    @Test
    void testTaskOutPutDtoList4() throws Exception {
        when(listTaskStatusServicePort.findAllByTaskStatus((TaskStatus) org.mockito.Mockito.any()))
                .thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/status/{status}", "Uri Vars",
                "Uri Vars");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(taskListStatusController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link TaskListStatusController#taskOutPutDtoList(TaskStatus)}
     */
    @Test
    void testTaskOutPutDtoList5() throws Exception {
        when(listTaskStatusServicePort.findAllByTaskStatus((TaskStatus) org.mockito.Mockito.any()))
                .thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/status/{status}", "Uri Vars",
                "Uri Vars", "Uri Vars");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(taskListStatusController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link TaskListStatusController#taskOutPutDtoList(TaskStatus)}
     */
    @Test
    void testTaskOutPutDtoList6() throws Exception {
        when(listTaskStatusServicePort.findAllByTaskStatus((TaskStatus) org.mockito.Mockito.any()))
                .thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/status/{status}", TaskStatus.ON_TIME);
        getResult.secure(true);
        MockMvcBuilders.standaloneSetup(taskListStatusController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link TaskListStatusController#taskOutPutDtoList(TaskStatus)}
     */
    @Test
    void testTaskOutPutDtoList7() throws Exception {
        when(listTaskStatusServicePort.findAllByTaskStatus((TaskStatus) org.mockito.Mockito.any()))
                .thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/status/{status}", TaskStatus.ON_TIME);
        getResult.content("AXAXAXAX".getBytes("UTF-8"));
        MockMvcBuilders.standaloneSetup(taskListStatusController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link TaskListStatusController#taskOutPutDtoList(TaskStatus)}
     */
    @Test
    void testTaskOutPutDtoList8() throws Exception {
        when(listTaskStatusServicePort.findAllByTaskStatus((TaskStatus) org.mockito.Mockito.any()))
                .thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/status/{status}", TaskStatus.ON_TIME);
        getResult.content("https://example.org/example");
        MockMvcBuilders.standaloneSetup(taskListStatusController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link TaskListStatusController#taskOutPutDtoList(TaskStatus)}
     */
    @Test
    void testTaskOutPutDtoList9() throws Exception {
        when(listTaskStatusServicePort.findAllByTaskStatus((TaskStatus) org.mockito.Mockito.any()))
                .thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/status/{status}", TaskStatus.ON_TIME);
        getResult.contentType("text/plain");
        MockMvcBuilders.standaloneSetup(taskListStatusController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link TaskListStatusController#taskOutPutDtoList(TaskStatus)}
     */
    @Test
    void testTaskOutPutDtoList10() throws Exception {
        when(listTaskStatusServicePort.findAllByTaskStatus((TaskStatus) org.mockito.Mockito.any()))
                .thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/status/{status}", TaskStatus.ON_TIME);
        getResult.accept("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(taskListStatusController)
                .build()
                .perform(getResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(406));
    }

    /**
     * Method under test: {@link TaskListStatusController#taskOutPutDtoList(TaskStatus)}
     */
    @Test
    void testTaskOutPutDtoList11() throws Exception {
        when(listTaskStatusServicePort.findAllByTaskStatus((TaskStatus) org.mockito.Mockito.any()))
                .thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/status/{status}", TaskStatus.ON_TIME);
        getResult.session(new MockHttpSession());
        MockMvcBuilders.standaloneSetup(taskListStatusController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link TaskListStatusController#taskOutPutDtoList(TaskStatus)}
     */
    @Test
    void testTaskOutPutDtoList12() throws Exception {
        when(listTaskStatusServicePort.findAllByTaskStatus((TaskStatus) org.mockito.Mockito.any()))
                .thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/status/{status}", TaskStatus.ON_TIME);
        getResult.principal(new UserPrincipal("principal"));
        MockMvcBuilders.standaloneSetup(taskListStatusController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }


}
