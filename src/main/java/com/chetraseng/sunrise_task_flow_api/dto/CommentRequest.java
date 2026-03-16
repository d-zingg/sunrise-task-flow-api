package com.chetraseng.sunrise_task_flow_api.dto;

import lombok.Data;

@Data
public class CommentRequest {
    private String content;
    private String author;
}
