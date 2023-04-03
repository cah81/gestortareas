package com.task.manager.task.application.CasetoUse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.task.manager.task.infrastructure.repository.TaskRepository;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ListTask.class})
@ExtendWith(SpringExtension.class)
class ListTaskTest {
    @Autowired
    private ListTask listTask;

    @MockBean
    private TaskRepository taskRepository;

    /**
     * Method under test: {@link ListTask#findAll()}
     */
    @Test
    void testFindAll() {
        when(taskRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(listTask.findAll().isEmpty());
        verify(taskRepository).findAll();
    }
}

