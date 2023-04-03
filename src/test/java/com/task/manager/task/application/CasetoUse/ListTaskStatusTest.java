package com.task.manager.task.application.CasetoUse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.task.manager.task.domain.TaskStatus;
import com.task.manager.task.infrastructure.repository.TaskRepository;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ListTaskStatus.class})
@ExtendWith(SpringExtension.class)
class ListTaskStatusTest {
    @Autowired
    private ListTaskStatus listTaskStatus;

    @MockBean
    private TaskRepository taskRepository;

    /**
     * Method under test: {@link ListTaskStatus#findAllByTaskStatus(TaskStatus)}
     */
    @Test
    void testFindAllByTaskStatus() {
        when(taskRepository.findAllByTaskStatus((TaskStatus) any())).thenReturn(new ArrayList<>());
        assertTrue(listTaskStatus.findAllByTaskStatus(TaskStatus.ON_TIME).isEmpty());
        verify(taskRepository).findAllByTaskStatus((TaskStatus) any());
    }

    /**
     * Method under test: {@link ListTaskStatus#findAllByTaskStatus(TaskStatus)}
     */
    @Test
    void testFindAllByTaskStatus2() {
        when(taskRepository.findAllByTaskStatus((TaskStatus) any())).thenReturn(new ArrayList<>());
        assertTrue(listTaskStatus.findAllByTaskStatus(TaskStatus.LATE).isEmpty());
        verify(taskRepository).findAllByTaskStatus((TaskStatus) any());
    }
}

