package org.liverpool.smtp.ticket_electronico.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClienteDTO {
    private String emailCliente;
    private String telefonoCliente;
    private String nombreCliente;
}
