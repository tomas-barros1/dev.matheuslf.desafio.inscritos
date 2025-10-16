package dev.matheuslf.desafio.inscritos.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.matheuslf.desafio.inscritos.domain.enums.PriorityEnum;
import dev.matheuslf.desafio.inscritos.domain.enums.StatusEnum;
import dev.matheuslf.desafio.inscritos.dtos.TaskRequestDto;
import dev.matheuslf.desafio.inscritos.dtos.TaskResponseDto;
import dev.matheuslf.desafio.inscritos.dtos.TaskUpdateRequestDto;
import dev.matheuslf.desafio.inscritos.services.TaskService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private TaskService taskService;

    @Test
    @DisplayName("POST /tasks - deve criar uma tarefa com sucesso")
    void shouldCreateTaskSuccessfully() throws Exception {
        TaskRequestDto dto = new TaskRequestDto();
        dto.setProjectId(1L);

        TaskResponseDto response = new TaskResponseDto();
        response.setId(10L);

        Mockito.when(taskService.createTask(any(TaskRequestDto.class))).thenReturn(response);

        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(10L));
    }

    @Test
    @DisplayName("GET /tasks - deve retornar lista filtrada")
    void shouldReturnFilteredTasks() throws Exception {
        TaskResponseDto dto1 = new TaskResponseDto();
        dto1.setId(1L);
        TaskResponseDto dto2 = new TaskResponseDto();
        dto2.setId(2L);

        Mockito.when(taskService.getTaskByOptionalFilters(StatusEnum.TODO, PriorityEnum.HIGH, 1L))
                .thenReturn(List.of(dto1, dto2));

        mockMvc.perform(get("/tasks")
                        .param("status", "TODO")
                        .param("priority", "HIGH")
                        .param("projectId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[1].id").value(2L));
    }

    @Test
    @DisplayName("PUT /tasks/{id}/status - deve atualizar o status da tarefa")
    void shouldUpdateTaskSuccessfully() throws Exception {
        TaskUpdateRequestDto dto = new TaskUpdateRequestDto();
        dto.setNewStatus(StatusEnum.DONE);

        TaskResponseDto response = new TaskResponseDto();
        response.setId(5L);

        Mockito.when(taskService.updateTask(eq(5L), any(TaskUpdateRequestDto.class))).thenReturn(response);

        mockMvc.perform(put("/tasks/5/status")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(5L));
    }

    @Test
    @DisplayName("DELETE /tasks/{id} - deve deletar uma tarefa com sucesso")
    void shouldDeleteTaskSuccessfully() throws Exception {
        mockMvc.perform(delete("/tasks/9"))
                .andExpect(status().isNoContent());
        Mockito.verify(taskService).deleteTask(9L);
    }
}
