package com.chetraseng.sunrise_task_flow_api.controllers;

import com.chetraseng.sunrise_task_flow_api.Services.LabelService;
import com.chetraseng.sunrise_task_flow_api.dto.LabelRequest;
import com.chetraseng.sunrise_task_flow_api.dto.LabelResponse;
import com.chetraseng.sunrise_task_flow_api.dto.TaskResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/labels")
@RequiredArgsConstructor
public class LabelController {

    private final LabelService labelService;

    @GetMapping
    public ResponseEntity<List<LabelResponse>> getAll() {
        return ResponseEntity.ok(labelService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LabelResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(labelService.findById(id));
    }

    @PostMapping
    public ResponseEntity<LabelResponse> create(@RequestBody LabelRequest request) {
        return ResponseEntity.status(201).body(labelService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LabelResponse> update(@PathVariable Long id, @RequestBody LabelRequest request) {
        return ResponseEntity.ok(labelService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        labelService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/tasks")
    public ResponseEntity<List<TaskResponse>> getTasksByLabel(@PathVariable Long id) {
        return ResponseEntity.ok(labelService.findTasksByLabelId(id));
    }
}
