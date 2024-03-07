package org.liverpool.smtp.ticket_electronico.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MailDTO {

    @NotBlank(message = "El campo 'to' no puede estar vacío")
    @Email(message = "Debe ser una dirección de correo electrónico válida")
    private String to;

    @NotBlank(message = "El campo 'messageCode' no puede estar vacío")
    private String messageCode;

    @NotNull(message = "El campo 'body' no puede ser nulo")
    @Valid
    private BodyDTO body;
}
