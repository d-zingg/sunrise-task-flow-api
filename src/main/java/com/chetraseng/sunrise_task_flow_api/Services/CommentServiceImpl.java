package com.chetraseng.sunrise_task_flow_api.Services;


import com.chetraseng.sunrise_task_flow_api.dto.CommentRequest;
import com.chetraseng.sunrise_task_flow_api.dto.CommentResponse;
import com.chetraseng.sunrise_task_flow_api.exception.ResourceNotFoundException;
import com.chetraseng.sunrise_task_flow_api.mapper.CommentMapper;
import com.chetraseng.sunrise_task_flow_api.model.CommentModel;
import com.chetraseng.sunrise_task_flow_api.model.TaskModel;
import com.chetraseng.sunrise_task_flow_api.repository.CommentRepository;
import com.chetraseng.sunrise_task_flow_api.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;
    private final CommentMapper commentMapper;

    @Override
    public List<CommentResponse> findByTaskId(Long taskId) {
        TaskModel task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
        return commentRepository.findByTaskIdOrderByCreatedAtDesc(task.getId())
                .stream()
                .map(commentMapper::toCommentResponse)
                .toList();
    }

    @Override
    public CommentResponse create(Long taskId, CommentRequest request) {
        TaskModel task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        CommentModel comment = new CommentModel();
        comment.setContent(request.getContent());
        comment.setAuthor(request.getAuthor());
        comment.setTask(task);

        CommentModel saved = commentRepository.save(comment);
        return commentMapper.toCommentResponse(saved);
    }

    @Override
    public CommentResponse update(Long id, CommentRequest request) {
        CommentModel comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));

        comment.setContent(request.getContent());
        comment.setAuthor(request.getAuthor());

        CommentModel updated = commentRepository.save(comment);
        return commentMapper.toCommentResponse(updated);
    }

    @Override
    public void delete(Long id) {
        CommentModel comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
        commentRepository.delete(comment);
    }
}
