package dev.matheuslf.desafio.inscritos.controllers;

import dev.matheuslf.desafio.inscritos.dtos.ProjectRequestDto;
import dev.matheuslf.desafio.inscritos.dtos.ProjectResponseDto;
import dev.matheuslf.desafio.inscritos.services.ProjectService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService service;

    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProjectResponseDto> createProject(@Valid ProjectRequestDto dto) {
        return ResponseEntity.ok(service.createProject(dto));
    }

    @GetMapping
    public ResponseEntity<Page<ProjectResponseDto>> getProjectsPaginated(Pageable pageable) {
        return ResponseEntity.ok(service.getAllProjectsPaginated(pageable));
    }

}
