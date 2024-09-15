package cr.ac.una.chatandmailapi.controller;

import cr.ac.una.chatandmailapi.service.EmailService;
import cr.ac.una.chatandmailapi.util.Respuesta;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/emails")
@Tag(name = "Emails", description = "Operaciones relacionadas con el envío de correos electrónicos")
public class EmailsController {

    private static final Logger LOG = Logger.getLogger(EmailsController.class.getName());

    @EJB
    private EmailService emailService;

    @POST
    @Path("/send")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Enviar un correo electrónico", description = "Permite enviar un correo electrónico a través de SMTP")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Correo enviado exitosamente"),
        @ApiResponse(responseCode = "500", description = "Error al enviar el correo")
    })
    public Response enviarCorreo(
            @FormParam("destinatario") String destinatario,
            @FormParam("asunto") String asunto,
            @FormParam("mensaje") String mensaje) {

        try {
            String resultado = emailService.enviarCorreo(destinatario, asunto, mensaje);
            return Response.ok("{\"message\": \"" + resultado + "\"}").build();
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error enviando el correo", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Error enviando el correo: " + e.getMessage() + "\"}")
                    .build();
        }
    }

    @POST
    @Path("/send-html")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Enviar un correo electrónico con plantilla HTML", description = "Permite enviar un correo electrónico con formato HTML a través de SMTP")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Correo con plantilla HTML enviado exitosamente"),
        @ApiResponse(responseCode = "500", description = "Error al enviar el correo con plantilla HTML")
    })
    public Response enviarCorreoHtml(
            @FormParam("destinatario") String destinatario,
            @FormParam("asunto") String asunto,
            @FormParam("plantillaHtml") String plantillaHtml) {

        try {
            String resultado = emailService.enviarCorreo(destinatario, asunto, plantillaHtml);
            return Response.ok("{\"message\": \"" + resultado + "\"}").build();
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error enviando el correo con plantilla HTML", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Error enviando el correo con plantilla HTML: " + e.getMessage() + "\"}")
                    .build();
        }
    }

    @POST
    @Path("/enviarConEspera")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "Enviar un correo con tiempo de espera",
            description = "Envía un correo electrónico aplicando un tiempo de espera basado en un parámetro almacenado en la base de datos. El tiempo de espera se especifica en segundos y se añade antes de que el correo sea enviado."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Correo enviado exitosamente."),
        @ApiResponse(responseCode = "500", description = "Error al enviar el correo.")
    })
    public Response enviarCorreoConEspera(
            @QueryParam("destinatario") String destinatario,
            @QueryParam("asunto") String asunto,
            @QueryParam("mensaje") String mensaje) {
        try {
            String resultado = emailService.enviarCorreoConEspera(destinatario, asunto, mensaje);
            return Response.ok("{\"message\": \"" + resultado + "\"}").build();
        } catch (Exception e) {
            LOG.severe("Error al enviar el correo: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Error enviando el correo: " + e.getMessage() + "\"}")
                    .build();
        }
    }
}
