package org.liverpool.smtp.ticket_electronico.configuration;

import lombok.Getter;
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
        private String nameHtml;
        private List<String> labelType;
        private List<String> labelProduct;
        private List<String> labelProductTitle;
        private String labelFormaPagoTitle;
        private List<String> labelResumenTitle;
        private Map<String, List<String>> warnings;
    }
}
