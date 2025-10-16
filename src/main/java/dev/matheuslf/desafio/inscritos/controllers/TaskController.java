package dev.matheuslf.desafio.inscritos.controllers;

import dev.matheuslf.desafio.inscritos.domain.enums.PriorityEnum;
import dev.matheuslf.desafio.inscritos.domain.enums.StatusEnum;
import dev.matheuslf.desafio.inscritos.dtos.TaskRequestDto;
import dev.matheuslf.desafio.inscritos.dtos.TaskResponseDto;
import dev.matheuslf.desafio.inscritos.dtos.TaskUpdateRequestDto;
import dev.matheuslf.desafio.inscritos.services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TaskResponseDto> createTask(@RequestBody TaskRequestDto dto) {
        return ResponseEntity.ok(service.createTask(dto));
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDto>> getTasksByOptionalFilters(@RequestParam(required = false) StatusEnum status, @RequestParam(required = false) PriorityEnum priority, @RequestParam(required = false) Long projectId) {
        return ResponseEntity.ok(service.getTaskByOptionalFilters(status, priority, projectId));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<TaskResponseDto> updateTask(@PathVariable Long id, @RequestBody TaskUpdateRequestDto dto) {
        return ResponseEntity.ok(service.updateTask(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTask(@PathVariable Long id) {
        service.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

}
