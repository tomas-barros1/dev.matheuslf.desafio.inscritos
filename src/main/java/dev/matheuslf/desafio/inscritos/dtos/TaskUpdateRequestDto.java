package dev.matheuslf.desafio.inscritos.dtos;

import dev.matheuslf.desafio.inscritos.domain.enums.StatusEnum;

public class TaskUpdateRequestDto {
    private StatusEnum newStatus;

    public StatusEnum getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(StatusEnum newStatus) {
        this.newStatus = newStatus;
    }
}
