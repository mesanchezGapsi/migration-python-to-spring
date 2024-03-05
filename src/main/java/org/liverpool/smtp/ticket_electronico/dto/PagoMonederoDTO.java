package org.liverpool.smtp.ticket_electronico.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PagoMonederoDTO {
    private String saldoActualMonedero;
    private String montoObtenidoEnMonedero;
    private String saldoRedimidoMonedero;
    private String saldoUtilizadoMonedero;
    private String noMonederoEnmascarado;
    private String saldoAnteriorMonedero;
}
