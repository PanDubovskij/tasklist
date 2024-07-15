package com.eample.tasklist.web.controller;

import com.eample.tasklist.domain.task.Task;
import com.eample.tasklist.domain.user.User;
import com.eample.tasklist.service.TaskService;
import com.eample.tasklist.service.UserService;
import com.eample.tasklist.web.dto.mappers.TaskMapper;
import com.eample.tasklist.web.dto.mappers.UserMapper;
import com.eample.tasklist.web.dto.task.TaskDto;
import com.eample.tasklist.web.dto.user.UserDto;
import com.eample.tasklist.web.dto.validation.OnCreate;
import com.eample.tasklist.web.dto.validation.OnUpdate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Validated
@Tag(name = "User Controller", description = "User API")
public class UserController {

    private final UserService userService;
    private final TaskService taskService;

    private final UserMapper userMapper;
    private final TaskMapper taskMapper;

    @PutMapping
    @MutationMapping(name = "updateUser")
    @Operation(summary = "Update user")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#dto.id)")
    public UserDto update(
            @Validated(OnUpdate.class)
            @RequestBody @Argument final UserDto dto) {
        User user = userMapper.toEntity(dto);
        User updatedUser = userService.update(user);
        return userMapper.toDto(updatedUser);
    }

    @GetMapping("/{id}")
    @QueryMapping(name = "userById")
    @Operation(summary = "Get UserDto by id")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#id)")
    public UserDto getById(final @PathVariable @Argument Long id) {
        User user = userService.getById(id);
        return userMapper.toDto(user);
    }

    @DeleteMapping("/{id}")
    @MutationMapping(name = "deleteUserById")
    @Operation(summary = "Delete user by id")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#id)")
    public void deleteById(final @PathVariable @Argument Long id) {
        userService.delete(id);
    }

    @GetMapping("/{id}/tasks")
    @QueryMapping(name = "tasksByUserId")
    @Operation(summary = "Get all User tasks")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#id)")
    public List<TaskDto> getTasksByUserId(
            final @PathVariable @Argument Long id) {
        List<Task> tasks = taskService.getAllByUserId(id);
        return taskMapper.toDto(tasks);
    }

    @PostMapping("/{id}/tasks")
    @MutationMapping(name = "createTask")
    @Operation(summary = "Add task to user")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#id)")
    public TaskDto createTask(
            final @PathVariable @Argument Long id,
            final @Validated(OnCreate.class)
            @RequestBody @Argument TaskDto dto) {
        Task task = taskMapper.toEntity(dto);
        Task createdTask = taskService.create(task, id);
        return taskMapper.toDto(createdTask);
    }
}
