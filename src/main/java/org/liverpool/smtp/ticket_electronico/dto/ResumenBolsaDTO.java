package org.liverpool.smtp.ticket_electronico.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResumenBolsaDTO {
    private String porcentajeMonedero;
    private String descuento;
    private String importeAPagar;
    private String totalBoleta;
    private String subtotal;
    private String montoTransaccion;
    private String leyendaBanco;
}
