package dev.matheuslf.desafio.inscritos.services;

import dev.matheuslf.desafio.inscritos.domain.Project;
import dev.matheuslf.desafio.inscritos.dtos.ProjectRequestDto;
import dev.matheuslf.desafio.inscritos.dtos.ProjectResponseDto;
import dev.matheuslf.desafio.inscritos.mappers.ProjectMapper;
import dev.matheuslf.desafio.inscritos.repositories.ProjectRepository;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {
    @Mock
    private ProjectRepository repository;

    @Mock
    private ProjectMapper mapper;

    @InjectMocks
    private ProjectService service;

    @Test
    @DisplayName("Should create a project successfully")
    void shouldCreateAProjectSuccessfully() {
        ProjectRequestDto dto = new ProjectRequestDto();
        dto.setDescription("Description");
        dto.setName("Project");
        dto.setStartDate(Instant.now());
        dto.setEndDate(Instant.now().plus(100, ChronoUnit.MINUTES));

        Project project = new Project();
        project.setDescription(dto.getDescription());
        project.setName(dto.getName());
        project.setStartDate(dto.getStartDate());
        project.setEndDate(dto.getEndDate());

        Project savedProject = new Project();
        savedProject.setId(1L);
        savedProject.setDescription(dto.getDescription());
        savedProject.setName(dto.getName());
        savedProject.setStartDate(dto.getStartDate());
        savedProject.setEndDate(dto.getEndDate());

        ProjectResponseDto responseDto = new ProjectResponseDto();
        responseDto.setId(savedProject.getId());
        responseDto.setDescription(savedProject.getDescription());
        responseDto.setName(savedProject.getName());
        responseDto.setStartDate(savedProject.getStartDate());
        responseDto.setEndDate(savedProject.getEndDate());

        when(mapper.dtoToProject(dto)).thenReturn(project);
        when(repository.save(project)).thenReturn(savedProject);
        when(mapper.projectToResponseDto(savedProject)).thenReturn(responseDto);

        var createdProject = service.createProject(dto);

        assertEquals(dto.getDescription(), createdProject.getDescription());
        assertEquals(dto.getName(), createdProject.getName());
        verify(mapper).dtoToProject(dto);
        verify(repository).save(project);
        verify(mapper).projectToResponseDto(savedProject);
    }

    @Test
    @DisplayName("Should fail validation when name is null")
    void shouldFailValidationWhenNameIsNull() {
        ProjectRequestDto dto = new ProjectRequestDto();
        dto.setDescription("Description");
        dto.setStartDate(Instant.now());
        dto.setEndDate(Instant.now().plus(100, ChronoUnit.MINUTES));

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        var violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Nome é obrigatório!")));
    }

    @Test
    @DisplayName("Should return paginated projects successfully")
    void shouldReturnPaginatedProjectsSuccessfully() {
        Pageable pageable = PageRequest.of(0, 2);

        Project project1 = new Project();
        project1.setId(1L);
        project1.setName("Project 1");

        Project project2 = new Project();
        project2.setId(2L);
        project2.setName("Project 2");

        ProjectResponseDto dto1 = new ProjectResponseDto();
        dto1.setId(1L);
        dto1.setName("Project 1");

        ProjectResponseDto dto2 = new ProjectResponseDto();
        dto2.setId(2L);
        dto2.setName("Project 2");

        Page<Project> projectPage = new PageImpl<>(List.of(project1, project2), pageable, 2);

        when(repository.findAll(pageable)).thenReturn(projectPage);
        when(mapper.projectToResponseDto(project1)).thenReturn(dto1);
        when(mapper.projectToResponseDto(project2)).thenReturn(dto2);

        Page<ProjectResponseDto> result = service.getAllProjectsPaginated(pageable);

        assertEquals(2, result.getTotalElements());
        assertEquals("Project 1", result.getContent().get(0).getName());
        assertEquals("Project 2", result.getContent().get(1).getName());

        verify(repository).findAll(pageable);
        verify(mapper).projectToResponseDto(project1);
        verify(mapper).projectToResponseDto(project2);
    }

}