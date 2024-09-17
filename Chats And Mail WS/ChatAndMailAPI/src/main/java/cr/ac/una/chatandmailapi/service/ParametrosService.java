package cr.ac.una.chatandmailapi.service;

import cr.ac.una.chatandmailapi.model.Parametros;
import cr.ac.una.chatandmailapi.model.ParametrosDTO;
import cr.ac.una.chatandmailapi.util.CodigoRespuesta;
import cr.ac.una.chatandmailapi.util.Respuesta;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
@LocalBean
public class ParametrosService {

    private static final Logger LOG = Logger.getLogger(ParametrosService.class.getName());

    @PersistenceContext(unitName = "SigeceUnaWsPU")
    private EntityManager em;

    public Respuesta guardarParametros(ParametrosDTO parametrosDto) {
        try {
            Parametros parametros = em.find(Parametros.class, parametrosDto.getParId());
            if (parametros == null) {
                parametros = new Parametros(parametrosDto);
                parametros.setParId(1);
                em.persist(parametros);
            } else {
                parametros.actualizar(parametrosDto);
                parametros = em.merge(parametros);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Parametros", new ParametrosDTO(parametros));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrió un error al guardar los parámetros.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrió un error al guardar los parámetros.", "guardarParametros " + ex.getMessage());
        }
    }

    public Respuesta getParametros() {
        try {
            Parametros parametros = em.find(Parametros.class, 1L); 
            if (parametros == null) {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encontraron los parámetros.", "getParametros NoResultException");
            }
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Parametros", new ParametrosDTO(parametros));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrió un error al obtener los parámetros.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrió un error al obtener los parámetros.", "getParametros " + ex.getMessage());
        }
    }
}