package com.chetraseng.sunrise_task_flow_api.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CommentResponse {
    private Long id;
    private String content;
    private String author;
    private LocalDateTime createdAt;
}
