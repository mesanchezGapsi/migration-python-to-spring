package org.liverpool.smtp.ticket_electronico.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BalanceDataDTO {
    private String pagoMin;
    private String saldoVencido;
    private String statusSaldo;
    private String cashAvailable;
    private String pagoMinMSI;
    private String pagoSinRefin;
    private String fechaPago;
    private String saldo;
}
