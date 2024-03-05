package org.liverpool.smtp.ticket_electronico.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MesaRegalosDTO {
    private String tipoEvento;
    private String numeroDeEvento;
    private String numTarjeta;
    private String fechaEvento;
}
