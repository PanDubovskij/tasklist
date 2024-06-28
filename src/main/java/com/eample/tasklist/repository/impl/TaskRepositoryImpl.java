package com.eample.tasklist.repository.impl;

import com.eample.tasklist.domain.task.Task;
import com.eample.tasklist.repository.TaskRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class TaskRepositoryImpl implements TaskRepository {
    @Override
    public Optional<Task> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Task> findByUserId(Long userId) {
        return Optional.empty();
    }

    @Override
    public void assignUserId(Long taskId, Long userId) {

    }

    @Override
    public void update(Task task) {

    }

    @Override
    public void create(Task tsak) {

    }

    @Override
    public void delete(Long id) {

    }
}
