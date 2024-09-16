package cr.ac.una.mailapp.service;

import cr.ac.una.mailapp.model.NotificacionDTO;
import cr.ac.una.mailapp.util.Request;
import cr.ac.una.mailapp.util.Respuesta;
import jakarta.ws.rs.core.GenericType;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Servicio para la gestión de notificaciones.
 */
public class NotificacionService {

    public Respuesta getNotificacionById(Long id) {
        try {
            Request request = new Request("NotificacionesController/" + id);
            request.get();

            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }

            NotificacionDTO notificacion = (NotificacionDTO) request.readEntity(NotificacionDTO.class);
            return new Respuesta(true, "", "", "Notificacion", notificacion);

        } catch (Exception ex) {
            Logger.getLogger(NotificacionService.class.getName()).log(Level.SEVERE, "Error obteniendo la notificación.", ex);
            return new Respuesta(false, "Error obteniendo la notificación.", "getNotificacionById " + ex.getMessage());
        }
    }

    public Respuesta guardarNotificacion(NotificacionDTO notificacionDTO) {
        try {
            Request request = new Request("NotificacionesController/guardar");
            request.post(notificacionDTO);

            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }

            NotificacionDTO notificacionGuardada = (NotificacionDTO) request.readEntity(NotificacionDTO.class);
            return new Respuesta(true, "", "", "Notificacion", notificacionGuardada);

        } catch (Exception ex) {
            Logger.getLogger(NotificacionService.class.getName()).log(Level.SEVERE, "Error guardando la notificación.", ex);
            return new Respuesta(false, "Error guardando la notificación.", "guardarNotificacion " + ex.getMessage());
        }
    }

    public Respuesta eliminarNotificacion(Long id) {
        try {
            Request request = new Request("NotificacionesController/" + id);
            request.delete();

            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }

            return new Respuesta(true, "", "Notificación eliminada exitosamente.");

        } catch (Exception ex) {
            Logger.getLogger(NotificacionService.class.getName()).log(Level.SEVERE, "Error eliminando la notificación.", ex);
            return new Respuesta(false, "Error eliminando la notificación.", "eliminarNotificacion " + ex.getMessage());
        }
    }

    public Respuesta obtenerNotificaciones() {
        try {
            Request request = new Request("NotificacionesController/notificaciones");
            request.get();

            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }

            List<NotificacionDTO> notificaciones = (List<NotificacionDTO>) request.readEntity(new GenericType<List<NotificacionDTO>>() {});
            return new Respuesta(true, "", "", "Notificaciones", notificaciones);

        } catch (Exception ex) {
            Logger.getLogger(NotificacionService.class.getName()).log(Level.SEVERE, "Error obteniendo las notificaciones.", ex);
            return new Respuesta(false, "Error obteniendo las notificaciones.", "getAllNotificaciones " + ex.getMessage());
        }
    }
}
