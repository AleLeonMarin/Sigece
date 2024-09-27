package cr.ac.una.chatandmailapi.controller;

import cr.ac.una.chatandmailapi.model.CorreosDTO;
import cr.ac.una.chatandmailapi.model.UsuariosDTO;
import cr.ac.una.chatandmailapi.service.CorreosService;
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

@Path("/correos")
@Tag(name = "Correos", description = "Operaciones sobre Correos")
public class CorreosController {

    private static final Logger LOG = Logger.getLogger(CorreosController.class.getName());

    @EJB
    private CorreosService correosService;


    @POST
    @Path("/guardar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Guardar un correo en la base de datos", description = "Guarda un correo en la base de datos con estado 'P'.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Correo guardado exitosamente"),
        @ApiResponse(responseCode = "500", description = "Error al guardar el correo")
    })
    public Response guardarCorreo(CorreosDTO correosDto) {
        try {
            Respuesta respuesta = correosService.guardarCorreo(correosDto);
            if (!respuesta.getEstado()) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta).build();
            }
            return Response.ok(respuesta).build();
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error guardando el correo", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Error guardando el correo: " + e.getMessage() + "\"}")
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
            if (!respuesta.getEstado()) {
                return Response.status(Response.Status.NOT_FOUND).entity(respuesta).build();
            }
            return Response.ok(respuesta).build();
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error obteniendo el correo", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Error obteniendo el correo: " + e.getMessage() + "\"}")
                    .build();
        }
    }


    @POST
    @Path("/enviarPendientes")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Enviar correos pendientes", description = "Envía correos con estado 'P' que están pendientes de ser enviados.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Correos pendientes enviados exitosamente"),
        @ApiResponse(responseCode = "500", description = "Error al enviar correos pendientes")
    })
    
    
    public Response enviarCorreosPendientes() {
        try {
       
         
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error enviando correos pendientes", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Error enviando correos pendientes: " + e.getMessage() + "\"}")
                    .build();
        }
        return null;
    }
    
  
      @GET
    @Path("/obtenerTodos")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Obtener todos los correos", description = "Obtiene todos los correos de la base de datos.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Correos obtenidos exitosamente"),
        @ApiResponse(responseCode = "500", description = "Error al obtener los correos")
    })
    public Response obtenerTodosLosCorreos() {
        try {
            Respuesta respuesta = correosService.obtenerTodosLosCorreos();
            if (!respuesta.getEstado()) {
                return Response.status(respuesta.getCodigoRespuesta().getValue())
                        .entity(respuesta.getMensaje())
                        .build();
            }
            return Response.ok(respuesta.getResultado("Correos")).build();
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error obteniendo los correos", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Error obteniendo los correos: " + e.getMessage() + "\"}")
                    .build();
        }
    }
    
    
        @DELETE
    @Path("/eliminar/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Eliminar un correo", description = "Elimina un correo por su ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Correo eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Correo no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error al eliminar el correo")
    })
    public Response eliminarCorreo(@PathParam("id") Long id) {
        try {
            Respuesta respuesta = correosService.eliminarCorreo(id);
            if (!respuesta.getEstado()) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(respuesta.getMensaje())
                        .build();
            }
            return Response.ok("{\"mensaje\": \"Correo eliminado correctamente.\"}").build();
        } catch (Exception e) {
            Logger.getLogger(CorreosController.class.getName()).log(Level.SEVERE, "Error eliminando el correo", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Error eliminando el correo: " + e.getMessage() + "\"}")
                    .build();
        }
    }
    
    @POST
@Path("/enviarAhora")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Operation(summary = "Enviar un correo de inmediato", description = "Envía un correo inmediatamente sin el timeout.")
@ApiResponses({
    @ApiResponse(responseCode = "200", description = "Correo enviado exitosamente"),
    @ApiResponse(responseCode = "500", description = "Error al enviar el correo")
})
    
public Response enviarCorreoAhora(CorreosDTO correoDto) {
    try {
        Respuesta respuesta = correosService.enviarCorreoAhora(correoDto);

        if (!respuesta.getEstado()) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(respuesta.getMensaje())
                    .build();
        }

        return Response.ok().build(); // Envío exitoso
    } catch (Exception e) {
        Logger.getLogger(CorreosController.class.getName()).log(Level.SEVERE, "Error enviando el correo de inmediato", e);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("{\"error\": \"Error enviando el correo de inmediato: " + e.getMessage() + "\"}")
                .build();
    }
}

@POST
@Path("/enviarActivacion")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Operation(summary = "Enviar correo de activación", description = "Envía un correo de activación al usuario que se acaba de registrar.")
@ApiResponses({
    @ApiResponse(responseCode = "200", description = "Correo de activación enviado exitosamente"),
    @ApiResponse(responseCode = "500", description = "Error al enviar el correo de activación")
})
public Response enviarCorreoActivacion(UsuariosDTO usuarioDto) {
    try {
        Respuesta respuesta = correosService.enviarCorreoActivacion(usuarioDto);
        
        // Validar si el correo fue enviado correctamente
        if (!respuesta.getEstado()) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Error enviando el correo de activación: " + respuesta.getMensaje() + "\"}")
                    .build();
        }
        
        // Respuesta exitosa
        return Response.ok("{\"message\": \"Correo de activación enviado exitosamente.\"}").build();
        
    } catch (Exception e) {
        // Manejar cualquier excepción inesperada
        Logger.getLogger(CorreosController.class.getName()).log(Level.SEVERE, "Error enviando el correo de activación", e);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("{\"error\": \"Error enviando el correo de activación: " + e.getMessage() + "\"}")
                .build();
    }
}

    
}