package com.eample.tasklist.repository;

import com.eample.tasklist.domain.task.Task;

import java.util.Optional;

public interface TaskRepository {
    Optional<Task> findById(Long id);

    Optional<Task> findByUserId(Long userId);

    void assignUserId(Long taskId, Long userId);

    void update(Task task);

    void create(Task task);

    void delete(Long id);
}
