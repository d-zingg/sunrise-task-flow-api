package com.chetraseng.sunrise_task_flow_api.spec;

import com.chetraseng.sunrise_task_flow_api.model.TaskModel;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class TaskSpec {
  public static Specification<TaskModel> isCompleted(boolean completed) {
    return (root, query, cb) -> cb.equal(root.get("completed"), completed);
  }

  public static Specification<TaskModel> containsTitle(String title) {
    return (root, query, cb) ->
        cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%");
  }

  public static Specification<TaskModel> equalProjectId(Long projectId) {
    return (root, query, cb) -> cb.equal(root.get("project").get("id"), projectId);
  }

  public static Specification<TaskModel> afterCreatedAt(LocalDateTime date) {
    return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("createdAt"), date);
  }
}
