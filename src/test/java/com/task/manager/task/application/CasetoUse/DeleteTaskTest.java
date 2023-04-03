package com.task.manager.task.application.CasetoUse;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.task.manager.exception.EntityNotFoundException;
import com.task.manager.task.domain.Task;
import com.task.manager.task.domain.TaskStatus;
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

@ContextConfiguration(classes = {DeleteTask.class})
@ExtendWith(SpringExtension.class)
class DeleteTaskTest {
    @Autowired
    private DeleteTask deleteTask;

    @MockBean
    private TaskRepository taskRepository;

    /**
     * Method under test: {@link DeleteTask#deleteById(Integer)}
     */
    @Test
    void testDeleteById() {
        Task task = new Task();
        task.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        task.setDescription("The characteristics of someone or something");
        task.setEta(LocalDateTime.of(1, 1, 1, 1, 1));
        task.setFinished(true);
        task.setId(1);
        task.setTaskStatus(TaskStatus.ON_TIME);
        task.setTitle("Dr");
        Optional<Task> ofResult = Optional.of(task);
        doNothing().when(taskRepository).deleteById((Integer) any());
        when(taskRepository.findById((Integer) any())).thenReturn(ofResult);
        deleteTask.deleteById(1);
        verify(taskRepository).findById((Integer) any());
        verify(taskRepository).deleteById((Integer) any());
    }

    /**
     * Method under test: {@link DeleteTask#deleteById(Integer)}
     */
    @Test
    void testDeleteById2() {
        Task task = new Task();
        task.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        task.setDescription("The characteristics of someone or something");
        task.setEta(LocalDateTime.of(1, 1, 1, 1, 1));
        task.setFinished(true);
        task.setId(1);
        task.setTaskStatus(TaskStatus.ON_TIME);
        task.setTitle("Dr");
        Optional<Task> ofResult = Optional.of(task);
        doThrow(new EntityNotFoundException("An error occurred", 1,
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()))).when(taskRepository)
                .deleteById((Integer) any());
        when(taskRepository.findById((Integer) any())).thenReturn(ofResult);
        assertThrows(EntityNotFoundException.class, () -> deleteTask.deleteById(1));
        verify(taskRepository).findById((Integer) any());
        verify(taskRepository).deleteById((Integer) any());
    }

    /**
     * Method under test: {@link DeleteTask#deleteById(Integer)}
     */
    @Test
    void testDeleteById3() {
        doNothing().when(taskRepository).deleteById((Integer) any());
        when(taskRepository.findById((Integer) any())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> deleteTask.deleteById(1));
        verify(taskRepository).findById((Integer) any());
    }
}

