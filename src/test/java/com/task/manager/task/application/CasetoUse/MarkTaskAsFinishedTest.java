package com.task.manager.task.application.CasetoUse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.task.manager.exception.EntityNotFoundException;
import com.task.manager.task.domain.Task;
import com.task.manager.task.domain.TaskStatus;
import com.task.manager.task.infrastructure.dto.TaskOutPutDto;
import com.task.manager.task.infrastructure.repository.TaskRepository;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {MarkTaskAsFinished.class})
@ExtendWith(SpringExtension.class)
class MarkTaskAsFinishedTest {
    @Autowired
    private MarkTaskAsFinished markTaskAsFinished;

    @MockBean
    private TaskRepository taskRepository;

    /**
     * Method under test: {@link MarkTaskAsFinished#updateTaskAsFinished(Integer)}
     */
    @Test
    void testUpdateTaskAsFinished() {
        Task task = new Task();
        task.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        task.setDescription("The characteristics of someone or something");
        task.setEta(LocalDateTime.of(1, 1, 1, 1, 1));
        task.setFinished(true);
        task.setId(1);
        task.setTaskStatus(TaskStatus.ON_TIME);
        task.setTitle("Dr");
        Optional<Task> ofResult = Optional.of(task);

        Task task1 = new Task();
        task1.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        task1.setDescription("The characteristics of someone or something");
        task1.setEta(LocalDateTime.of(1, 1, 1, 1, 1));
        task1.setFinished(true);
        task1.setId(1);
        task1.setTaskStatus(TaskStatus.ON_TIME);
        task1.setTitle("Dr");
        when(taskRepository.save((Task) any())).thenReturn(task1);
        when(taskRepository.findById((Integer) any())).thenReturn(ofResult);
        TaskOutPutDto actualUpdateTaskAsFinishedResult = markTaskAsFinished.updateTaskAsFinished(1);
        assertTrue(actualUpdateTaskAsFinishedResult.isFinished());
        assertEquals("01:01", actualUpdateTaskAsFinishedResult.getCreatedDate().toLocalTime().toString());
        assertEquals("Dr", actualUpdateTaskAsFinishedResult.getTitle());
        assertEquals(TaskStatus.ON_TIME, actualUpdateTaskAsFinishedResult.getTaskStatus());
        assertEquals(1L, actualUpdateTaskAsFinishedResult.getId().longValue());
        assertEquals("The characteristics of someone or something", actualUpdateTaskAsFinishedResult.getDescription());
        assertEquals("01:01", actualUpdateTaskAsFinishedResult.getEta().toLocalTime().toString());
        verify(taskRepository).save((Task) any());
        verify(taskRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link MarkTaskAsFinished#updateTaskAsFinished(Integer)}
     */
    @Test
    void testUpdateTaskAsFinished2() {
        Task task = new Task();
        task.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        task.setDescription("The characteristics of someone or something");
        task.setEta(LocalDateTime.of(1, 1, 1, 1, 1));
        task.setFinished(true);
        task.setId(1);
        task.setTaskStatus(TaskStatus.ON_TIME);
        task.setTitle("Dr");
        Optional<Task> ofResult = Optional.of(task);
        when(taskRepository.save((Task) any())).thenThrow(new EntityNotFoundException("An error occurred", 1,
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())));
        when(taskRepository.findById((Integer) any())).thenReturn(ofResult);
        assertThrows(EntityNotFoundException.class, () -> markTaskAsFinished.updateTaskAsFinished(1));
        verify(taskRepository).save((Task) any());
        verify(taskRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link MarkTaskAsFinished#updateTaskAsFinished(Integer)}
     */
    @Test
    void testUpdateTaskAsFinished3() {
        Task task = new Task();
        task.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        task.setDescription("The characteristics of someone or something");
        task.setEta(LocalDateTime.of(1, 1, 1, 1, 1));
        task.setFinished(true);
        task.setId(1);
        task.setTaskStatus(TaskStatus.ON_TIME);
        task.setTitle("Dr");
        Optional<Task> ofResult = Optional.of(task);
        Task task1 = mock(Task.class);
        when(task1.isFinished()).thenReturn(true);
        when(task1.getTaskStatus()).thenReturn(TaskStatus.ON_TIME);
        when(task1.getId()).thenReturn(1);
        when(task1.getDescription()).thenReturn("The characteristics of someone or something");
        when(task1.getTitle()).thenReturn("Dr");
        when(task1.getCreatedDate()).thenReturn(LocalDateTime.of(1, 1, 1, 1, 1));
        when(task1.getEta()).thenReturn(LocalDateTime.of(1, 1, 1, 1, 1));
        doNothing().when(task1).setCreatedDate((LocalDateTime) any());
        doNothing().when(task1).setDescription((String) any());
        doNothing().when(task1).setEta((LocalDateTime) any());
        doNothing().when(task1).setFinished(anyBoolean());
        doNothing().when(task1).setId((Integer) any());
        doNothing().when(task1).setTaskStatus((TaskStatus) any());
        doNothing().when(task1).setTitle((String) any());
        task1.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        task1.setDescription("The characteristics of someone or something");
        task1.setEta(LocalDateTime.of(1, 1, 1, 1, 1));
        task1.setFinished(true);
        task1.setId(1);
        task1.setTaskStatus(TaskStatus.ON_TIME);
        task1.setTitle("Dr");
        when(taskRepository.save((Task) any())).thenReturn(task1);
        when(taskRepository.findById((Integer) any())).thenReturn(ofResult);
        TaskOutPutDto actualUpdateTaskAsFinishedResult = markTaskAsFinished.updateTaskAsFinished(1);
        assertTrue(actualUpdateTaskAsFinishedResult.isFinished());
        assertEquals("01:01", actualUpdateTaskAsFinishedResult.getCreatedDate().toLocalTime().toString());
        assertEquals("Dr", actualUpdateTaskAsFinishedResult.getTitle());
        assertEquals(TaskStatus.ON_TIME, actualUpdateTaskAsFinishedResult.getTaskStatus());
        assertEquals(1L, actualUpdateTaskAsFinishedResult.getId().longValue());
        assertEquals("The characteristics of someone or something", actualUpdateTaskAsFinishedResult.getDescription());
        assertEquals("01:01", actualUpdateTaskAsFinishedResult.getEta().toLocalTime().toString());
        verify(taskRepository).save((Task) any());
        verify(taskRepository).findById((Integer) any());
        verify(task1).isFinished();
        verify(task1).getTaskStatus();
        verify(task1, atLeast(1)).getId();
        verify(task1).getDescription();
        verify(task1).getTitle();
        verify(task1).getCreatedDate();
        verify(task1).getEta();
        verify(task1).setCreatedDate((LocalDateTime) any());
        verify(task1).setDescription((String) any());
        verify(task1).setEta((LocalDateTime) any());
        verify(task1).setFinished(anyBoolean());
        verify(task1).setId((Integer) any());
        verify(task1).setTaskStatus((TaskStatus) any());
        verify(task1).setTitle((String) any());
    }

    /**
     * Method under test: {@link MarkTaskAsFinished#updateTaskAsFinished(Integer)}
     */
    @Test
    void testUpdateTaskAsFinished4() {
        Task task = mock(Task.class);
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
        Optional<Task> ofResult = Optional.of(task);
        Task task1 = mock(Task.class);
        when(task1.isFinished()).thenReturn(true);
        when(task1.getTaskStatus()).thenReturn(TaskStatus.ON_TIME);
        when(task1.getId()).thenReturn(1);
        when(task1.getDescription()).thenReturn("The characteristics of someone or something");
        when(task1.getTitle()).thenReturn("Dr");
        when(task1.getCreatedDate()).thenReturn(LocalDateTime.of(1, 1, 1, 1, 1));
        when(task1.getEta()).thenReturn(LocalDateTime.of(1, 1, 1, 1, 1));
        doNothing().when(task1).setCreatedDate((LocalDateTime) any());
        doNothing().when(task1).setDescription((String) any());
        doNothing().when(task1).setEta((LocalDateTime) any());
        doNothing().when(task1).setFinished(anyBoolean());
        doNothing().when(task1).setId((Integer) any());
        doNothing().when(task1).setTaskStatus((TaskStatus) any());
        doNothing().when(task1).setTitle((String) any());
        task1.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        task1.setDescription("The characteristics of someone or something");
        task1.setEta(LocalDateTime.of(1, 1, 1, 1, 1));
        task1.setFinished(true);
        task1.setId(1);
        task1.setTaskStatus(TaskStatus.ON_TIME);
        task1.setTitle("Dr");
        when(taskRepository.save((Task) any())).thenReturn(task1);
        when(taskRepository.findById((Integer) any())).thenReturn(ofResult);
        TaskOutPutDto actualUpdateTaskAsFinishedResult = markTaskAsFinished.updateTaskAsFinished(1);
        assertTrue(actualUpdateTaskAsFinishedResult.isFinished());
        assertEquals("01:01", actualUpdateTaskAsFinishedResult.getCreatedDate().toLocalTime().toString());
        assertEquals("Dr", actualUpdateTaskAsFinishedResult.getTitle());
        assertEquals(TaskStatus.ON_TIME, actualUpdateTaskAsFinishedResult.getTaskStatus());
        assertEquals(1L, actualUpdateTaskAsFinishedResult.getId().longValue());
        assertEquals("The characteristics of someone or something", actualUpdateTaskAsFinishedResult.getDescription());
        assertEquals("01:01", actualUpdateTaskAsFinishedResult.getEta().toLocalTime().toString());
        verify(taskRepository).save((Task) any());
        verify(taskRepository).findById((Integer) any());
        verify(task1).isFinished();
        verify(task1).getTaskStatus();
        verify(task1, atLeast(1)).getId();
        verify(task1).getDescription();
        verify(task1).getTitle();
        verify(task1).getCreatedDate();
        verify(task1).getEta();
        verify(task1).setCreatedDate((LocalDateTime) any());
        verify(task1).setDescription((String) any());
        verify(task1).setEta((LocalDateTime) any());
        verify(task1).setFinished(anyBoolean());
        verify(task1).setId((Integer) any());
        verify(task1).setTaskStatus((TaskStatus) any());
        verify(task1).setTitle((String) any());
        verify(task).setCreatedDate((LocalDateTime) any());
        verify(task).setDescription((String) any());
        verify(task).setEta((LocalDateTime) any());
        verify(task, atLeast(1)).setFinished(anyBoolean());
        verify(task).setId((Integer) any());
        verify(task).setTaskStatus((TaskStatus) any());
        verify(task).setTitle((String) any());
    }
}

