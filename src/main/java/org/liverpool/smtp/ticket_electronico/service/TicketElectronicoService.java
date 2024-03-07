package org.liverpool.smtp.ticket_electronico.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.liverpool.smtp.ticket_electronico.config.CatalogoTicketsConfig;
import org.liverpool.smtp.ticket_electronico.dto.BodyDTO;
import org.liverpool.smtp.ticket_electronico.dto.ProductoDTO;
import org.liverpool.smtp.ticket_electronico.exception.EnvioEmailException;
import org.liverpool.smtp.ticket_electronico.exception.HardBounceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class TicketElectronicoService implements ITicketElectronicoService{

    private final RestTemplate restTemplate;

    @Autowired
    private CatalogoTicketsConfig catalogoTicketsConfig;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${api.key}")
    private String apiKey;

    public TicketElectronicoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void enviarTicketElectronico(final String to, BodyDTO body, final String brand) {
        log.info("to: " + to);
        log.info("body: " + body);

        if (catalogoTicketsConfig.getTickets() == null) {
            throw new EnvioEmailException("Error al tratar de obtener el catalogo de los tipos de ticket");
        }

        CatalogoTicketsConfig.TicketInfo labels = catalogoTicketsConfig.getTickets()
                .get(String.valueOf(body.getTipoTicket()));

        if (labels == null) {
            throw new EnvioEmailException("Error al tratar de obtener los detos para el template del tipo de ticket");
        }

        log.info("labels: " + labels);

        if (labels.getNameHtml().contains("REFUND")
                && ("Cambio mercancía igual precio".equals(body.getCambioCancelacionProducto().getMotivoDevolucion())
                || "CAMBIAR POR OTRO".equals(body.getCambioCancelacionProducto().getMotivoDevolucion()))) {
            labels = catalogoTicketsConfig.getTickets().get("3");
        }

        // Agregar la URL de las imagenes de los producots
        this.addUrlImage(body);

        // Construye el contenido HTML del ticket aquí usando Thymeleaf
        Context context = new Context();
        context.setVariable("body", body);
        context.setVariable("labels", labels);
        context.setVariable("brand", brand);

        String processHtml = templateEngine.process(labels.getNameHtml(), context);

        // Se envia el email por SMTP
        //enviarEmail(processHtml, body.getEmail())
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setSubject("Consulta tu ticket digital");
            helper.setFrom("Liverpool <ventasd@liverpool.com.mx>'");
            helper.setTo(to);
            helper.setText(processHtml,true);

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new EnvioEmailException("Error al enviar el correo electrónico", e);
        }
    }

    private void addUrlImage(BodyDTO body) {
        log.info("Agregar las url de las imagenes de los productos");
        for (ProductoDTO producto : body.getProductos()) {
            log.info(producto.getImagen());
            if (producto.getImagen() == null && producto.getSeccion() != null && producto.getSku() != null) {
                String url = "https://ss" + producto.getSeccion().substring(1) + ".liverpool.com.mx/xl/" + producto.getSku() + ".jpg";
                producto.setImagen(url);
            }
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
