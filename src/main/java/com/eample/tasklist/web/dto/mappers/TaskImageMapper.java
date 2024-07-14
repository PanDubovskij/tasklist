package com.eample.tasklist.web.dto.mappers;

import com.eample.tasklist.domain.task.TaskImage;
import com.eample.tasklist.web.dto.task.TaskImageDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskImageMapper extends Mappable<TaskImage, TaskImageDto> {
}
