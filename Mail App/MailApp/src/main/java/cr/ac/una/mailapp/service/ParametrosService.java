package cr.ac.una.mailapp.service;

import cr.ac.una.mailapp.model.ParametrosDTO;
import cr.ac.una.mailapp.util.Request;
import cr.ac.una.mailapp.util.Respuesta;
import jakarta.ws.rs.core.GenericType;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Servicio para la gestión de parámetros
 */
public class ParametrosService {

    public Respuesta getParametros() {
        try {
            Request request = new Request("ParametrosController/obtener");
            request.get();

            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }

            ParametrosDTO parametros = (ParametrosDTO) request.readEntity(ParametrosDTO.class);
            return new Respuesta(true, "", "", "Parametros", parametros);

        } catch (Exception ex) {
            Logger.getLogger(ParametrosService.class.getName()).log(Level.SEVERE, "Error obteniendo los parámetros.", ex);
            return new Respuesta(false, "Error obteniendo los parámetros.", "getParametros " + ex.getMessage());
        }
    }

    public Respuesta guardarParametros(ParametrosDTO parametrosDTO) {
        try {
            Request request = new Request("ParametrosController/guardar");
            request.post(parametrosDTO);

            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }

            ParametrosDTO parametrosGuardados = (ParametrosDTO) request.readEntity(ParametrosDTO.class);
            return new Respuesta(true, "", "", "Parametros", parametrosGuardados);

        } catch (Exception ex) {
            Logger.getLogger(ParametrosService.class.getName()).log(Level.SEVERE, "Error guardando los parámetros.", ex);
            return new Respuesta(false, "Error guardando los parámetros.", "guardarParametros " + ex.getMessage());
        }
    }
}
