package dev.matheuslf.desafio.inscritos.mappers;

import dev.matheuslf.desafio.inscritos.domain.Task;
import dev.matheuslf.desafio.inscritos.dtos.TaskRequestDto;
import dev.matheuslf.desafio.inscritos.dtos.TaskResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    Task dtoToTask(TaskRequestDto dto);

    TaskResponseDto taskToResponseDto(Task task);
}
