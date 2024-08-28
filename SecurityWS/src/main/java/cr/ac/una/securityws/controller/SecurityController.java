package cr.ac.una.securityws.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import cr.ac.una.securityws.model.UsuariosDto;
import cr.ac.una.securityws.service.UsuariosService;
import cr.ac.una.securityws.util.Respuesta;
import jakarta.ejb.EJB;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

@WebService(name = "SecurityController")
public class SecurityController {

    @EJB
    UsuariosService usuariosService;

    @WebMethod(operationName = "logIn")

    public UsuariosDto logic(@WebParam(name = "usuario") String usuario, @WebParam(name = "clave") String clave) {
        try {
            Respuesta res = usuariosService.validateUser(usuario, clave);

            if (!res.getEstado()) {
                return (UsuariosDto) res.getResultado("Usuario");
            }

            UsuariosDto user = (UsuariosDto) res.getResultado("Usuario");
            return user;
        } catch (Exception e) {
            Logger.getLogger(SecurityController.class.getName()).log(Level.SEVERE, "Error en el login", e);
            return null;
        }
    }

}
