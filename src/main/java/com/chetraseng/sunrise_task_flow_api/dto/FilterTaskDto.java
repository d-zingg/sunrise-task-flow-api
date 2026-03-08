package com.chetraseng.sunrise_task_flow_api.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FilterTaskDto {
    private Long projectId;
    private String title;
    private LocalDateTime date;
    private Boolean completed;
}
