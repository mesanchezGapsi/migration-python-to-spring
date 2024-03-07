package org.liverpool.smtp.ticket_electronico.controller;

import lombok.extern.slf4j.Slf4j;
import org.liverpool.smtp.ticket_electronico.dto.HardBounceRequest;
import org.liverpool.smtp.ticket_electronico.dto.TicketElectronicoRequest;
import org.liverpool.smtp.ticket_electronico.exception.EnvioEmailException;
import org.liverpool.smtp.ticket_electronico.service.ITicketElectronicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/ticket-electronico")
public class TicketElectronicoController {

    private final ITicketElectronicoService ticketElectronicoService;

    public TicketElectronicoController(ITicketElectronicoService ticketElectronicoService) {
        this.ticketElectronicoService = ticketElectronicoService;
    }

    @PostMapping
    public ResponseEntity<?> generarYEnviarTicket(@Validated @RequestBody TicketElectronicoRequest request) {
        if (request == null){
            throw new EnvioEmailException("El request llego nulo");
        }

        log.info("brand: " + request.getBrand());
        log.info("channel: " + request.getChannel());
        log.info("clientId: " + request.getClientId());

        if (request.getMail() == null){
            throw new EnvioEmailException("El request llego sin la información del mail");
        }

        ticketElectronicoService.enviarTicketElectronico(
                request.getMail().getTo(),
                request.getMail().getBody(),
                request.getBrand());
        return ResponseEntity.ok().body(Map.of("status", "ok"));
    }

    @PostMapping("/hard-bounce")
    public ResponseEntity<?> manejarHardBounce(@Validated @RequestBody HardBounceRequest request) {
        return ResponseEntity.ok().body(Map.of("status",
                ticketElectronicoService.manejarHardBounce(request.getCorreoElectronico())));
    }
}
