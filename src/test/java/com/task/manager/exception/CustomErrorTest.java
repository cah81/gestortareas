package com.task.manager.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CustomErrorTest {
    /**
     * Method under test: {@link CustomError#CustomError(String, int)}
     */
    @Test
    void testConstructor() {
        CustomError actualCustomError = new CustomError("Not all who wander are lost", 1);

        assertEquals(1, actualCustomError.getHttpCode());
        assertEquals("Not all who wander are lost", actualCustomError.getMessage());
    }
}

