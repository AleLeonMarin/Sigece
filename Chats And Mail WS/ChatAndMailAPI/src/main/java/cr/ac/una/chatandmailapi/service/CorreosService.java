package cr.ac.una.chatandmailapi.service;

import cr.ac.una.chatandmailapi.model.Correos;
import cr.ac.una.chatandmailapi.model.CorreosDTO;
import cr.ac.una.chatandmailapi.model.Notificacion;
import cr.ac.una.chatandmailapi.util.CodigoRespuesta;
import cr.ac.una.chatandmailapi.util.Respuesta;
import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.ConstraintViolationException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
@LocalBean
public class CorreosService {

    private static final Logger LOG = Logger.getLogger(CorreosService.class.getName());

    @PersistenceContext(unitName = "SigeceUnaWsPU")
    private EntityManager em;
    
    @EJB
    private EmailService emailService;


    
    public Respuesta guardarCorreo(CorreosDTO correosDto) {
    try {
        Correos correo;

        if (correosDto.getCorId() != null && correosDto.getCorId() > 0) {
            correo = em.find(Correos.class, correosDto.getCorId());
            if (correo == null) {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encontró el correo a modificar.", "guardarCorreo NoResultException");
            }
            correo.actualizar(correosDto);
            correo = em.merge(correo);
        } else {
            correo = new Correos(correosDto);
            
          
            if (correosDto.getCorNotId() != null && correosDto.getCorNotId().getNotId() != null) {
                Notificacion notificacion = em.find(Notificacion.class, correosDto.getCorNotId().getNotId());
                if (notificacion != null) {
                    correo.setCorNotId(notificacion);
                }
            }
            
            em.persist(correo);
        }
        em.flush(); // Asegurar que se ejecute la transacción en la base de datos

        return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Correo", new CorreosDTO(correo));
    } catch (ConstraintViolationException ex) {
        // Loguear los errores específicos de validación
        ex.getConstraintViolations().forEach(violation -> 
            LOG.severe("Error de validación: " + violation.getPropertyPath() + " " + violation.getMessage()));
        return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrió un error al guardar el correo. Detalles: " + ex.getMessage(), "guardarCorreo " + ex.getMessage());
    } catch (Exception ex) {
        LOG.log(Level.SEVERE, "Ocurrió un error al guardar el correo.", ex);
        return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrió un error al guardar el correo.", "guardarCorreo " + ex.getMessage());
    }
}
    

    public Respuesta enviarCorreo(CorreosDTO correosDto) {
        try {
            String resultadoEnvio = emailService.enviarCorreoConEspera(correosDto.getCorDestinatario(), correosDto.getCorAsunto(), correosDto.getCorResultado());

            correosDto.setCorResultado(resultadoEnvio);
            correosDto.setCorFecha(new Date());

         
            return guardarCorreo(correosDto);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrió un error al enviar el correo.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrió un error al enviar el correo.", "enviarCorreo " + ex.getMessage());
        }
    }
}