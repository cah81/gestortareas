package com.task.manager.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

import org.junit.jupiter.api.Test;

class UnprocessableEntityExceptionTest {
    /**
     * Method under test: {@link UnprocessableEntityException#UnprocessableEntityException(String, int, Date)}
     */
    @Test
    void testConstructor() {
        UnprocessableEntityException actualUnprocessableEntityException = new UnprocessableEntityException(
                "An error occurred", 1, Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

        assertNull(actualUnprocessableEntityException.getCause());
        Date expectedTimeStamp = actualUnprocessableEntityException.timeStamp;
        assertSame(expectedTimeStamp, actualUnprocessableEntityException.getTimeStamp());
        assertEquals(0, actualUnprocessableEntityException.getSuppressed().length);
        assertEquals("An error occurred", actualUnprocessableEntityException.getMessage());
        assertEquals("An error occurred", actualUnprocessableEntityException.getLocalizedMessage());
        assertEquals(1, actualUnprocessableEntityException.getHttpCode());
    }
}

