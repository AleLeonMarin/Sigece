package cr.ac.una.mailapp.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import cr.ac.una.securityws.controller.SigeceSoapWS;
import cr.ac.una.securityws.controller.SigeceSoapWS_Service;
import cr.ac.una.mailapp.model.UsuariosDto;
import cr.ac.una.mailapp.util.Respuesta;

public class UsuariosService {

    SigeceSoapWS securityWs;

    public Respuesta logIn(String user, String password) {

        try {
            SigeceSoapWS_Service service = new SigeceSoapWS_Service();
            securityWs = service.getSigeceSoapWSPort();
            securityWs.logIn(user, password);

            UsuariosDto usuario = new UsuariosDto(
                    (cr.ac.una.securityws.controller.UsuariosDto) securityWs.logIn(user, password));
            return new Respuesta(true, "", "", "Usuario", usuario);
        } catch (Exception ex) {
            Logger.getLogger(UsuariosService.class.getName()).log(Level.SEVERE,
                    "Error obteniendo el usuario [" + user + "]", ex);
            return new Respuesta(false, "Error obteniendo el usuario.", "logIn" + ex.getMessage());

        }
    }

    public Respuesta saveUser(cr.ac.una.securityws.controller.UsuariosDto usuariosDto) {
        try {
            SigeceSoapWS_Service service = new SigeceSoapWS_Service();
            securityWs = service.getSigeceSoapWSPort();

            UsuariosDto usuarioDto = new UsuariosDto(
                    (cr.ac.una.securityws.controller.UsuariosDto) securityWs.saveUser(usuariosDto));
            return new Respuesta(true, "", "", "Usuario", usuarioDto);
        } catch (Exception ex) {
            Logger.getLogger(UsuariosService.class.getName()).log(Level.SEVERE,
                    "Error guardando el usuario ", ex);
            return new Respuesta(false, "Error guardando el usuario.", "saveUser" + ex.getMessage());
        }
    }

    public Respuesta getUser(Long id) {
        try {
            SigeceSoapWS_Service service = new SigeceSoapWS_Service();
            securityWs = service.getSigeceSoapWSPort();
            UsuariosDto usuarioDto = new UsuariosDto(
                    (cr.ac.una.securityws.controller.UsuariosDto) securityWs.getUser(id));
            return new Respuesta(true, "", "", "Usuario", usuarioDto);
        } catch (Exception ex) {
            Logger.getLogger(UsuariosService.class.getName()).log(Level.SEVERE,
                    "Error obteniendo el usuario ", ex);
            return new Respuesta(false, "Error obteniendo el usuario.", "getUser" + ex.getMessage());
        }
    }

    public Respuesta deleteUser(Long id) {
        try {
            SigeceSoapWS_Service service = new SigeceSoapWS_Service();
            securityWs = service.getSigeceSoapWSPort();
            return new Respuesta(true, "", "", "Usuario", securityWs.deleteUser(id));
        } catch (Exception ex) {
            Logger.getLogger(UsuariosService.class.getName()).log(Level.SEVERE,
                    "Error eliminando el usuario ", ex);
            return new Respuesta(false, "Error eliminando el usuario.", "deleteUser" + ex.getMessage());
        }
    }

}