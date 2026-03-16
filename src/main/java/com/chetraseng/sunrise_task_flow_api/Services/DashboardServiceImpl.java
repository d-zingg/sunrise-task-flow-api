package com.chetraseng.sunrise_task_flow_api.Services;

import com.chetraseng.sunrise_task_flow_api.dto.DashboardResponse;
import com.chetraseng.sunrise_task_flow_api.model.TaskStatus;
import com.chetraseng.sunrise_task_flow_api.repository.ProjectRepository;
import com.chetraseng.sunrise_task_flow_api.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    @Override
    public DashboardResponse getSummary() {

        long totalTasks = taskRepository.count();
        long todoCount = taskRepository.countByStatus(TaskStatus.TODO);
        long inProgressCount = taskRepository.countByStatus(TaskStatus.IN_PROGRESS);
        long doneCount = taskRepository.countByStatus(TaskStatus.DONE);
        long overdueCount = taskRepository.findOverdueTasks(LocalDate.now()).size();
        var projectStats = projectRepository.getProjectStats();

        return new DashboardResponse(
                totalTasks,
                todoCount,
                inProgressCount,
                doneCount,
                overdueCount,
                projectStats
        );
    }
}
