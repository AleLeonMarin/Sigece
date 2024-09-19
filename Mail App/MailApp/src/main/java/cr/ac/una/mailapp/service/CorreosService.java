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

    public Respuesta enviarCorreo(CorreosDTO correoDto) {
        try {
            Request request = new Request("correos/enviar");
            request.post(correoDto);

            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }

            CorreosDTO correoEnviado = (CorreosDTO) request.readEntity(CorreosDTO.class);
            return new Respuesta(true, "", "", "Correo", correoEnviado);

        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Error enviando el correo.", ex);
            return new Respuesta(false, "Error enviando el correo.", "enviarCorreo " + ex.getMessage());
        }
    }


    public Respuesta enviarCorreoConEspera(CorreosDTO correoDto) {
        try {
            Request request = new Request("correos/enviarConEspera");
            request.post(correoDto);

            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }

            CorreosDTO correoEnviado = (CorreosDTO) request.readEntity(CorreosDTO.class);
            return new Respuesta(true, "", "", "Correo", correoEnviado);

        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Error enviando el correo con espera.", ex);
            return new Respuesta(false, "Error enviando el correo con espera.", "enviarCorreoConEspera " + ex.getMessage());
        }
    }


    public Respuesta obtenerCorreos() {
        try {
            Request request = new Request("correos/todos");
            request.get();

            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }

            List<CorreosDTO> correosList = (List<CorreosDTO>) request.readEntity(new GenericType<List<CorreosDTO>>() {});
            return new Respuesta(true, "", "", "Correos", correosList);

        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Error obteniendo los correos.", ex);
            return new Respuesta(false, "Error obteniendo los correos.", "obtenerCorreos " + ex.getMessage());
        }
    }
}
