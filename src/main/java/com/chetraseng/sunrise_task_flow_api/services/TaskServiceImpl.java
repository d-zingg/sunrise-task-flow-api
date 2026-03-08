package com.chetraseng.sunrise_task_flow_api.services;

import com.chetraseng.sunrise_task_flow_api.dto.FilterTaskDto;
import com.chetraseng.sunrise_task_flow_api.dto.TaskResponse;
import com.chetraseng.sunrise_task_flow_api.exception.ResourceNotFoundException;
import com.chetraseng.sunrise_task_flow_api.mapper.TaskMapper;
import com.chetraseng.sunrise_task_flow_api.model.TaskModel;
import com.chetraseng.sunrise_task_flow_api.repository.LegacyTaskRepository;
import java.util.List;

import com.chetraseng.sunrise_task_flow_api.repository.TaskRepository;
import com.chetraseng.sunrise_task_flow_api.spec.TaskSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
  private final TaskRepository taskRepository;
  private final TaskMapper taskMapper;

  @Override
  public List<TaskResponse> findAll() {
    return taskRepository.findAll().stream().map(taskMapper::toTaskResponse).toList();
  }

  @Override
  public TaskResponse findById(Long id) {
    return taskRepository
        .findById(id)
        .map(taskMapper::toTaskResponse)
        .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
  }

  @Override
  public TaskResponse create(String title, String description) {
    TaskModel task = new TaskModel();
    task.setTitle(title);
    task.setDescription(description);
    return taskMapper.toTaskResponse(taskRepository.save(task));
  }

  @Override
  public TaskResponse update(Long id, String title, String description) {
    TaskModel task =
        taskRepository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
    task.setTitle(title);
    task.setDescription(description);
    return taskMapper.toTaskResponse(taskRepository.save(task));
  }

  @Override
  public TaskResponse complete(Long id) {
    TaskModel task =
        taskRepository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
    task.setCompleted(true);
    return taskMapper.toTaskResponse(taskRepository.save(task));
  }

  @Override
  public void delete(Long id) {

    if (!taskRepository.existsById(id)) {
      throw new ResourceNotFoundException("Task not found");
    }

    taskRepository.deleteById(id);
  }

  @Override
  public List<TaskResponse> filterTask(FilterTaskDto filter) {
    Specification<TaskModel> spec = Specification.unrestricted();

    if (filter.getCompleted() != null) {
      spec = spec.and(TaskSpec.isCompleted(filter.getCompleted()));
    }
    if (filter.getTitle() != null && !filter.getTitle().isEmpty()) {
      spec = spec.and(TaskSpec.containsTitle(filter.getTitle()));
    }
    if (filter.getProjectId() != null) {
      spec = spec.and(TaskSpec.equalProjectId(filter.getProjectId()));
    }
    if (filter.getDate() != null) {
      spec = spec.and(TaskSpec.afterCreatedAt(filter.getDate()));
    }

    return taskRepository.findAll(spec).stream().map(taskMapper::toTaskResponse).toList();
  }
}
