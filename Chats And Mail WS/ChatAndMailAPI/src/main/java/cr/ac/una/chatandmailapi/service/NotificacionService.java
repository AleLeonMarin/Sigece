package cr.ac.una.chatandmailapi.service;

import cr.ac.una.chatandmailapi.model.Notificacion;
import cr.ac.una.chatandmailapi.model.NotificacionDTO;
import cr.ac.una.chatandmailapi.model.Variables;
import cr.ac.una.chatandmailapi.model.VariablesDTO;
import cr.ac.una.chatandmailapi.util.CodigoRespuesta;
import cr.ac.una.chatandmailapi.util.Respuesta;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
@LocalBean
public class NotificacionService {

    private static final Logger LOG = Logger.getLogger(NotificacionService.class.getName());

    @PersistenceContext(unitName = "SigeceUnaWsPU")
    private EntityManager em;

    public Respuesta obtenerNotificacionPorId(Long notId) {
        try {
            Query qryNotificacion = em.createNamedQuery("Notificacion.findByNotId", Notificacion.class);
            qryNotificacion.setParameter("notId", notId);

            Notificacion notificacion = (Notificacion) qryNotificacion.getSingleResult();
            em.refresh(notificacion);
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Notificacion", new NotificacionDTO(notificacion));

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe una notificación con el código ingresado.", "obtenerNotificacionPorId NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrió un error al consultar la notificación.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrió un error al consultar la notificación.", "obtenerNotificacionPorId NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrió un error al consultar la notificación.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrió un error al consultar la notificación.", "obtenerNotificacionPorId " + ex.getMessage());
        }
    }

public Respuesta guardarNotificacion(NotificacionDTO notificacionDto) {
    try {
        Notificacion notificacion;
        if (notificacionDto.getNotId() != null && notificacionDto.getNotId() > 0) {
            notificacion = em.find(Notificacion.class, notificacionDto.getNotId());
            if (notificacion == null) {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encontró la notificación a modificar.", "guardarNotificacion NoResultException");
            }
            notificacion.actualizar(notificacionDto);

            List<Variables> listaVariables = new ArrayList<>();
            for (VariablesDTO varDto : notificacionDto.getSisVariablesList()) {
                Variables variable = new Variables(varDto);  
                variable.setVarNotId(notificacion);
                listaVariables.add(variable);

                if (variable.getVarId() == null) {
                    em.persist(variable);  
                } else {
                    em.merge(variable); 
                }
            }
            notificacion.setSisVariablesList(listaVariables);
            notificacion = em.merge(notificacion); 
        } else {
      
            notificacion = new Notificacion(notificacionDto);

            List<Variables> listaVariables = new ArrayList<>();
            for (VariablesDTO varDto : notificacionDto.getSisVariablesList()) {
                Variables variable = new Variables(varDto); 
                variable.setVarNotId(notificacion);
                listaVariables.add(variable);
                em.persist(variable); 
            }
            notificacion.setSisVariablesList(listaVariables); 
            em.persist(notificacion);
        }
        em.flush();
        return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Notificacion", new NotificacionDTO(notificacion));  // Retornar DTO
    } catch (Exception ex) {
        LOG.log(Level.SEVERE, "Ocurrió un error al guardar la notificación.", ex);
        return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrió un error al guardar la notificación.", "guardarNotificacion " + ex.getMessage());
    }
}


    public Respuesta eliminarNotificacion(Long notId) {
        try {
            Notificacion notificacion = em.find(Notificacion.class, notId);
            if (notificacion == null) {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encontró la notificación a eliminar.", "eliminarNotificacion NoResultException");
            }
            em.remove(notificacion);
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "Notificación eliminada correctamente.", "");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrió un error al eliminar la notificación.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrió un error al eliminar la notificación.", "eliminarNotificacion " + ex.getMessage());
        }
    }
    
    public Respuesta obtenerTodasNotificaciones() {
        try {
            Query qryNotificaciones = em.createNamedQuery("Notificacion.findAll", Notificacion.class);
            List<Notificacion> notificaciones = qryNotificaciones.getResultList();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Notificaciones", notificaciones);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrió un error al consultar las notificaciones.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Error al consultar las notificaciones.", "obtenerTodasNotificaciones " + ex.getMessage());
        }
    }
}
