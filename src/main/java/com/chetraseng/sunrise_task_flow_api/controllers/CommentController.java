package com.chetraseng.sunrise_task_flow_api.controllers;

import com.chetraseng.sunrise_task_flow_api.Services.CommentService;
import com.chetraseng.sunrise_task_flow_api.dto.CommentRequest;
import com.chetraseng.sunrise_task_flow_api.dto.CommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // Nested under tasks
    @GetMapping("/api/tasks/{taskId}/comments")
    public ResponseEntity<List<CommentResponse>> getComments(@PathVariable Long taskId) {
        return ResponseEntity.ok(commentService.findByTaskId(taskId));
    }

    @PostMapping("/api/tasks/{taskId}/comments")
    public ResponseEntity<CommentResponse> createComment(
            @PathVariable Long taskId,
            @RequestBody CommentRequest request) {
        return ResponseEntity.status(201).body(commentService.create(taskId, request));
    }

    // By comment ID
    @PutMapping("/api/comments/{id}")
    public ResponseEntity<CommentResponse> updateComment(
            @PathVariable Long id,
            @RequestBody CommentRequest request) {
        return ResponseEntity.ok(commentService.update(id, request));
    }

    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
