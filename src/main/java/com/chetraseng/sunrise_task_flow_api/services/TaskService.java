package com.chetraseng.sunrise_task_flow_api.services;

import com.chetraseng.sunrise_task_flow_api.dto.FilterTaskDto;
import com.chetraseng.sunrise_task_flow_api.dto.TaskResponse;
import java.util.List;

public interface TaskService {
  List<TaskResponse> findAll();

  TaskResponse findById(Long id);

  TaskResponse create(String title, String description);

  TaskResponse update(Long id, String title, String description);

  TaskResponse complete(Long id);

  void delete(Long id);

  List<TaskResponse> filterTask(FilterTaskDto filter);
}
