package dev.matheuslf.desafio.inscritos.mappers;

import dev.matheuslf.desafio.inscritos.domain.Task;
import dev.matheuslf.desafio.inscritos.dtos.TaskRequestDto;
import dev.matheuslf.desafio.inscritos.dtos.TaskResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    Task dtoToTask(TaskRequestDto dto);

    TaskResponseDto taskToResponseDto(Task task);
}
