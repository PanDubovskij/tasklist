package com.eample.tasklist.web.dto.mappers;

import com.eample.tasklist.domain.task.Task;
import com.eample.tasklist.web.dto.task.TaskDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper extends Mappable<Task, TaskDto> {
}
