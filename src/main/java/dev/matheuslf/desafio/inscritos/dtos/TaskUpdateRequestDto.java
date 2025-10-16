package dev.matheuslf.desafio.inscritos.dtos;

import dev.matheuslf.desafio.inscritos.domain.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TaskUpdateRequestDto {
    private StatusEnum newStatus;
}
