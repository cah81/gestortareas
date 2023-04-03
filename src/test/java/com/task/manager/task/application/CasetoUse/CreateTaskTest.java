package com.task.manager.task.application.CasetoUse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.task.manager.task.domain.Task;
import com.task.manager.task.domain.TaskStatus;
import com.task.manager.task.infrastructure.dto.TaskInputDto;
import com.task.manager.task.infrastructure.dto.TaskOutPutDto;
import com.task.manager.task.infrastructure.repository.TaskRepository;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CreateTask.class})
@ExtendWith(SpringExtension.class)
class CreateTaskTest {
    @Autowired
    private CreateTask createTask;

    @MockBean
    private TaskRepository taskRepository;

    /**
     * Method under test: {@link CreateTask#createTask(TaskInputDto)}
     */
    @Test
    void testCreateTask() {
        Task task = new Task();
        task.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        task.setDescription("The characteristics of someone or something");
        task.setEta(LocalDateTime.of(1, 1, 1, 1, 1));
        task.setFinished(true);
        task.setId(1);
        task.setTaskStatus(TaskStatus.ON_TIME);
        task.setTitle("Dr");
        when(taskRepository.save((Task) any())).thenReturn(task);
        TaskOutPutDto actualCreateTaskResult = createTask.createTask(new TaskInputDto());
        assertTrue(actualCreateTaskResult.isFinished());
        assertEquals("01:01", actualCreateTaskResult.getCreatedDate().toLocalTime().toString());
        assertEquals("Dr", actualCreateTaskResult.getTitle());
        assertEquals(TaskStatus.ON_TIME, actualCreateTaskResult.getTaskStatus());
        assertEquals(1L, actualCreateTaskResult.getId().longValue());
        assertEquals("The characteristics of someone or something", actualCreateTaskResult.getDescription());
        assertEquals("01:01", actualCreateTaskResult.getEta().toLocalTime().toString());
        verify(taskRepository).save((Task) any());
    }

    /**
     * Method under test: {@link CreateTask#createTask(TaskInputDto)}
     */
    @Test
    void testCreateTask2() {
        Task task = mock(Task.class);
        when(task.isFinished()).thenReturn(true);
        when(task.getTaskStatus()).thenReturn(TaskStatus.ON_TIME);
        when(task.getId()).thenReturn(1);
        when(task.getDescription()).thenReturn("The characteristics of someone or something");
        when(task.getTitle()).thenReturn("Dr");
        when(task.getCreatedDate()).thenReturn(LocalDateTime.of(1, 1, 1, 1, 1));
        when(task.getEta()).thenReturn(LocalDateTime.of(1, 1, 1, 1, 1));
        doNothing().when(task).setCreatedDate((LocalDateTime) any());
        doNothing().when(task).setDescription((String) any());
        doNothing().when(task).setEta((LocalDateTime) any());
        doNothing().when(task).setFinished(anyBoolean());
        doNothing().when(task).setId((Integer) any());
        doNothing().when(task).setTaskStatus((TaskStatus) any());
        doNothing().when(task).setTitle((String) any());
        task.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        task.setDescription("The characteristics of someone or something");
        task.setEta(LocalDateTime.of(1, 1, 1, 1, 1));
        task.setFinished(true);
        task.setId(1);
        task.setTaskStatus(TaskStatus.ON_TIME);
        task.setTitle("Dr");
        when(taskRepository.save((Task) any())).thenReturn(task);
        TaskOutPutDto actualCreateTaskResult = createTask.createTask(new TaskInputDto());
        assertTrue(actualCreateTaskResult.isFinished());
        assertEquals("01:01", actualCreateTaskResult.getCreatedDate().toLocalTime().toString());
        assertEquals("Dr", actualCreateTaskResult.getTitle());
        assertEquals(TaskStatus.ON_TIME, actualCreateTaskResult.getTaskStatus());
        assertEquals(1L, actualCreateTaskResult.getId().longValue());
        assertEquals("The characteristics of someone or something", actualCreateTaskResult.getDescription());
        assertEquals("01:01", actualCreateTaskResult.getEta().toLocalTime().toString());
        verify(taskRepository).save((Task) any());
        verify(task).isFinished();
        verify(task).getTaskStatus();
        verify(task, atLeast(1)).getId();
        verify(task).getDescription();
        verify(task).getTitle();
        verify(task).getCreatedDate();
        verify(task).getEta();
        verify(task).setCreatedDate((LocalDateTime) any());
        verify(task).setDescription((String) any());
        verify(task).setEta((LocalDateTime) any());
        verify(task).setFinished(anyBoolean());
        verify(task).setId((Integer) any());
        verify(task).setTaskStatus((TaskStatus) any());
        verify(task).setTitle((String) any());
    }

}

