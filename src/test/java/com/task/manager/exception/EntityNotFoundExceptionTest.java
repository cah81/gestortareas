package com.task.manager.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

import org.junit.jupiter.api.Test;

class EntityNotFoundExceptionTest {
    /**
     * Method under test: {@link EntityNotFoundException#EntityNotFoundException(String, int, Date)}
     */
    @Test
    void testConstructor() {
        EntityNotFoundException actualEntityNotFoundException = new EntityNotFoundException("An error occurred", 1,
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

        assertNull(actualEntityNotFoundException.getCause());
        Date expectedTimeStamp = actualEntityNotFoundException.timeStamp;
        assertSame(expectedTimeStamp, actualEntityNotFoundException.getTimeStamp());
        assertEquals(0, actualEntityNotFoundException.getSuppressed().length);
        assertEquals("An error occurred", actualEntityNotFoundException.getMessage());
        assertEquals("An error occurred", actualEntityNotFoundException.getLocalizedMessage());
        assertEquals(1, actualEntityNotFoundException.getHttpCode());
    }
}

