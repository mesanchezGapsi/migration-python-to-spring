package org.liverpool.smtp.ticket_electronico.config;

import lombok.Getter;
import lombok.Setter;

import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "catalog")
@Getter
@Setter
public class CatalogoTicketsConfig {

    private Map<String, TicketInfo> tickets;

    @Getter
    @Setter
    @ToString
    public static class TicketInfo {
        private String nameHtml;
        private String labelFormaPagoTitle;
        private List<String> labelType;
        private List<String> labelProduct;
        private List<String> labelProductTitle;
        private List<String> labelResumenTitle;
        private Map<String, String> labelProductTitle2;
        private Map<String, List<String>> warnings;
    }
}
