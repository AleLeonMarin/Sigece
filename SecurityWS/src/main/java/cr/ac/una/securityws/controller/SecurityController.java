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

@WebService(serviceName = "SecurityController")
public class SecurityController {

    @EJB
    UsuariosService usuariosService;

    @WebMethod(operationName = "logIn")
    public UsuariosDto logIn(@WebParam(name = "usuario") String usuario, @WebParam(name = "clave") String clave) {
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

    @WebMethod(operationName = "saveUser")
    public UsuariosDto saveUser(UsuariosDto usuario) {
        try {
            Respuesta res = usuariosService.saveUser(usuario);

            if (!res.getEstado()) {
                return (UsuariosDto) res.getResultado("Usuarios");
            }

            UsuariosDto users = (UsuariosDto) res.getResultado("Usuarios");
            return users;
        } catch (Exception e) {
            Logger.getLogger(SecurityController.class.getName()).log(Level.SEVERE, "Error al consultar los usuarios", e);
            return null;

        }
    }

    @WebMethod(operationName = "deleteUser")
    public UsuariosDto deleteUser(@WebParam(name = "usuario") Long id) {
        try {
            Respuesta res = usuariosService.deleteUser(id);

            if (!res.getEstado()) {
                return (UsuariosDto) res.getResultado("Usuario");
            }

            UsuariosDto users = (UsuariosDto) res.getResultado("Usuario");
            return users;
        } catch (Exception e) {
            Logger.getLogger(SecurityController.class.getName()).log(Level.SEVERE, "Error al eliminar el usuario", e);
            return null;

        }
    }

    @WebMethod(operationName = "getAllUsers")
    public UsuariosDto getAllUsers() {
        try {
            Respuesta res = usuariosService.getAllUser();

            if (!res.getEstado()) {
                return (UsuariosDto) res.getResultado("Usuarios");
            }

            UsuariosDto users = (UsuariosDto) res.getResultado("Usuarios");
            return users;
        } catch (Exception e) {
            Logger.getLogger(SecurityController.class.getName()).log(Level.SEVERE, "Error al consultar los usuarios", e);
            return null;

        }
    }

}
