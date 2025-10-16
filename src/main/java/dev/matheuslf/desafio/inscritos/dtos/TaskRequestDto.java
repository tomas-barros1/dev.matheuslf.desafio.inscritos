package dev.matheuslf.desafio.inscritos.dtos;

import dev.matheuslf.desafio.inscritos.domain.enums.PriorityEnum;
import dev.matheuslf.desafio.inscritos.domain.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequestDto {
    private Long projectId;

    private String title;

    private String description;

    private StatusEnum status;

    private PriorityEnum priority;

    private Date dueDate;
}
