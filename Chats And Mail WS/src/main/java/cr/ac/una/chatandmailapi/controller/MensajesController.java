/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.chatandmailapi.controller;

import cr.ac.una.chatandmailapi.model.MensajesDTO;
import cr.ac.una.chatandmailapi.service.MensajesService;
import cr.ac.una.chatandmailapi.util.CodigoRespuesta;
import cr.ac.una.chatandmailapi.util.Respuesta;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ejb.EJB;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
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

@Tag(name = "Mensajes", description = "Operaciones sobre Mensajes")
//@SecurityRequirement(name = "jwt-auth")
@Path("/MensajesController")
public class MensajesController {
    
    @EJB
    MensajesService mensajesService;
    
@GET
@Path("/mensajes/{id}")
@Produces(MediaType.APPLICATION_JSON)
public Response getMensaje(@PathParam("id") Long id) {
    try {
        Respuesta res = mensajesService.getMensaje(id);
        if (!res.getEstado()) {
            return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
        }
        return Response.ok((MensajesDTO) res.getResultado("Mensaje")).build();
    } catch (Exception ex) {
        Logger.getLogger(ChatsController.class.getName()).log(Level.SEVERE, "Ocurrió un error al obtener el mensaje.", ex);
        return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el mensaje").build();
    }
}

@POST
@Path("/mensajes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Response guardarMensaje(MensajesDTO mensaje) {
    try {
        Respuesta res = mensajesService.guardarMensaje(mensaje);
        if (!res.getEstado()) {
            
            return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            
        }
        return Response.ok((MensajesDTO) res.getResultado("Mensaje")).build();
    } catch (Exception ex) {
        Logger.getLogger(MensajesController.class.getName()).log(Level.SEVERE, "Error guardando el mensaje.", ex);
        return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando el mensaje").build();
    }
}

@DELETE
@Path("/mensajes/{id}")
@Produces(MediaType.APPLICATION_JSON)
public Response eliminarMensaje(@PathParam("id") Long id) {
    try {
        Respuesta res = mensajesService.eliminarMensaje(id);
        if (!res.getEstado()) {
            return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
        }
        return Response.ok().build();  // Responder con éxito
    } catch (Exception ex) {
        Logger.getLogger(MensajesController.class.getName()).log(Level.SEVERE, "Ocurrió un error al eliminar el mensaje.", ex);
        return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error eliminando el mensaje").build();
    }
}

    
}
