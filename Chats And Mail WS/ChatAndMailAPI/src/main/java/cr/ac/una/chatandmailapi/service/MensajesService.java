package cr.ac.una.chatandmailapi.service;

import cr.ac.una.chatandmailapi.model.Mensajes;
import cr.ac.una.chatandmailapi.model.MensajesDTO;
import cr.ac.una.chatandmailapi.model.Chats;
import cr.ac.una.chatandmailapi.util.CodigoRespuesta;
import cr.ac.una.chatandmailapi.util.Respuesta;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
@LocalBean
public class MensajesService {

    private static final Logger LOG = Logger.getLogger(MensajesService.class.getName());

    @PersistenceContext(unitName = "SigeceUnaWsPU")
    private EntityManager em;

    // Obtener un mensaje por ID
    public Respuesta getMensaje(Long id) {
        try {
            Mensajes mensaje = em.find(Mensajes.class, id);
            if (mensaje == null) {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encontró el mensaje con el ID proporcionado.", "getMensaje NoResultException");
            }
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Mensaje", new MensajesDTO(mensaje));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrió un error al consultar el mensaje.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrió un error al consultar el mensaje.", "getMensaje " + ex.getMessage());
        }
    }

    // Obtener todos los mensajes de un chat
    public Respuesta getMensajesByChat(Long chatId) {
        try {
            Query qryMensajes = em.createNamedQuery("Mensajes.findBySmsChatId", Mensajes.class);
            qryMensajes.setParameter("smsChatId", chatId);
            em.refresh(chatId);
            List<Mensajes> mensajes = qryMensajes.getResultList();
            List<MensajesDTO> mensajesDto = new ArrayList<>();
            for (Mensajes mensaje : mensajes) {
                mensajesDto.add(new MensajesDTO(mensaje));
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Mensajes", mensajesDto);
        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encontraron mensajes para este chat.", "getMensajesByChat NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrió un error al consultar los mensajes del chat.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrió un error al consultar los mensajes del chat.", "getMensajesByChat " + ex.getMessage());
        }
    }

    // Guardar o actualizar un mensaje
   public Respuesta guardarMensaje(MensajesDTO mensajeDto) {
    try {
        Mensajes mensaje;
        if (mensajeDto.getSmsId() != null && mensajeDto.getSmsId() > 0) {
            mensaje = em.find(Mensajes.class, mensajeDto.getSmsId());
            if (mensaje == null) {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encontró el mensaje a modificar.", "guardarMensaje NoResultException");
            }
            mensaje.actualizar(mensajeDto);
            mensaje = em.merge(mensaje);
        } else {
            mensaje = new Mensajes(mensajeDto);
            em.persist(mensaje);
        }
        em.flush();
        return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Mensaje", new MensajesDTO(mensaje));
    } catch (jakarta.validation.ConstraintViolationException ex) {
        ex.getConstraintViolations().forEach(violation -> {
            LOG.log(Level.SEVERE, "Validation error in field {0}: {1}", new Object[]{violation.getPropertyPath(), violation.getMessage()});
        });
        return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Error de validación al guardar el mensaje.", "guardarMensaje " + ex.getMessage());
    } catch (Exception ex) {
        LOG.log(Level.SEVERE, "Ocurrió un error al guardar el mensaje.", ex);
        return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrió un error al guardar el mensaje.", "guardarMensaje " + ex.getMessage());
    }
}


   
    

    // Enviar mensajes a un chat
    public Respuesta enviarMensajes(List<MensajesDTO> mensajesDtoList) {
        try {
            List<Mensajes> mensajes = new ArrayList<>();
            for (MensajesDTO mensajeDto : mensajesDtoList) {
                Mensajes mensaje = new Mensajes(mensajeDto);
                em.persist(mensaje);
                mensajes.add(mensaje);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Mensajes", mensajesDtoList);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrió un error al enviar los mensajes.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrió un error al enviar los mensajes.", "enviarMensajes " + ex.getMessage());
        }
    }
   
    
    public Respuesta eliminarMensaje(Long id) {
        try {
            Mensajes mensaje = em.find(Mensajes.class, id);
            if (mensaje == null) {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encontró el mensaje a eliminar.", "eliminarMensaje NoResultException");
            }
            em.remove(mensaje);
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrió un error al eliminar el mensaje.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrió un error al eliminar el mensaje.", "eliminarMensaje " + ex.getMessage());
        }
    }

}
