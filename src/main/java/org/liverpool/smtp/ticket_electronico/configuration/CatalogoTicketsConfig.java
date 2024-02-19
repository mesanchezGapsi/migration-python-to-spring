package org.liverpool.smtp.ticket_electronico.configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "tickets")
public class CatalogoTicketsConfig {

    @Getter
    private Map<String, TicketInfo> tipos;

    @Getter
    @ToString
    public static class TicketInfo {
        private String NAME_HTML;
        private List<String> LABEL_TYPE;
        private List<String> LABEL_PRODUCT;
        private List<String> LABEL_PRODUCT_TITLE;
        private String LABEL_FORMA_PAGO_TITLE;
        private List<String> LABEL_RESUMEN_TITLE;
        private Map<String, List<String>> WARNINGS;
    }
}
