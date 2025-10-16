package dev.matheuslf.desafio.inscritos.services;

import dev.matheuslf.desafio.inscritos.domain.Project;
import dev.matheuslf.desafio.inscritos.dtos.ProjectRequestDto;
import dev.matheuslf.desafio.inscritos.dtos.ProjectResponseDto;
import dev.matheuslf.desafio.inscritos.mappers.ProjectMapper;
import dev.matheuslf.desafio.inscritos.repositories.ProjectRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    public ProjectService(ProjectRepository projectRepository, ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
    }

    public ProjectResponseDto createProject(ProjectRequestDto dto) {
        Project project = projectMapper.dtoToProject(dto);
        var savedProject = projectRepository.save(project);
        return projectMapper.projectToResponseDto(savedProject);
    }

    public Page<ProjectResponseDto> getAllProjectsPaginated(Pageable pageable) {
        return projectRepository.findAll(pageable)
                .map(projectMapper::projectToResponseDto);
    }

}
