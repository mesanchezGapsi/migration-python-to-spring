package org.liverpool.smtp.ticket_electronico.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ProductoDTO {
    private String total;
    private String tipoEntrega;
    private String articuloNormalDescuento;
    private String seccion;
    private String mkpMessage;
    private String nombre;
    private List<String> descuentosValor;
    private String numeroTelefono;
    private String cantidad;
    private String fechaEstimadaEntregaFin;
    private String captura;
    private String tipoProducto;
    private String SIM;
    private String descripcion;
    private String catalogoExtendido;
    private String marca;
    private String sku;
    private String codigoBarraGE;
    private String seller;
    private String planDeCredito;
    private String fechaEstimadaEntregaInicio;
    private String referencia;
    private List<String> promocion;
    private String precioUnitario;
    private String imagen;
    private String fechaEntregaFinal;
    private String IMEI;
}
