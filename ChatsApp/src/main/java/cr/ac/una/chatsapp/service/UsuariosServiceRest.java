package cr.ac.una.chatsapp.service;

import cr.ac.una.chatsapp.model.UsuariosDTO;
import cr.ac.una.chatsapp.util.Request;
import cr.ac.una.chatsapp.util.Respuesta;

import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuariosServiceRest {

    public Respuesta actualizarEstadoUsuario(UsuariosDTO usuarioDto) {
        try {
            Request request = new Request("UsuariosController/actualizarEstado");
            request.put(usuarioDto);

            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }

            UsuariosDTO usuarioActualizado = (UsuariosDTO) request.readEntity(UsuariosDTO.class);
            return new Respuesta(true, "", "", "Usuario", usuarioActualizado);

        } catch (Exception ex) {
            Logger.getLogger(UsuariosService.class.getName()).log(Level.SEVERE, "Error actualizando el estado del usuario.", ex);
            return new Respuesta(false, "Error actualizando el estado del usuario.", "actualizarEstadoUsuario " + ex.getMessage());
        }
    }

}