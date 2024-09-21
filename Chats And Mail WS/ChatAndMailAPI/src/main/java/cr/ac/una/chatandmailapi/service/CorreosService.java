package cr.ac.una.chatandmailapi.service;

import cr.ac.una.chatandmailapi.model.Correos;
import cr.ac.una.chatandmailapi.model.CorreosDTO;
import cr.ac.una.chatandmailapi.model.Notificacion;
import cr.ac.una.chatandmailapi.model.Parametros;
import cr.ac.una.chatandmailapi.model.ParametrosDTO;
import cr.ac.una.chatandmailapi.util.CodigoRespuesta;
import cr.ac.una.chatandmailapi.util.Respuesta;
import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Schedule;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.ConstraintViolationException;
import java.util.Date;
import java.util.List;
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
    private ParametrosService parametroService;

    /**
     * Guarda o actualiza un correo en la base de datos.
     */
    public Respuesta guardarCorreo(CorreosDTO correosDto) {
        try {
            Correos correo;

            // Verifica si se trata de una actualización o una inserción nueva
            if (correosDto.getCorId() != null && correosDto.getCorId() > 0) {
                correo = em.find(Correos.class, correosDto.getCorId());
                if (correo == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encontró el correo a modificar.", "guardarCorreo NoResultException");
                }
                correo.actualizar(correosDto);
                correo = em.merge(correo);
            } else {
                // Si no existe, crea uno nuevo
                correo = new Correos(correosDto);
                
                // Relaciona la notificación si está presente en el DTO
                if (correosDto.getCorNotId() != null && correosDto.getCorNotId().getNotId() != null) {
                    Notificacion notificacion = em.find(Notificacion.class, correosDto.getCorNotId().getNotId());
                    if (notificacion != null) {
                        correo.setCorNotId(notificacion);
                    }
                }
                em.persist(correo);
            }
            em.flush(); // Asegura que la transacción se complete en la base de datos

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Correo", new CorreosDTO(correo));
        } catch (ConstraintViolationException ex) {
            // Logea los errores de validación
            ex.getConstraintViolations().forEach(violation -> 
                LOG.severe("Error de validación: " + violation.getPropertyPath() + " " + violation.getMessage()));
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Error al guardar el correo. Detalles: " + ex.getMessage(), "guardarCorreo " + ex.getMessage());
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrió un error al guardar el correo.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Error al guardar el correo.", "guardarCorreo " + ex.getMessage());
        }
    }
    
    /**
     * Envía un correo electrónico y luego lo guarda en la base de datos.
     */
    public Respuesta enviarCorreo(CorreosDTO correosDto) {
        try {
            // Llama al servicio de envío de correos
            String resultadoEnvio = emailService.enviarCorreoConEspera(correosDto.getCorDestinatario(), correosDto.getCorAsunto(), correosDto.getCorResultado());

            correosDto.setCorResultado(resultadoEnvio);
            correosDto.setCorFecha(new Date()); // Actualiza la fecha de envío

            // Guarda el correo en la base de datos después de enviarlo
            return guardarCorreo(correosDto);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrió un error al enviar el correo.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrió un error al enviar el correo.", "enviarCorreo " + ex.getMessage());
        }
    }

    /**
     * Busca un correo en la base de datos por su ID.
     */
    public Respuesta getCorreo(Long id) {
        try {
            Correos correo = em.find(Correos.class, id);
            if (correo == null) {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Correo no encontrado.", "getCorreo NoResultException");
            }
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Correo", new CorreosDTO(correo));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrió un error al obtener el correo.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrió un error al obtener el correo.", "getCorreo " + ex.getMessage());
        }
    }

    /**
     * Obtiene todos los correos almacenados en la base de datos.
     */
    public Respuesta obtenerTodosLosCorreos() {
        try {
            List<Correos> correos = em.createQuery("SELECT c FROM Correos c", Correos.class).getResultList();
            List<CorreosDTO> correosDTOList = correos.stream().map(CorreosDTO::new).toList();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Correos", correosDTOList);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrió un error al obtener los correos.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrió un error al obtener los correos.", "obtenerTodosLosCorreos " + ex.getMessage());
        }
    }
    
      public Respuesta persistirCorreo(CorreosDTO correosDto) {
        try {
            Correos correo = new Correos(correosDto);
            if (correosDto.getCorId() != null && correosDto.getCorId() > 0) {
                correo = em.merge(correo);
            } else {
                em.persist(correo);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Correo", new CorreosDTO(correo));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Error persistiendo el correo.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Error persistiendo el correo.", ex.getMessage());
        }
    }

    // Método para revisar y enviar correos pendientes (con estado "P")
    // Método para revisar y enviar un correo pendiente (con estado "P")
      

      
    @Schedule(hour = "*", minute = "*/0.5", persistent = false)  // Ejecuta el método cada minuto
public void enviarUnCorreoPendiente() {
    try {
        // Obtener el parámetro de correos por hora desde la base de datos
        Respuesta parametros = parametroService.getParametros(); 
        if (!parametros.getEstado()) {
            LOG.log(Level.SEVERE, "Error obteniendo parámetros de configuración.");
            return;  // Terminar si hay un error al obtener los parámetros
        }
        
        ParametrosDTO parametroDTO = (ParametrosDTO) parametros.getResultado("Parametros");
        int correosPorHora = (int) parametroDTO.getParTimeout();  // Obtener el valor de PAR_TIMEOUT

        if (correosPorHora <= 0) {
            LOG.log(Level.SEVERE, "El valor de correos por hora no es válido.");
            return;
        }

        // Calcular cuántos minutos deben pasar entre cada correo
        int intervaloEntreCorreos = 60 / correosPorHora;  // Minutos entre correos
        LOG.log(Level.INFO, "Se enviará 1 correo cada {0} minutos.", intervaloEntreCorreos);

        // Seleccionar el primer correo pendiente (uno solo)
        Correos correoPendiente = em.createQuery("SELECT c FROM Correos c WHERE c.corEstado = 'P'", Correos.class)
                                    .setMaxResults(1)  // Limitar la consulta a 1 resultado
                                    .getSingleResult();

        if (correoPendiente != null) {
            // Usar la plantilla HTML del correo pendiente
            String contenidoHtml = correoPendiente.getCorResultado();
            String destinatario = correoPendiente.getCorDestinatario();
            String asunto = correoPendiente.getCorAsunto();

            // Enviar el correo
            String resultadoEnvio = emailService.enviarCorreo(destinatario, asunto, contenidoHtml);

            // Si se envía correctamente, actualizar el estado
            if (resultadoEnvio.contains("exitosamente")) {
                correoPendiente.setCorEstado("E");  // Cambiar estado a "Enviado"
                em.merge(correoPendiente);  // Actualizar en la base de datos
                em.flush();  // Confirmar los cambios
            }
        }

    } catch (NoResultException ex) {
        // No hay correos pendientes
        LOG.log(Level.INFO, "No hay correos pendientes por enviar.");
    } catch (Exception ex) {
        LOG.log(Level.SEVERE, "Error enviando el correo pendiente.", ex);
    }
}

}
      
     
