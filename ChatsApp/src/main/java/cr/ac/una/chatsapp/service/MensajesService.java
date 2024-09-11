package cr.ac.una.chatsapp.service;

import cr.ac.una.chatsapp.model.MensajesDTO;
import cr.ac.una.chatsapp.util.Request;
import cr.ac.una.chatsapp.util.Respuesta;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MensajesService {

    public Respuesta guardarMensaje(MensajesDTO mensajeDto) {
        try {
            Request request = new Request("MensajesController/mensajes");
            request.post(mensajeDto);

            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }

            MensajesDTO mensajeGuardado = (MensajesDTO) request.readEntity(MensajesDTO.class);

            return new Respuesta(true, "", "", "Mensaje", mensajeGuardado);
        } catch (Exception ex) {
            Logger.getLogger(MensajesService.class.getName()).log(Level.SEVERE, "Ocurrió un error al guardar el mensaje.", ex);
            return new Respuesta(false, "Ocurrió un error al guardar el mensaje.", "guardarMensaje " + ex.getMessage());
        }
    }

    public Respuesta eliminarMensaje(Long id) {
        try {
            Request request = new Request("MensajesController/mensajes/" + id);
            request.delete();

            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }

            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            Logger.getLogger(MensajesService.class.getName()).log(Level.SEVERE, "Ocurrió un error al eliminar el mensaje.", ex);
            return new Respuesta(false, "Ocurrió un error al eliminar el mensaje.", "eliminarMensaje " + ex.getMessage());
        }
    }


}
