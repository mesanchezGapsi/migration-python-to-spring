package org.liverpool.smtp.ticket_electronico.controller;

import org.liverpool.smtp.ticket_electronico.dto.HardBounceRequest;
import org.liverpool.smtp.ticket_electronico.dto.TicketElectronicoRequest;
import org.liverpool.smtp.ticket_electronico.service.ITicketElectronicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/ticket-electronico")
public class TicketElectronicoController {

    private final ITicketElectronicoService ticketElectronicoService;

    public TicketElectronicoController(ITicketElectronicoService ticketElectronicoService) {
        this.ticketElectronicoService = ticketElectronicoService;
    }

    @PostMapping
    public ResponseEntity<?> generarYEnviarTicket(@Validated @RequestBody TicketElectronicoRequest request) {
        ticketElectronicoService.enviarTicketElectronico(
                request.getEmail().getTo(),
                request.getEmail().getBody(),
                request.getBrand());
        return ResponseEntity.ok().body(Map.of("status", "ok"));
    }

    @PostMapping("/hard-bounce")
    public ResponseEntity<?> manejarHardBounce(@Validated @RequestBody HardBounceRequest request) {
        return ResponseEntity.ok().body(Map.of("status",
                ticketElectronicoService.manejarHardBounce(request.getCorreoElectronico())));
    }
}
