package cr.ac.una.chatsapp.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import cr.ac.una.chatsapp.model.UsuariosDTO;
import cr.ac.una.chatsapp.util.Respuesta;
import cr.ac.una.securityws.controller.SigeceSoapWS;
import cr.ac.una.securityws.controller.SigeceSoapWS_Service;



public class UsuariosService {

    SigeceSoapWS securityWs;

    public cr.ac.una.chatsapp.util.Respuesta logIn(String user, String password) {

        try {
            SigeceSoapWS_Service service = new SigeceSoapWS_Service();
            securityWs = service.getSigeceSoapWSPort();
            securityWs.logIn(user, password);

            UsuariosDTO usuario = new UsuariosDTO(
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

            UsuariosDTO usuarioDto = new UsuariosDTO(
                    (cr.ac.una.securityws.controller.UsuariosDto) securityWs.saveUser(usuariosDto));
            return new Respuesta(true, "", "", "Usuario", usuarioDto);
        } catch (Exception ex) {
            Logger.getLogger(UsuariosService.class.getName()).log(Level.SEVERE,
                    "Error guardando el usuario ", ex);
            return new Respuesta(false, "Error guardando el usuario.", "saveUser" + ex.getMessage());
        }
    }

}