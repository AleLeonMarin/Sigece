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
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
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

}
