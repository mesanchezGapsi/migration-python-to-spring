package org.liverpool.smtp.ticket_electronico.exception;

public class EnvioEmailException extends RuntimeException {
    public EnvioEmailException(String message) {
        super(message);
    }

    public EnvioEmailException(String message, Throwable cause) {
        super(message, cause);
    }
}
