package dev.matheuslf.desafio.inscritos.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.matheuslf.desafio.inscritos.dtos.ProjectRequestDto;
import dev.matheuslf.desafio.inscritos.dtos.ProjectResponseDto;
import dev.matheuslf.desafio.inscritos.services.ProjectService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProjectController.class)
class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ProjectService projectService;

    @Test
    @DisplayName("POST /projects - deve criar um projeto com sucesso")
    void shouldCreateProjectSuccessfully() throws Exception {
        ProjectRequestDto dto = new ProjectRequestDto();
        dto.setName("Meu Projeto");
        dto.setDescription("Descrição teste");
        dto.setStartDate(Instant.now());
        dto.setEndDate(Instant.now().plusSeconds(3600));

        ProjectResponseDto response = new ProjectResponseDto();
        response.setId(1L);
        response.setName("Meu Projeto");
        response.setDescription("Descrição teste");

        when(projectService.createProject(any(ProjectRequestDto.class)))
                .thenReturn(response);

        mockMvc.perform(post("/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Meu Projeto"));
    }

    @Test
    @DisplayName("GET /projects - deve retornar lista paginada de projetos")
    void shouldReturnPaginatedProjects() throws Exception {
        ProjectResponseDto dto1 = new ProjectResponseDto();
        dto1.setId(1L);
        dto1.setName("Project 1");

        ProjectResponseDto dto2 = new ProjectResponseDto();
        dto2.setId(2L);
        dto2.setName("Project 2");

        Page<ProjectResponseDto> page = new PageImpl<>(List.of(dto1, dto2), PageRequest.of(0, 10), 2);

        when(projectService.getAllProjectsPaginated(any())).thenReturn(page);

        mockMvc.perform(get("/projects?page=0&size=10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(2))
                .andExpect(jsonPath("$.content[0].name").value("Project 1"))
                .andExpect(jsonPath("$.content[1].name").value("Project 2"));
    }
}
