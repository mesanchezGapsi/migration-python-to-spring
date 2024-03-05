package org.liverpool.smtp.ticket_electronico.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TicketDTO {
    private String codigoFacturacion;
    private String terminal;
    private String numBoleta;
    private String fechaDeCompra;
    private String codigoBarra;
    private String codigoBarraGE;
    private String totalCompra;
}
