package org.liverpool.smtp.ticket_electronico;

import org.liverpool.smtp.ticket_electronico.configuration.CatalogoTicketsConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(CatalogoTicketsConfig.class)
public class TicketElectronicoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicketElectronicoApplication.class, args);
    }

}
