package cr.ac.una.mailapp.service;

import cr.ac.una.mailapp.model.CorreosDTO;
import cr.ac.una.mailapp.util.Request;
import cr.ac.una.mailapp.util.Respuesta;
import jakarta.ws.rs.core.GenericType;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CorreosService {

    private static final Logger LOG = Logger.getLogger(CorreosService.class.getName());


    public Respuesta guardarCorreo(CorreosDTO correoDto) {
        try {
            Request request = new Request("correos/guardar");
            request.post(correoDto);

            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }

            CorreosDTO correoGuardado = (CorreosDTO) request.readEntity(CorreosDTO.class);
            return new Respuesta(true, "", "", "Correo", correoGuardado);

        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Error guardando el correo.", ex);
            return new Respuesta(false, "Error guardando el correo.", "guardarCorreo " + ex.getMessage());
        }
    }

    public Respuesta getCorreo(Long id) {
        try {
            Request request = new Request("correos/" + id);
            request.get();

            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }

            CorreosDTO correo = (CorreosDTO) request.readEntity(CorreosDTO.class);
            return new Respuesta(true, "", "", "Correo", correo);

        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Error obteniendo el correo.", ex);
            return new Respuesta(false, "Error obteniendo el correo.", "getCorreo " + ex.getMessage());
        }
    }


    public Respuesta enviarCorreosPendientes() {
        try {
            Request request = new Request("correos/enviarPendientes");
            request.post(null);

            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }

            List<CorreosDTO> correosEnviados = (List<CorreosDTO>) request.readEntity(new GenericType<List<CorreosDTO>>() {});
            return new Respuesta(true, "", "", "Correos", correosEnviados);

        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Error enviando correos pendientes.", ex);
            return new Respuesta(false, "Error enviando correos pendientes.", "enviarCorreosPendientes " + ex.getMessage());
        }
    }
}
