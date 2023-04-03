package com.task.manager.task.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public class TaskInputDto {

        private String title;
        private String description;
        private LocalDateTime eta;
    }