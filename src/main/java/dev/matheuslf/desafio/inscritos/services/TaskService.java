package dev.matheuslf.desafio.inscritos.services;

import dev.matheuslf.desafio.inscritos.domain.Project;
import dev.matheuslf.desafio.inscritos.domain.Task;
import dev.matheuslf.desafio.inscritos.domain.enums.PriorityEnum;
import dev.matheuslf.desafio.inscritos.domain.enums.StatusEnum;
import dev.matheuslf.desafio.inscritos.dtos.TaskRequestDto;
import dev.matheuslf.desafio.inscritos.dtos.TaskResponseDto;
import dev.matheuslf.desafio.inscritos.dtos.TaskUpdateRequestDto;
import dev.matheuslf.desafio.inscritos.mappers.TaskMapper;
import dev.matheuslf.desafio.inscritos.repositories.ProjectRepository;
import dev.matheuslf.desafio.inscritos.repositories.TaskRepository;
import dev.matheuslf.desafio.inscritos.services.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskService(ProjectRepository projectRepository, TaskRepository taskRepository, TaskMapper taskMapper) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    public TaskResponseDto createTask(TaskRequestDto dto) {
        Project project = projectRepository.findById(dto.getProjectId())
                .orElseThrow(() -> new NotFoundException("Projeto não encontrado!"));
        Task task = taskRepository.save(taskMapper.dtoToTask(dto));
        return taskMapper.taskToResponseDto(task);
    }

    public List<TaskResponseDto> getTaskByOptionalFilters(StatusEnum status, PriorityEnum priority, Long projectId) {
        return taskRepository.findByOptionalFilters(status, priority, projectId)
                .stream()
                .map(taskMapper::taskToResponseDto)
                .toList();
    }

    public TaskResponseDto updateTask(Long taskId, TaskUpdateRequestDto dto) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NotFoundException("Tarefa de id: " + taskId + " não encontrada"));
        task.setStatus(dto.getNewStatus());
        return taskMapper.taskToResponseDto(taskRepository.save(task));
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

}
