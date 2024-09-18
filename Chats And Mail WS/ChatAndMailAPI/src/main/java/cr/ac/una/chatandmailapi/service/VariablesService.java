package cr.ac.una.chatandmailapi.service;

import cr.ac.una.chatandmailapi.model.Variables;
import cr.ac.una.chatandmailapi.model.VariablesDTO;
import cr.ac.una.chatandmailapi.util.CodigoRespuesta;
import cr.ac.una.chatandmailapi.util.Respuesta;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
@LocalBean
public class VariablesService {

    private static final Logger LOG = Logger.getLogger(VariablesService.class.getName());

    @PersistenceContext(unitName = "SigeceUnaWsPU")
    private EntityManager em;

    /**
     * Método para obtener una variable por su ID.
     */
    public Respuesta obtenerVariablePorId(Long varId) {
        try {
            Query qryVariable = em.createNamedQuery("Variables.findByVarId", Variables.class);
            qryVariable.setParameter("varId", varId);

            Variables variable = (Variables) qryVariable.getSingleResult();
            em.refresh(variable);
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Variable", new VariablesDTO(variable));

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe una variable con el ID ingresado.", "obtenerVariablePorId NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrió un error al consultar la variable.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrió un error al consultar la variable.", "obtenerVariablePorId " + ex.getMessage());
        }
    }

    /**
     * Método para guardar o actualizar una variable.
     */
    public Respuesta guardarVariable(VariablesDTO variablesDto) {
        try {
            Variables variable;
            if (variablesDto.getVarId() != null && variablesDto.getVarId() > 0) {
             
                variable = em.find(Variables.class, variablesDto.getVarId());
                if (variable == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encontró la variable a modificar.", "guardarVariable NoResultException");
                }
                variable.actualizar(variablesDto);
                variable = em.merge(variable);
            } else {
          
                variable = new Variables(variablesDto);
                em.persist(variable);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Variable", new VariablesDTO(variable));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrió un error al guardar la variable.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrió un error al guardar la variable.", "guardarVariable " + ex.getMessage());
        }
    }

    public Respuesta obtenerVariablesPorNotificacion(Long notId) {
        try {
            Query qryVariables = em.createQuery("SELECT v FROM Variables v WHERE v.varNotId.notId = :notId", Variables.class);
            qryVariables.setParameter("notId", notId);

            List<Variables> variables = qryVariables.getResultList();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Variables", variables);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrió un error al consultar las variables.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Error al consultar las variables.", "obtenerVariablesPorNotificacion " + ex.getMessage());
        }
    }
    
    public Respuesta eliminarVariable(Long id) {
    try {
        Variables variable = em.find(Variables.class, id);
        if (variable == null) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encontró la variable a eliminar.", "eliminarVariable NoResultException");
        }
        em.remove(variable);
        em.flush();
        return new Respuesta(true, CodigoRespuesta.CORRECTO, "Variable eliminada correctamente.", "");
    } catch (Exception ex) {
        LOG.log(Level.SEVERE, "Ocurrió un error al eliminar la variable.", ex);
        return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrió un error al eliminar la variable.", "eliminarVariable " + ex.getMessage());
    }
}

}
