package org.liverpool.smtp.ticket_electronico.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
public class TicketElectronicoRequest {

    @NotNull(message = "El campo 'email' no puede ser nulo")
    @Valid // Asegura que los campos dentro de Email también sean validados
    private EmailDTO email;

    @NotBlank(message = "El campo 'brand' no puede estar vacío")
    private String brand;

    @NotBlank(message = "El campo 'channel' no puede estar vacío")
    private String channel;

    private String clientId; //?
}
