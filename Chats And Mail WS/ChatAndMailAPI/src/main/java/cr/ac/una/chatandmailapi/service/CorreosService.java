package cr.ac.una.chatandmailapi.service;

import cr.ac.una.chatandmailapi.model.Correos;
import cr.ac.una.chatandmailapi.model.CorreosDTO;
import cr.ac.una.chatandmailapi.util.CodigoRespuesta;
import cr.ac.una.chatandmailapi.util.Respuesta;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
@LocalBean
public class CorreosService {

    private static final Logger LOG = Logger.getLogger(CorreosService.class.getName());

    @PersistenceContext(unitName = "SigeceUnaWsPU")
    private EntityManager em;

    private final EmailService emailService = new EmailService();  // Instancia de EmailService

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
                em.persist(correo);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Correo", new CorreosDTO(correo));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrió un error al guardar el correo.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrió un error al guardar el correo.", "guardarCorreo " + ex.getMessage());
        }
    }

    public Respuesta getCorreo(Long id) {
        try {
            Correos correo = em.find(Correos.class, id);
            if (correo == null) {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encontró el correo.", "getCorreo NoResultException");
            }
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Correo", new CorreosDTO(correo));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrió un error al obtener el correo.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrió un error al obtener el correo.", "getCorreo " + ex.getMessage());
        }
    }

 
    public Respuesta enviarCorreo(CorreosDTO correosDto) {
        try {
     
            String resultadoEnvio = emailService.enviarCorreo(correosDto.getCorDestinatario(), correosDto.getCorAsunto(), correosDto.getCorResultado());

    
            correosDto.setCorResultado(resultadoEnvio);
            correosDto.setCorFecha(new Date());
            return guardarCorreo(correosDto);  
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrió un error al enviar el correo.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrió un error al enviar el correo.", "enviarCorreo " + ex.getMessage());
        }
    }
}
