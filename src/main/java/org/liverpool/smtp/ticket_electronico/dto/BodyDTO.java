package org.liverpool.smtp.ticket_electronico.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BodyDTO {

    @NotBlank(message = "El campo 'tipoTicket' no puede estar vacío")
    private String tipoTicket;

    @NotNull(message = "El campo 'cambioCancelacionProducto' no puede ser nulo")
    @Valid
    private CambioCancelacionProductoDTO cambioCancelacionProducto;
}
