package cr.ac.una.chatandmailapi.service;

import cr.ac.una.chatandmailapi.model.Correos;
import cr.ac.una.chatandmailapi.model.CorreosDTO;
import cr.ac.una.chatandmailapi.model.Notificacion;
import cr.ac.una.chatandmailapi.model.Parametros;
import cr.ac.una.chatandmailapi.model.ParametrosDTO;
import cr.ac.una.chatandmailapi.model.UsuariosDTO;
import cr.ac.una.chatandmailapi.util.CodigoRespuesta;
import cr.ac.una.chatandmailapi.util.Respuesta;
import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Schedule;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
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
    @EJB
    private ParametrosService parametroService;

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
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Correo", new CorreosDTO(correo));
        } catch (ConstraintViolationException ex) {
            ex.getConstraintViolations().forEach(violation
                    -> LOG.severe("Error de validación: " + violation.getPropertyPath() + " " + violation.getMessage()));
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Error al guardar el correo. Detalles: " + ex.getMessage(), "guardarCorreo " + ex.getMessage());
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrió un error al guardar el correo.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Error al guardar el correo.", "guardarCorreo " + ex.getMessage());
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

    public Respuesta obtenerTodosLosCorreos() {
        try {
            Query qrycorreos = em.createQuery("SELECT c FROM Correos c", Correos.class);
            List<CorreosDTO> correosDTOList = qrycorreos.getResultList();
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

    @Schedule(hour = "*", minute = "*", second = "30", persistent = false)
    public void enviarUnCorreoPendiente() {
        try {
            Respuesta parametros = parametroService.getParametros();
            if (!parametros.getEstado()) {
                LOG.log(Level.SEVERE, "Error obteniendo parámetros de configuración.");
                return;
            }

            ParametrosDTO parametroDTO = (ParametrosDTO) parametros.getResultado("Parametros");
            int correosPorHora = (int) parametroDTO.getParTimeout();

            if (correosPorHora <= 0) {
                LOG.log(Level.SEVERE, "El valor de correos por hora no es válido.");
                return;
            }

            int intervaloEntreCorreos = (60 / correosPorHora) * 60 * 1000;
            LOG.log(Level.INFO, "Se enviará 1 correo cada {0} minutos.", (60 / correosPorHora));

            Correos correoPendiente = em.createQuery("SELECT c FROM Correos c WHERE c.corEstado = 'P'", Correos.class)
                    .setMaxResults(1)
                    .getSingleResult();

            if (correoPendiente != null) {
                String contenidoHtml = correoPendiente.getCorResultado();
                String destinatario = correoPendiente.getCorDestinatario();
                String asunto = correoPendiente.getCorAsunto();

                String resultadoEnvio = emailService.enviarCorreo(destinatario, asunto, contenidoHtml);

                if (resultadoEnvio.contains("exitosamente")) {
                    correoPendiente.setCorEstado("E");
                    em.merge(correoPendiente);
                    em.flush();

                    Thread.sleep(intervaloEntreCorreos - 30000);
                }
            }

        } catch (NoResultException ex) {
            LOG.log(Level.INFO, "No hay correos pendientes por enviar.");
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt(); // Restablecer el estado de interrupción
            LOG.log(Level.SEVERE, "La tarea fue interrumpida.", ex);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Error enviando el correo pendiente.", ex);
        }

    }

    public Respuesta eliminarCorreo(Long id) {
        try {
            Correos correo = em.find(Correos.class, id);
            if (correo == null) {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Correo no encontrado.", "eliminarCorreo");
            }

            em.remove(correo);
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "Correo eliminado correctamente.", "");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Error eliminando el correo.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Error eliminando el correo.", "eliminarCorreo " + ex.getMessage());
        }
    }

    public Respuesta enviarCorreoAhora(CorreosDTO correosDto) {
        try {
            Correos correo;

            // Verificar si el correo ya existe en la base de datos
            if (correosDto.getCorId() != null && correosDto.getCorId() > 0) {
                correo = em.find(Correos.class, correosDto.getCorId());
                if (correo == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encontró el correo a enviar.", "enviarCorreoAhora");
                }
            } else {
                // Si es un correo nuevo, lo creamos
                correo = new Correos(correosDto);
                em.persist(correo);  // Persistir el nuevo correo
                em.flush();
            }

            // Enviar el correo
            String resultadoEnvio = emailService.enviarCorreo(correo.getCorDestinatario(), correo.getCorAsunto(), correo.getCorResultado());

            // Verificar si el correo fue enviado correctamente
            if (resultadoEnvio.contains("exitosamente")) {
                correo.setCorEstado("E"); // Cambiar el estado a 'Enviado'
                em.merge(correo);  // Guardar los cambios en la base de datos
                em.flush();

                return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Correo", new CorreosDTO(correo));
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Error al enviar el correo.", "enviarCorreoAhora");
            }
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Error enviando el correo de inmediato.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Error enviando el correo de inmediato.", "enviarCorreoAhora");
        }
    }
    
    public Respuesta enviarCorreoActivacion(UsuariosDTO usuario) {
    try {
        // Obtener la notificación de activación (ID = 3)
        Notificacion notificacion = em.find(Notificacion.class, 3L);
        if (notificacion == null) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encontró la notificación de activación.", "enviarCorreoActivacion NoResultException");
        }

        // Reemplazar la variable [Usuario] en el contenido HTML
        String contenidoHtml = notificacion.getNotPlantilla()
                .replace("[Usuario]", usuario.getUsuUsuario());

        // Crear el correo a persistir
        CorreosDTO correoDto = new CorreosDTO();
        correoDto.setCorAsunto("Activación de cuenta SigeceUna");
        correoDto.setCorDestinatario(usuario.getUsuCorreo());
        correoDto.setCorResultado(contenidoHtml);
        correoDto.setCorNotId(notificacion); // Asignamos la notificación al correo
        correoDto.setCorEstado("E"); // Estado inicial pendiente
        correoDto.setCorFecha(new Date());
        correoDto.setCorVersion(Long.MIN_VALUE);

        // Persistir el correo
        Respuesta respuestaGuardarCorreo = guardarCorreo(correoDto);
        if (!respuestaGuardarCorreo.getEstado()) {
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Error al guardar el correo de activación.", "enviarCorreoActivacion " + respuestaGuardarCorreo.getMensaje());
        }

        // Enviar el correo utilizando el servicio de email
        String resultadoEnvio = emailService.enviarCorreo(correoDto.getCorDestinatario(), correoDto.getCorAsunto(), contenidoHtml);
        if (resultadoEnvio.contains("exitosamente")) {
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "Correo de activación enviado exitosamente.", "", "Correo", correoDto);
        } else {
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Error enviando el correo de activación: " + resultadoEnvio, "enviarCorreoActivacion");
        }

    } catch (Exception ex) {
        LOG.log(Level.SEVERE, "Ocurrió un error al enviar el correo de activación.", ex);
        return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrió un error al enviar el correo de activación.", "enviarCorreoActivacion " + ex.getMessage());
    }
}

}
