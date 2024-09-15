package cr.ac.una.chatandmailapi.controller;

import cr.ac.una.chatandmailapi.model.CorreosDTO;
import cr.ac.una.chatandmailapi.service.CorreosService;
import cr.ac.una.chatandmailapi.util.Respuesta;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/correos")
@Tag(name = "Correos", description = "Operaciones sobre Correos")
public class CorreosController {

    private static final Logger LOG = Logger.getLogger(CorreosController.class.getName());

    @Inject
    private CorreosService correosService;

    @POST
    @Path("/enviar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Enviar un correo y guardarlo en la base de datos", description = "Envía un correo electrónico y guarda el resultado en la base de datos.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Correo enviado y guardado exitosamente"),
        @ApiResponse(responseCode = "500", description = "Error al enviar o guardar el correo")
    })
    public Response enviarCorreo(CorreosDTO correosDto) {
        try {
            Respuesta respuesta = correosService.enviarCorreo(correosDto);
            return Response.ok(respuesta).build();
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error enviando y guardando el correo", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Error enviando y guardando el correo: " + e.getMessage() + "\"}")
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Obtener un correo por ID", description = "Obtiene un correo de la base de datos por su ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Correo obtenido exitosamente"),
        @ApiResponse(responseCode = "404", description = "Correo no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error al obtener el correo")
    })
    public Response getCorreo(@PathParam("id") Long id) {
        try {
            Respuesta respuesta = correosService.getCorreo(id);
            return Response.ok(respuesta).build();
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error obteniendo el correo", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Error obteniendo el correo: " + e.getMessage() + "\"}")
                    .build();
        }
    }
}

