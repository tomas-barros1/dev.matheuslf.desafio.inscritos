package dev.matheuslf.desafio.inscritos.controllers.exceptions;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Builder
@Getter
@Setter
public class ErrorResponse {
    private int status;
    private String message;
    private Instant timestamp;
}
