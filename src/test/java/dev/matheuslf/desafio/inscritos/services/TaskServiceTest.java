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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private TaskMapper taskMapper;

    @InjectMocks
    private TaskService taskService;

    @Test
    @DisplayName("Should create a task successfully")
    void shouldCreateTaskSuccessfully() {
        TaskRequestDto dto = new TaskRequestDto();
        dto.setProjectId(1L);

        Project project = new Project();
        project.setId(1L);

        Task task = new Task();
        task.setId(10L);

        TaskResponseDto responseDto = new TaskResponseDto();
        responseDto.setId(task.getId());

        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));
        when(taskMapper.dtoToTask(dto)).thenReturn(task);
        when(taskRepository.save(task)).thenReturn(task);
        when(taskMapper.taskToResponseDto(task)).thenReturn(responseDto);

        TaskResponseDto result = taskService.createTask(dto);

        assertNotNull(result);
        assertEquals(10L, result.getId());
        verify(projectRepository).findById(1L);
        verify(taskRepository).save(task);
        verify(taskMapper).dtoToTask(dto);
        verify(taskMapper).taskToResponseDto(task);
        assertEquals(project, task.getProject());
    }

    @Test
    @DisplayName("Should throw NotFoundException when project does not exist")
    void shouldThrowWhenProjectNotFoundOnCreate() {
        TaskRequestDto dto = new TaskRequestDto();
        dto.setProjectId(999L);
        when(projectRepository.findById(999L)).thenReturn(Optional.empty());

        NotFoundException ex = assertThrows(NotFoundException.class, () -> taskService.createTask(dto));
        assertEquals("Projeto não encontrado!", ex.getMessage());
        verify(projectRepository).findById(999L);
        verifyNoInteractions(taskRepository, taskMapper);
    }

    @Test
    @DisplayName("Should return filtered tasks successfully")
    void shouldReturnFilteredTasksSuccessfully() {
        Task task1 = new Task();
        task1.setId(1L);
        Task task2 = new Task();
        task2.setId(2L);

        TaskResponseDto dto1 = new TaskResponseDto();
        dto1.setId(1L);
        TaskResponseDto dto2 = new TaskResponseDto();
        dto2.setId(2L);

        when(taskRepository.findByOptionalFilters(StatusEnum.DOING, PriorityEnum.HIGH, 1L))
                .thenReturn(List.of(task1, task2));
        when(taskMapper.taskToResponseDto(task1)).thenReturn(dto1);
        when(taskMapper.taskToResponseDto(task2)).thenReturn(dto2);

        List<TaskResponseDto> result = taskService.getTaskByOptionalFilters(StatusEnum.DOING, PriorityEnum.HIGH, 1L);

        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(2L, result.get(1).getId());
        verify(taskRepository).findByOptionalFilters(StatusEnum.DOING, PriorityEnum.HIGH, 1L);
        verify(taskMapper, times(2)).taskToResponseDto(any(Task.class));
    }

    @Test
    @DisplayName("Should update task status successfully")
    void shouldUpdateTaskSuccessfully() {
        Task existingTask = new Task();
        existingTask.setId(10L);
        existingTask.setStatus(StatusEnum.TODO);

        TaskUpdateRequestDto dto = new TaskUpdateRequestDto();
        dto.setNewStatus(StatusEnum.DONE);

        Task savedTask = new Task();
        savedTask.setId(10L);
        savedTask.setStatus(StatusEnum.DONE);

        TaskResponseDto responseDto = new TaskResponseDto();
        responseDto.setId(10L);

        when(taskRepository.findById(10L)).thenReturn(Optional.of(existingTask));
        when(taskRepository.save(existingTask)).thenReturn(savedTask);
        when(taskMapper.taskToResponseDto(savedTask)).thenReturn(responseDto);

        TaskResponseDto result = taskService.updateTask(10L, dto);

        assertEquals(10L, result.getId());
        assertEquals(StatusEnum.DONE, existingTask.getStatus());
        verify(taskRepository).findById(10L);
        verify(taskRepository).save(existingTask);
        verify(taskMapper).taskToResponseDto(savedTask);
    }

    @Test
    @DisplayName("Should throw NotFoundException when updating non-existing task")
    void shouldThrowWhenUpdatingNonExistingTask() {
        when(taskRepository.findById(99L)).thenReturn(Optional.empty());

        NotFoundException ex = assertThrows(NotFoundException.class, () ->
                taskService.updateTask(99L, new TaskUpdateRequestDto()));
        assertEquals("Tarefa de id: 99 não encontrada", ex.getMessage());
        verify(taskRepository).findById(99L);
        verifyNoMoreInteractions(taskRepository);
    }

    @Test
    @DisplayName("Should delete task by id")
    void shouldDeleteTaskSuccessfully() {
        taskService.deleteTask(5L);

        verify(taskRepository).deleteById(5L);
    }
}
