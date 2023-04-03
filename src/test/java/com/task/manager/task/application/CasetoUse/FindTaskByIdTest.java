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

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {FindTaskById.class})
@ExtendWith(SpringExtension.class)
class FindTaskByIdTest {
    @Autowired
    private FindTaskById findTaskById;

    @MockBean
    private TaskRepository taskRepository;

    /**
     * Method under test: {@link FindTaskById#findById(Integer)}
     */
    @Test
    void testFindById() {
        Task task = new Task();
        task.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        task.setDescription("The characteristics of someone or something");
        task.setEta(LocalDateTime.of(1, 1, 1, 1, 1));
        task.setFinished(true);
        task.setId(1);
        task.setTaskStatus(TaskStatus.ON_TIME);
        task.setTitle("Dr");
        Optional<Task> ofResult = Optional.of(task);
        when(taskRepository.findById((Integer) any())).thenReturn(ofResult);
        TaskOutPutDto actualFindByIdResult = findTaskById.findById(1);
        assertTrue(actualFindByIdResult.isFinished());
        assertEquals("01:01", actualFindByIdResult.getCreatedDate().toLocalTime().toString());
        assertEquals("Dr", actualFindByIdResult.getTitle());
        assertEquals(TaskStatus.ON_TIME, actualFindByIdResult.getTaskStatus());
        assertEquals(1L, actualFindByIdResult.getId().longValue());
        assertEquals("The characteristics of someone or something", actualFindByIdResult.getDescription());
        assertEquals("01:01", actualFindByIdResult.getEta().toLocalTime().toString());
        verify(taskRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link FindTaskById#findById(Integer)}
     */
    @Test
    void testFindById2() {
        LocalDateTime createdDate = LocalDateTime.of(3, 3, 3, 3, 1);

        Task task = new Task(1, "Dr", "The characteristics of someone or something", createdDate,
                LocalDateTime.of(3, 3, 3, 3, 1), true, TaskStatus.ON_TIME);
        task.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        task.setDescription("The characteristics of someone or something");
        task.setEta(LocalDateTime.of(1, 1, 1, 1, 1));
        task.setFinished(true);
        task.setId(1);
        task.setTaskStatus(TaskStatus.ON_TIME);
        task.setTitle("Dr");
        Optional<Task> ofResult = Optional.of(task);
        when(taskRepository.findById((Integer) any())).thenReturn(ofResult);
        TaskOutPutDto actualFindByIdResult = findTaskById.findById(1);
        assertTrue(actualFindByIdResult.isFinished());
        assertEquals("01:01", actualFindByIdResult.getCreatedDate().toLocalTime().toString());
        assertEquals("Dr", actualFindByIdResult.getTitle());
        assertEquals(TaskStatus.ON_TIME, actualFindByIdResult.getTaskStatus());
        assertEquals(1L, actualFindByIdResult.getId().longValue());
        assertEquals("The characteristics of someone or something", actualFindByIdResult.getDescription());
        assertEquals("01:01", actualFindByIdResult.getEta().toLocalTime().toString());
        verify(taskRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link FindTaskById#findById(Integer)}
     */
    @Test
    void testFindById3() {
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
        Optional<Task> ofResult = Optional.of(task);
        when(taskRepository.findById((Integer) any())).thenReturn(ofResult);
        TaskOutPutDto actualFindByIdResult = findTaskById.findById(1);
        assertTrue(actualFindByIdResult.isFinished());
        assertEquals("01:01", actualFindByIdResult.getCreatedDate().toLocalTime().toString());
        assertEquals("Dr", actualFindByIdResult.getTitle());
        assertEquals(TaskStatus.ON_TIME, actualFindByIdResult.getTaskStatus());
        assertEquals(1L, actualFindByIdResult.getId().longValue());
        assertEquals("The characteristics of someone or something", actualFindByIdResult.getDescription());
        assertEquals("01:01", actualFindByIdResult.getEta().toLocalTime().toString());
        verify(taskRepository).findById((Integer) any());
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

    /**
     * Method under test: {@link FindTaskById#findById(Integer)}
     */
    @Test
    void testFindById4() {
        when(taskRepository.findById((Integer) any())).thenReturn(Optional.empty());
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
        assertThrows(EntityNotFoundException.class, () -> findTaskById.findById(1));
        verify(taskRepository).findById((Integer) any());
        verify(task).setCreatedDate((LocalDateTime) any());
        verify(task).setDescription((String) any());
        verify(task).setEta((LocalDateTime) any());
        verify(task).setFinished(anyBoolean());
        verify(task).setId((Integer) any());
        verify(task).setTaskStatus((TaskStatus) any());
        verify(task).setTitle((String) any());
    }
}

