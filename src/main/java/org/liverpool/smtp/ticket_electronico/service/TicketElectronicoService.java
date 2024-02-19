package org.liverpool.smtp.ticket_electronico.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.liverpool.smtp.ticket_electronico.configuration.CatalogoTicketsConfig;
import org.liverpool.smtp.ticket_electronico.dto.BodyDTO;
import org.liverpool.smtp.ticket_electronico.exception.EnvioEmailException;
import org.liverpool.smtp.ticket_electronico.exception.HardBounceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class TicketElectronicoService implements ITicketElectronicoService{

    private final RestTemplate restTemplate;

    @Autowired
    private CatalogoTicketsConfig catalogoTicketsConfig;

    @Autowired
    private JavaMailSender mailSender;

    @Value("${api.key}")
    private String apiKey;

    public TicketElectronicoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void enviarTicketElectronico(final String to, final BodyDTO body, final String brand) {
        String tipoTicketKey = String.valueOf(body.getTipoTicket());
        CatalogoTicketsConfig.TicketInfo labels = catalogoTicketsConfig.getTipos().get(tipoTicketKey);

        if (labels == null) {
            throw new EnvioEmailException("Error al tratar de obtener los detos para el template del tipo de ticket");
        }

        if (labels.getNAME_HTML().contains("REFUND")
                && ("Cambio mercancía igual precio".equals(body.getCambioCancelacionProducto().getMotivoDevolucion())
                || "CAMBIAR POR OTRO".equals(body.getCambioCancelacionProducto().getMotivoDevolucion()))) {
            labels = catalogoTicketsConfig.getTipos().get("3");
        }

        // Construye el contenido HTML del ticket aquí usando Thymeleaf o similar

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("Liverpool <ventasd@liverpool.com.mx>'");
            helper.setTo(to);
            helper.setSubject("Consulta tu ticket digital");
            helper.setText("htmlContent", true);

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new EnvioEmailException("Error al enviar el correo electrónico", e);
        }
    }

    @Override
    public String manejarHardBounce(final String correoElectronico) {
        // String url = "https://dev-api.liverpool.com.mx/contactar-cliente/ticket-electronico/hard-bounce\""
        String url = "https://apigee.liverpool.com.mx/cdp-pos/ticket/v2/clientes/email";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("apikey", apiKey);

        Map<String, String> payload = new HashMap<>();
        payload.put("correo_electronico", correoElectronico);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(payload, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, request, String.class);

            // verify=False, sirve para desactivar la verificación SSL en la solicitud HTTP
            // Esto se hace generalmente para evitar errores de certificados SSL en entornos
            // de desarrollo o con servidores que utilizan certificados autofirmados.
            // No es recomendable para producción debido a riesgos de seguridad,
            // ya que hace a la aplicación vulnerable a ataques de hombre en el medio (MITM).

            // return response.getBody()
            // resultjson["correoEnviado"]=mail
            // print(resultjson)
            return "";
        } catch (Exception e) {
            throw new HardBounceException(e.getMessage());
        }
    }
}
