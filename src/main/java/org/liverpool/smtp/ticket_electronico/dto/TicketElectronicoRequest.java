package org.liverpool.smtp.ticket_electronico.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class TicketElectronicoRequest {

    @Valid // Asegura que los campos dentro de Email también sean validados
    private EmailDTO email;

    @NotBlank(message = "El campo 'brand' no puede estar vacío")
    private String brand;
}
