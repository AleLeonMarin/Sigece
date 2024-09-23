package cr.ac.una.chatsapp.service;

import cr.ac.una.chatsapp.model.ChatsDTO;
import cr.ac.una.chatsapp.model.UsuariosDTO;
import cr.ac.una.chatsapp.util.Request;
import cr.ac.una.chatsapp.util.Respuesta;
import jakarta.ws.rs.core.GenericType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatsService {

    public Respuesta getUsuarios() {
        try {
            Request request = new Request("UsuariosController/usuarios");
            request.get();

            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }

            List<UsuariosDTO> usuariosList = (List<UsuariosDTO>) request.readEntity(new GenericType<List<UsuariosDTO>>() {});

            return new Respuesta(true, "", "", "Usuarios", usuariosList);
        } catch (Exception ex) {
            Logger.getLogger(ChatsService.class.getName()).log(Level.SEVERE, "Error obteniendo la lista de usuarios.", ex);
            return new Respuesta(false, "Error obteniendo la lista de usuarios.", "getUsuarios " + ex.getMessage());
        }
    }

    public Respuesta getChatsEntreUsuarios(Long idEmisor, Long idReceptor) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("idEmisor", idEmisor);
            parametros.put("idReceptor", idReceptor);

            Request request = new Request("ChatsController/chats", "/{idEmisor}/{idReceptor}", parametros);
            request.get();

            List<ChatsDTO> chatsList = (List<ChatsDTO>) request.readEntity(new GenericType<List<ChatsDTO>>() {});

            return new Respuesta(true, "", "", "Chats", chatsList);
        } catch (Exception ex) {
            Logger.getLogger(ChatsService.class.getName()).log(Level.SEVERE, "Error obteniendo los chats entre usuarios [idEmisor: " + idEmisor + ", idReceptor: " + idReceptor + "]", ex);
            return new Respuesta(false, "Error obteniendo los chats entre usuarios.", "getChatsEntreUsuarios " + ex.getMessage());
        }
    }

    public Respuesta getChatsByUsuario(Long usuarioId) {
        try {
            Request request = new Request("ChatsController/chats/usuario/" + usuarioId);
            request.get();

            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }

            List<ChatsDTO> chatsList = (List<ChatsDTO>) request.readEntity(new GenericType<List<ChatsDTO>>() {});

            return new Respuesta(true, "", "", "Chats", chatsList);
        } catch (Exception ex) {
            Logger.getLogger(ChatsService.class.getName()).log(Level.SEVERE, "Error obteniendo los chats del usuario con ID: " + usuarioId, ex);
            return new Respuesta(false, "Error obteniendo los chats del usuario.", "getChatsByUsuario " + ex.getMessage());
        }
    }

    public Respuesta guardarChat(ChatsDTO chatDto) {
        try {
            Request request = new Request("ChatsController/chat");
            request.post(chatDto);

            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }

            ChatsDTO chatGuardado = (ChatsDTO) request.readEntity(ChatsDTO.class);
            return new Respuesta(true, "", "", "Chat", chatGuardado);
        } catch (Exception ex) {
            Logger.getLogger(ChatsService.class.getName()).log(Level.SEVERE, "Error guardando el chat.", ex);
            return new Respuesta(false, "Error guardando el chat.", "guardarChat " + ex.getMessage());
        }
    }

    public Respuesta eliminarChat(String idChat) {
        try {
            Request request = new Request("ChatsController/chat/" + idChat);
            request.delete();

            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }

            return new Respuesta(true, "", "", "Chat eliminado correctamente.", null);
        } catch (Exception ex) {
            Logger.getLogger(ChatsService.class.getName()).log(Level.SEVERE, "Error eliminando el chat con ID: " + idChat, ex);
            return new Respuesta(false, "Error eliminando el chat.", "eliminarChat " + ex.getMessage());
        }
    }
}
