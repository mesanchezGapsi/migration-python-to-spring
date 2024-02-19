package org.liverpool.smtp.ticket_electronico.exception;

public class HardBounceException extends RuntimeException {
    public HardBounceException(String message) {
        super(message);
    }
}
