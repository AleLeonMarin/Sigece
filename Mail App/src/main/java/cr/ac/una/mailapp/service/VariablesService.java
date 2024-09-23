package cr.ac.una.mailapp.service;

import cr.ac.una.mailapp.model.VariablesDTO;
import cr.ac.una.mailapp.util.Request;
import cr.ac.una.mailapp.util.Respuesta;
import jakarta.ws.rs.core.GenericType;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Servicio para la gestión de variables
 */
public class VariablesService {

    public Respuesta obtenerVariablesPorNotificacion(Long notId) {
        try {
            Request request = new Request("VariablesController/notificacion/" + notId);
            request.get();

            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }

            List<VariablesDTO> variables = (List<VariablesDTO>) request.readEntity(new GenericType<List<VariablesDTO>>() {});
            return new Respuesta(true, "", "", "Variables", variables);

        } catch (Exception ex) {
            Logger.getLogger(VariablesService.class.getName()).log(Level.SEVERE, "Error obteniendo las variables.", ex);
            return new Respuesta(false, "Error obteniendo las variables.", "obtenerVariablesPorNotificacion " + ex.getMessage());
        }
    }

    public Respuesta guardarVariable(VariablesDTO variableDto) {
        try {
            Request request = new Request("VariablesController/guardar");
            request.post(variableDto);

            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }

            VariablesDTO variableGuardada = (VariablesDTO) request.readEntity(VariablesDTO.class);
            return new Respuesta(true, "", "", "Variable", variableGuardada);

        } catch (Exception ex) {
            Logger.getLogger(VariablesService.class.getName()).log(Level.SEVERE, "Error guardando la variable.", ex);
            return new Respuesta(false, "Error guardando la variable.", "guardarVariable " + ex.getMessage());
        }
    }

    public Respuesta eliminarVariable(Long varId) {
        try {
            Request request = new Request("VariablesController/" + varId);
            request.delete();

            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }

            return new Respuesta(true, "", "", "Variable", null);

        } catch (Exception ex) {
            Logger.getLogger(VariablesService.class.getName()).log(Level.SEVERE, "Error eliminando la variable.", ex);
            return new Respuesta(false, "Error eliminando la variable.", "eliminarVariable " + ex.getMessage());
        }
    }
}
