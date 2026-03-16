package com.chetraseng.sunrise_task_flow_api.controllers;

import com.chetraseng.sunrise_task_flow_api.dto.ProjectRequest;
import com.chetraseng.sunrise_task_flow_api.dto.ProjectResponse;
import com.chetraseng.sunrise_task_flow_api.dto.TaskResponse;
import com.chetraseng.sunrise_task_flow_api.Services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<ProjectResponse>> getAll() {

        return ResponseEntity.ok(projectService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> getById(@PathVariable Long id) {

        return ResponseEntity.ok(projectService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProjectResponse> create(
            @RequestBody ProjectRequest request) {

        ProjectResponse response = projectService.create(request);

        return ResponseEntity.status(201).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponse> update(
            @PathVariable Long id,
            @RequestBody ProjectRequest request) {

        return ResponseEntity.ok(
                projectService.update(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        projectService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/tasks")
    public ResponseEntity<List<TaskResponse>> getTasksByProject(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                projectService.findTasksByProjectId(id)
        );
    }
}