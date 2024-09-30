package cr.ac.una.chatandmailapi.controller;

import cr.ac.una.chatandmailapi.model.NotificacionDTO;
import cr.ac.una.chatandmailapi.service.NotificacionService;
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

@Path("/NotificacionesController")
@Tag(name = "Notificaciones", description = "Operaciones sobre Notificaciones")
public class NotificacionesController {

    private static final Logger LOG = Logger.getLogger(NotificacionesController.class.getName());

    @EJB
    private NotificacionService notificacionService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Obtener una notificación por ID", description = "Obtiene una notificación de la base de datos por su ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Notificación obtenida exitosamente"),
        @ApiResponse(responseCode = "404", description = "Notificación no encontrada"),
        @ApiResponse(responseCode = "500", description = "Error al obtener la notificación")
    })
    public Response obtenerNotificacionPorId(@PathParam("id") Long id) {
        try {
            Respuesta respuesta = notificacionService.obtenerNotificacionPorId(id);
            if (!respuesta.getEstado()) {
                return Response.status(respuesta.getCodigoRespuesta().getValue()).entity(respuesta.getMensaje()).build();
            }
            return Response.ok((NotificacionDTO) respuesta.getResultado("Notificacion")).build();
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error obteniendo la notificación", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Error obteniendo la notificación: " + e.getMessage() + "\"}")
                    .build();
        }
    }

    @POST
    @Path("/guardar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Guardar una notificación", description = "Guarda una notificación en la base de datos.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Notificación guardada exitosamente"),
        @ApiResponse(responseCode = "500", description = "Error al guardar la notificación")
    })
    public Response guardarNotificacion(NotificacionDTO notificacionDto) {
        try {
            Respuesta respuesta = notificacionService.guardarNotificacion(notificacionDto);
            return Response.ok(respuesta).build();
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error guardando la notificación", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Error guardando la notificación: " + e.getMessage() + "\"}")
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Eliminar una notificación por ID", description = "Elimina una notificación de la base de datos por su ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Notificación eliminada exitosamente"),
        @ApiResponse(responseCode = "404", description = "Notificación no encontrada"),
        @ApiResponse(responseCode = "500", description = "Error al eliminar la notificación")
    })
    public Response eliminarNotificacion(@PathParam("id") Long id) {
        try {
            Respuesta respuesta = notificacionService.eliminarNotificacion(id);
            return Response.ok(respuesta).build();
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error eliminando la notificación", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Error eliminando la notificación: " + e.getMessage() + "\"}")
                    .build();
        }
    }

    @GET
    @Path("/notificaciones")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Obtener todas las notificaciones", description = "Obtiene todas las notificaciones de la base de datos.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Notificaciones obtenidas exitosamente"),
        @ApiResponse(responseCode = "500", description = "Error al obtener las notificaciones")
    })
    public Response obtenerTodasNotificaciones() {
        try {
            Respuesta respuesta = notificacionService.obtenerTodasNotificaciones();
            if (!respuesta.getEstado()) {
                return Response.status(respuesta.getCodigoRespuesta().getValue()).entity(respuesta.getMensaje()).build();
            }
            return Response.ok(respuesta.getResultado("Notificaciones")).build();
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error obteniendo las notificaciones", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Error obteniendo las notificaciones: " + e.getMessage() + "\"}")
                    .build();
        }
    }
}
