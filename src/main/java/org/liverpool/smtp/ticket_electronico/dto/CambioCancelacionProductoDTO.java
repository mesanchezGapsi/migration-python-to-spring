package org.liverpool.smtp.ticket_electronico.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CambioCancelacionProductoDTO {
    @NotBlank(message = "El motivo de devolución no puede estar vacío")
    private String motivoDevolucion;
}
