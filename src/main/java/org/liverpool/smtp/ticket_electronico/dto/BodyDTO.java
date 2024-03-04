package org.liverpool.smtp.ticket_electronico.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class BodyDTO {

    @NotBlank(message = "El campo 'tipoTicket' no puede estar vacío")
    private String tipoTicket;

    @NotNull(message = "El campo 'cambioCancelacionProducto' no puede ser nulo")
    @Valid
    private CambioCancelacionProductoDTO cambioCancelacionProducto;

    @NotNull(message = "El campo 'productos' no puede ser nulo")
    @Valid
    private List<ProductoDTO> productos;
}
