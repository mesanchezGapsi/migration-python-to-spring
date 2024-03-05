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
@ToString
public class BodyDTO {

    @NotNull(message = "El campo 'vendedor' no puede ser nulo")
    @Valid
    private VendedorDTO vendedor;

    @Valid
    private BalanceDataDTO balanceData;

    @Valid
    private VideojuegosDescargablesDTO videojuegosDescargables;

    private String montoNoAhorrado;

    @Valid
    private PagoMonederoDTO pagoMonedero;

    @Valid
    private MesaRegalosDTO mesaRegalos;

    private List<String> pagoCupon; //?

    private String montoAhorrado; //?

    private String noPedido; //?

    private List<String> pagoCheque; //?

    private List<String> pagoEfectivo; //?

    @Valid
    private TicketDTO ticket;

    @Valid
    private VentaCasaDTO ventaCasa;

    @Valid
    private List<SomsDTO> soms;

    @NotNull(message = "El campo 'productos' no puede ser nulo")
    @Valid
    private List<ProductoDTO> productos;

    @Valid
    private List<String> warnings;

    private List<String> pagoTarjeta; //?

    private List<String> abonoTarjeta; //?

    @Valid
    private List<TiendaDTO> tienda;

    private String cambio;

    @Valid
    private ResumenBolsaDTO resumenBolsa;

    @NotNull(message = "El campo 'cambioCancelacionProducto' no puede ser nulo")
    @Valid
    private CambioCancelacionProductoDTO cambioCancelacionProducto;

    private List<String> pagoAppleBuy; //?

    private List<ClienteDTO> cliente;

    private List<String> ventaPie; //?

    @NotBlank(message = "El campo 'tipoTicket' no puede estar vacío")
    private String tipoTicket;

}
