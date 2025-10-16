package dev.matheuslf.desafio.inscritos.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectResponseDto {
    private Long id;

    private String name;

    private String description;

    private Date startDate;

    private Date endDate;
}
