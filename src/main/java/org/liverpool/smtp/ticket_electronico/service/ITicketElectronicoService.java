package org.liverpool.smtp.ticket_electronico.service;

import org.liverpool.smtp.ticket_electronico.dto.BodyDTO;

public interface ITicketElectronicoService {
    void enviarTicketElectronico(String to, BodyDTO body, String brand);

    String manejarHardBounce(String correoElectronico);
}
