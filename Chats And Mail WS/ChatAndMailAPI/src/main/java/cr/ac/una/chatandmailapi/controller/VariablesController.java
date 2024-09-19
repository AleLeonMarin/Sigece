package cr.ac.una.chatandmailapi.controller;

import cr.ac.una.chatandmailapi.model.VariablesDTO;
import cr.ac.una.chatandmailapi.service.VariablesService;
import cr.ac.una.chatandmailapi.util.Respuesta;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/VariablesController")
@Tag(name = "Variables", description = "Operaciones sobre Variables")
public class VariablesController {

    private static final Logger LOG = Logger.getLogger(VariablesController.class.getName());

    @EJB
    private VariablesService variablesService;

    /**
     * Obtener una variable por ID
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Obtener una variable por ID", description = "Obtiene una variable de la base de datos por su ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Variable obtenida exitosamente"),
        @ApiResponse(responseCode = "404", description = "Variable no encontrada"),
        @ApiResponse(responseCode = "500", description = "Error al obtener la variable")
    })
    public Response obtenerVariablePorId(@PathParam("id") Long id) {
        try {
            Respuesta respuesta = variablesService.obtenerVariablePorId(id);
            if (!respuesta.getEstado()) {
                return Response.status(respuesta.getCodigoRespuesta().getValue()).entity(respuesta.getMensaje()).build();
            }
            return Response.ok((VariablesDTO) respuesta.getResultado("Variable")).build();
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error obteniendo la variable", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Error obteniendo la variable: " + e.getMessage() + "\"}")
                    .build();
        }
    }

    /**
     * Guardar una variable
     */
    @POST
    @Path("/guardar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Guardar una variable", description = "Guarda o actualiza una variable en la base de datos.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Variable guardada exitosamente"),
        @ApiResponse(responseCode = "500", description = "Error al guardar la variable")
    })
    public Response guardarVariable(VariablesDTO variableDto) {
        try {
            Respuesta respuesta = variablesService.guardarVariable(variableDto);
            return Response.ok(respuesta).build();
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error guardando la variable", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Error guardando la variable: " + e.getMessage() + "\"}")
                    .build();
        }
    }

    /**
     * Obtener todas las variables asociadas a una notificación por ID de notificación
     */
    @GET
    @Path("/notificacion/{notId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Obtener variables por ID de notificación", description = "Obtiene todas las variables asociadas a una notificación específica.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Variables obtenidas exitosamente"),
        @ApiResponse(responseCode = "500", description = "Error al obtener las variables")
    })
    public Response obtenerVariablesPorNotificacion(@PathParam("notId") Long notId) {
        try {
            Respuesta respuesta = variablesService.obtenerVariablesPorNotificacion(notId);
            if (!respuesta.getEstado()) {
                return Response.status(respuesta.getCodigoRespuesta().getValue()).entity(respuesta.getMensaje()).build();
            }
            return Response.ok(respuesta.getResultado("Variables")).build();
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error obteniendo las variables", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Error obteniendo las variables: " + e.getMessage() + "\"}")
                    .build();
        }
    }

    /**
     * Eliminar una variable por ID
     */
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Eliminar una variable por ID", description = "Elimina una variable de la base de datos por su ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Variable eliminada exitosamente"),
        @ApiResponse(responseCode = "404", description = "Variable no encontrada"),
        @ApiResponse(responseCode = "500", description = "Error al eliminar la variable")
    })
    public Response eliminarVariable(@PathParam("id") Long id) {
        try {
            Respuesta respuesta = variablesService.eliminarVariable(id);
            if (!respuesta.getEstado()) {
                return Response.status(respuesta.getCodigoRespuesta().getValue()).entity(respuesta.getMensaje()).build();
            }
            return Response.ok(respuesta).build();
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error eliminando la variable", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Error eliminando la variable: " + e.getMessage() + "\"}")
                    .build();
        }
    }
}
