package dev.matheuslf.desafio.inscritos.controllers;

import dev.matheuslf.desafio.inscritos.dtos.ProjectRequestDto;
import dev.matheuslf.desafio.inscritos.dtos.ProjectResponseDto;
import dev.matheuslf.desafio.inscritos.services.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/projects")
@Tag(name = "Projects", description = "API para gerenciamento de projetos")
public class ProjectController {

    private final ProjectService service;

    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Criar novo projeto", description = "Cria um novo projeto no sistema")
    public ResponseEntity<ProjectResponseDto> createProject(@Valid @RequestBody ProjectRequestDto dto) {
        return ResponseEntity.ok(service.createProject(dto));
    }

    @GetMapping
    @Operation(
        summary = "Listar projetos paginados",
        description = "Retorna uma lista paginada de projetos. " +
                     "Use 'sort' com valores como: 'name', 'id', 'startDate', etc. " +
                     "Para ordem decrescente, use: 'name,desc'"
    )
    public ResponseEntity<Page<ProjectResponseDto>> getProjectsPaginated(
        @Parameter(
            description = "Parâmetros de paginação e ordenação",
            schema = @Schema(
                example = "page=0&size=10&sort=name,asc"
            )
        )
        @PageableDefault(size = 10, sort = "id")
        Pageable pageable
    ) {
        return ResponseEntity.ok(service.getAllProjectsPaginated(pageable));
    }

}
