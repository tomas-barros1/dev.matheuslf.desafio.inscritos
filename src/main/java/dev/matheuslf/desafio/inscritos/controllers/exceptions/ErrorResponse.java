package dev.matheuslf.desafio.inscritos.controllers.exceptions;

import java.time.Instant;

public class ErrorResponse {
    private int status;
    private String message;
    private Instant timestamp;

    public ErrorResponse() {
    }

    public ErrorResponse(int status, String message, Instant timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public static ErrorResponseBuilder builder() {
        return new ErrorResponseBuilder();
    }

    public static class ErrorResponseBuilder {
        private int status;
        private String message;
        private Instant timestamp;

        ErrorResponseBuilder() {
        }

        public ErrorResponseBuilder status(int status) {
            this.status = status;
            return this;
        }

        public ErrorResponseBuilder message(String message) {
            this.message = message;
            return this;
        }

        public ErrorResponseBuilder timestamp(Instant timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public ErrorResponse build() {
            return new ErrorResponse(status, message, timestamp);
        }

        @Override
        public String toString() {
            return "ErrorResponse.ErrorResponseBuilder(status=" + this.status + ", message=" + this.message + ", timestamp=" + this.timestamp + ")";
        }
    }
}
