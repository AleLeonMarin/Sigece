package cr.ac.una.chatandmailapi.controller;

import cr.ac.una.chatandmailapi.model.ParametrosDTO;
import cr.ac.una.chatandmailapi.service.ParametrosService;
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

@Path("/ParametrosController")
@Tag(name = "Parametros", description = "Operaciones sobre los parámetros de configuración de correos")
public class ParametrosController {

    private static final Logger LOG = Logger.getLogger(ParametrosController.class.getName());

    @Inject
    private ParametrosService parametrosService;

    @POST
    @Path("/guardar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Guardar o actualizar parámetros", description = "Permite guardar o actualizar los parámetros de configuración de correos en la base de datos.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Parámetros guardados exitosamente"),
        @ApiResponse(responseCode = "500", description = "Error al guardar los parámetros")
    })
    public Response guardarParametros(ParametrosDTO parametrosDto) {
        try {
            Respuesta respuesta = parametrosService.guardarParametros(parametrosDto);
            return Response.ok(respuesta).build();
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error guardando los parámetros", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Error guardando los parámetros: " + e.getMessage() + "\"}")
                    .build();
        }
    }

    @GET
    @Path("/obtener")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Obtener parámetros", description = "Obtiene los parámetros de configuración de correos por su ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Parámetros obtenidos exitosamente"),
        @ApiResponse(responseCode = "404", description = "Parámetros no encontrados"),
        @ApiResponse(responseCode = "500", description = "Error al obtener los parámetros")
    })

    public Response getParametros() {
        try {
            Respuesta respuesta = parametrosService.getParametros();
            ParametrosDTO parametrosDTO = (ParametrosDTO) respuesta.getResultado("Parametros");

            return Response.ok(parametrosDTO).build();
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error obteniendo los parámetros", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("\"Error eliminando el mensaje\"" + e.getMessage() + "\"}")
                    .build();
        }
    }
}
