package cr.ac.una.tarea.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import cr.ac.una.securityws.controller.SecurityController;
import cr.ac.una.securityws.controller.SecurityController_Service;
import cr.ac.una.tarea.model.UsuariosDto;
import cr.ac.una.tarea.util.Respuesta;

public class UsuariosService {

    SecurityController securityWs;

    public Respuesta logIn(String user, String password) {

        try {
            SecurityController_Service service = new SecurityController_Service();
            securityWs = service.getSecurityControllerPort();
            securityWs.logIn(user, password);

            if (securityWs.logIn(user, password).equals(false)) {
                return new Respuesta(false, "Usuario o contraseña incorrecta", "Usuario o contraseña incorrecta");
            }
            UsuariosDto usuario = new UsuariosDto(
                    (cr.ac.una.securityws.controller.UsuariosDto) securityWs.logIn(user, password));
            return new Respuesta(true, "", "", "TarUsuario", usuario);
        } catch (Exception ex) {
            Logger.getLogger(UsuariosService.class.getName()).log(Level.SEVERE,
                    "Error obteniendo el usuario [" + user + "]", ex);
            return new Respuesta(false, "Error obteniendo el usuario.", "logIn" + ex.getMessage());

        }
    }

}
