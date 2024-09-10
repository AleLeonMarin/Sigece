package cr.ac.una.tarea.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import cr.ac.una.securityws.controller.SigeceSoapWS;
import cr.ac.una.securityws.controller.SigeceSoapWS_Service;
import cr.ac.una.tarea.model.UsuariosDto;
import cr.ac.una.tarea.util.Respuesta;

public class UsuariosService {

    SigeceSoapWS securityWs;

    public Respuesta logIn(String user, String password) {

        try {
            SigeceSoapWS_Service service = new SigeceSoapWS_Service();
            securityWs = service.getSigeceSoapWSPort();
            securityWs.logIn(user, password);

            if (securityWs.logIn(user, password).equals(false)) {
                return new Respuesta(false, "Usuario o contraseña incorrecta", "Usuario o contraseña incorrecta");
            }
            UsuariosDto usuario = new UsuariosDto(
                    (cr.ac.una.securityws.controller.UsuariosDto) securityWs.logIn(user, password));
            return new Respuesta(true, "", "", "Usuario", usuario);
        } catch (Exception ex) {
            Logger.getLogger(UsuariosService.class.getName()).log(Level.SEVERE,
                    "Error obteniendo el usuario [" + user + "]", ex);
            return new Respuesta(false, "Error obteniendo el usuario.", "logIn" + ex.getMessage());

        }
    }

}