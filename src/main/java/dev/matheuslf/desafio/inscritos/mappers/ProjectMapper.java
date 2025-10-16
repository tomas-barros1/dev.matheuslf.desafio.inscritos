package dev.matheuslf.desafio.inscritos.mappers;

import dev.matheuslf.desafio.inscritos.domain.Project;
import dev.matheuslf.desafio.inscritos.dtos.ProjectRequestDto;
import dev.matheuslf.desafio.inscritos.dtos.ProjectResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    Project dtoToProject(ProjectRequestDto dto);

    ProjectResponseDto projectToResponseDto(Project project);
}
