package org.liverpool.smtp.ticket_electronico.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SomsDTO {
    private String fechaFila;
    private String leyendaCompromisoDePagos;
    private String numeroFila;
    private String aSuArribo;
    private String clienteAvisa;
}
