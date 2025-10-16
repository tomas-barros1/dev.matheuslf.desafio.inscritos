package dev.matheuslf.desafio.inscritos.controllers;

import dev.matheuslf.desafio.inscritos.domain.enums.PriorityEnum;
import dev.matheuslf.desafio.inscritos.domain.enums.StatusEnum;
import dev.matheuslf.desafio.inscritos.dtos.TaskRequestDto;
import dev.matheuslf.desafio.inscritos.dtos.TaskResponseDto;
import dev.matheuslf.desafio.inscritos.dtos.TaskUpdateRequestDto;
import dev.matheuslf.desafio.inscritos.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@Tag(name = "Tasks", description = "Gerenciamento de tarefas")
public class TaskController {
    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Criar nova tarefa", description = "Cria uma nova tarefa associada a um projeto")
    public ResponseEntity<TaskResponseDto> createTask(@Valid @RequestBody TaskRequestDto dto) {
        return ResponseEntity.ok(service.createTask(dto));
    }

    @GetMapping
    @Operation(
            summary = "Listar tarefas com filtros",
            description = "Retorna uma lista de tarefas filtradas por status, prioridade e/ou projeto"
    )
    public ResponseEntity<List<TaskResponseDto>> getTasksByOptionalFilters(
            @Parameter(description = "Filtrar por status da tarefa")
            @RequestParam(required = false) StatusEnum status,

            @Parameter(description = "Filtrar por prioridade da tarefa")
            @RequestParam(required = false) PriorityEnum priority,

            @Parameter(description = "Filtrar por ID do projeto")
            @RequestParam(required = false) Long projectId
    ) {
        return ResponseEntity.ok(service.getTaskByOptionalFilters(status, priority, projectId));
    }

    @PutMapping("/{id}/status")
    @Operation(summary = "Atualizar status da tarefa", description = "Atualiza o status de uma tarefa específica")
    public ResponseEntity<TaskResponseDto> updateTask(
            @Parameter(description = "ID da tarefa")
            @PathVariable Long id,

            @Parameter(description = "Status da tarefa: TODO, DOING ou DONE")
            @Valid @RequestBody TaskUpdateRequestDto dto
    ) {
        return ResponseEntity.ok(service.updateTask(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar tarefa", description = "Remove uma tarefa do sistema")
    public ResponseEntity<Void> deleteTask(
            @Parameter(description = "ID da tarefa")
            @PathVariable Long id
    ) {
        service.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

}
