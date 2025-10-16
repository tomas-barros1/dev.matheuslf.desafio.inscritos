package dev.matheuslf.desafio.inscritos.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectRequestDto {
    @NotNull(message = "Nome é obrigatório!")
    private String name;

    private String description;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date startDate;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date endDate;
}
