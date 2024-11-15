/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.chatandmailapi.controller;

import cr.ac.una.chatandmailapi.model.UsuariosDTO;
import cr.ac.una.chatandmailapi.service.MensajesService;
import cr.ac.una.chatandmailapi.service.UsuariosService;
import cr.ac.una.chatandmailapi.util.CodigoRespuesta;
import cr.ac.una.chatandmailapi.util.Respuesta;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ejb.EJB;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kendall
 */
@Path("/UsuariosController")
@Tag(name = "Usuarios", description = "Operaciones sobre Usuarios")
//@SecurityRequirement(name = "jwt-auth")

public class UsuariosController {

    @EJB
    UsuariosService usuariosService;

    @GET
    @Path("/usuario/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(description = "Obtiene un usuario por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Usuario encontrado", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = UsuariosDTO.class))),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content(mediaType = MediaType.TEXT_PLAIN)),
        @ApiResponse(responseCode = "500", description = "Error interno durante la obtención del usuario", content = @Content(mediaType = MediaType.TEXT_PLAIN))
    })
    public Response getUsuario(@Parameter(description = "ID del Usuario", required = true) @PathParam("id") Long id) {
        try {
            Respuesta res = usuariosService.getUsuario(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }

            UsuariosDTO usuarioDto = (UsuariosDTO) res.getResultado("Usuario");

            return Response.ok(usuarioDto).build();
        } catch (Exception ex) {
            Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, "Error obteniendo el usuario", ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el usuario").build();
        }
    }

    @GET
    @Path("/usuarios")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(description = "Obtiene todos los usuarios")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Usuarios encontrados", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = UsuariosDTO.class))),
        @ApiResponse(responseCode = "500", description = "Error interno durante la obtención de los usuarios", content = @Content(mediaType = MediaType.TEXT_PLAIN))
    })
    public Response getAllUsuarios() {
        try {
            Respuesta res = usuariosService.getAllUsuarios();
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }

            List<UsuariosDTO> usuariosList = (List<UsuariosDTO>) res.getResultado("Usuarios");

            return Response.ok(usuariosList).build();
        } catch (Exception ex) {
            Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, "Error obteniendo los usuarios", ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo los usuarios").build();
        }
    }

    @GET
    @Path("/activarUsuario/{usuario}")
    @Produces(MediaType.TEXT_HTML)
    public Response activarUsuario(@PathParam("usuario") String nombreUsuario) {
        try {
            Respuesta respuesta = usuariosService.activarUsuario(nombreUsuario);
            if (!respuesta.getEstado()) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("<html><body><h1>Usuario no encontrado</h1></body></html>")
                        .build();
            }

            // Cargar el archivo HTML desde el directorio de recursos
            String htmlFilePath = "plantilla/activacion_exitosa.html"; 
            String htmlContent = new String(Files.readAllBytes(Paths.get(htmlFilePath)));

            // Reemplazar el marcador con el nombre del usuario
            htmlContent = htmlContent.replace("[Usuario]", nombreUsuario);

            return Response.ok(htmlContent).build();

        } catch (Exception e) {
            Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, "");
            String htmlError = "<html><body><h1>Error activando el usuario</h1><p>" + e.getMessage() + "</p></body></html>";
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(htmlError)
                    .build();
        }
    }

    @PUT
    @Path("/actualizarEstado")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarEstado(UsuariosDTO usuarioDto) {
        try {
            Respuesta respuesta = usuariosService.actualizarEstadoUsuario(usuarioDto);
            if (!respuesta.getEstado()) {
                return Response.status(Response.Status.NOT_FOUND).entity(respuesta.getMensaje()).build();
            }
            return Response.ok(respuesta).build();
        } catch (Exception e) {
            Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, "Error actualizando el estado del usuario.", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Error actualizando el estado: " + e.getMessage() + "\"}")
                    .build();
        }
    }

}
