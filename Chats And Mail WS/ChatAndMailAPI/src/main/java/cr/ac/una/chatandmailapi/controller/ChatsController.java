/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.chatandmailapi.controller;

import cr.ac.una.chatandmailapi.model.ChatsDTO;
import cr.ac.una.chatandmailapi.model.MensajesDTO;
import cr.ac.una.chatandmailapi.model.UsuariosDTO;
import cr.ac.una.chatandmailapi.service.ChatsService;
import cr.ac.una.chatandmailapi.service.MensajesService;
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
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kendall Fonseca
 */
@Path("/ChatsController")
@Tag(name = "Chats", description = "Operaciones sobre Chats")
//@SecurityRequirement(name = "jwt-auth")

public class ChatsController {

    private static final Logger LOG = Logger.getLogger(ChatsController.class.getName());

    @EJB
    ChatsService chatsService;

    @GET
    @Path("/chat/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(description = "Obtiene un chat por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Chat encontrado", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ChatsDTO.class))),
            @ApiResponse(responseCode = "404", description = "Chat no encontrado", content = @Content(mediaType = MediaType.TEXT_PLAIN)),
            @ApiResponse(responseCode = "500", description = "Error interno durante la consulta", content = @Content(mediaType = MediaType.TEXT_PLAIN))
    })
    public Response getChat(@Parameter(description = "ID del Chat", required = true) @PathParam("id") Long id) {
        try {
            Respuesta res = chatsService.getChat(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((ChatsDTO) res.getResultado("Chat")).build();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Error obteniendo el chat", ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el chat").build();
        }
    }
    
   


    @GET
    @Path("/chats")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(description = "Obtiene todos los chats")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Chats obtenidos", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ChatsDTO.class))),
            @ApiResponse(responseCode = "500", description = "Error interno durante la consulta", content = @Content(mediaType = MediaType.TEXT_PLAIN))
    })
    public Response getChats() {
        try {
            Respuesta res = chatsService.getChats();
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(new GenericEntity<List<ChatsDTO>>((List<ChatsDTO>) res.getResultado("Chats")) {}).build();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Error obteniendo los chats", ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo los chats").build();
        }
    }

    @POST
    @Path("/chat")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    
    public Response guardarChat(ChatsDTO chatDto) {
        try {
            Respuesta res = chatsService.guardarChat(chatDto);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((ChatsDTO) res.getResultado("Chat")).build();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Error guardando el chat", ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando el chat").build();
        }
    }

    @DELETE
    @Path("/chat/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(description = "Elimina un chat por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Chat eliminado"),
            @ApiResponse(responseCode = "404", description = "Chat no encontrado", content = @Content(mediaType = MediaType.TEXT_PLAIN)),
            @ApiResponse(responseCode = "500", description = "Error interno durante la eliminación", content = @Content(mediaType = MediaType.TEXT_PLAIN))
    })
    public Response eliminarChat(@PathParam("id") Long id) {
        try {
            Respuesta res = chatsService.eliminarChat(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok().build();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Error eliminando el chat", ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error eliminando el chat").build();
        }
    }

    @GET
    @Path("/chats/usuario/{usuarioId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(description = "Obtiene todos los chats de un usuario")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Chats del usuario obtenidos", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ChatsDTO.class))),
            @ApiResponse(responseCode = "500", description = "Error obteniendo los chats del usuario", content = @Content(mediaType = MediaType.TEXT_PLAIN))
    })
    public Response getChatsByUsuario(@PathParam("usuarioId") Long usuarioId) {
        try {
            Respuesta res = chatsService.getChatsByUsuario(usuarioId);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(new GenericEntity<List<ChatsDTO>>((List<ChatsDTO>) res.getResultado("Chats")) {}).build();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Error obteniendo los chats del usuario", ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo los chats del usuario").build();
        }
    }
    


}
