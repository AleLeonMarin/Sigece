package cr.ac.una.securityws.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import cr.ac.una.securityws.model.RolesDto;
import cr.ac.una.securityws.model.SistemasDto;
import cr.ac.una.securityws.model.UsuariosDto;
import cr.ac.una.securityws.service.RolesService;
import cr.ac.una.securityws.service.SistemasService;
import cr.ac.una.securityws.service.UsuariosService;
import cr.ac.una.securityws.util.Respuesta;
import jakarta.ejb.EJB;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

@WebService(serviceName = "SigeceSoapWS")
public class SigeceSoapWS {

    /*
     * 
     * 
     * Controller methods from the ws Soap for users
     * 
     * 
     */

    @EJB
    UsuariosService usuariosService;

    @EJB
    SistemasService sistemasService;

    @EJB
    RolesService rolesService;

    @WebMethod(operationName = "logIn")
    public UsuariosDto logIn(@WebParam(name = "usuario") String usuario, @WebParam(name = "clave") String clave) {
        try {
            Respuesta res = usuariosService.validateUser(usuario, clave);

            // Verifica si la validación fue exitosa
            if (!res.getEstado()) {
                // Opcional: puedes lanzar una excepción o manejar el error de otra manera
                Logger.getLogger(SigeceSoapWS.class.getName()).log(Level.WARNING, "Error de autenticación: {0}",
                        res.getMensajeInterno());
                return null; // o podrías retornar un DTO que represente un error
            }

            UsuariosDto user = (UsuariosDto) res.getResultado("Usuario");
            System.out.println(user);
            return user;
        } catch (Exception e) {
            Logger.getLogger(SigeceSoapWS.class.getName()).log(Level.SEVERE, "Error en el login", e);
            return null;
        }
    }

    @WebMethod(operationName = "saveUser")
    public UsuariosDto saveUser(UsuariosDto usuario) {
        try {
            Respuesta res = usuariosService.saveUser(usuario);

            if (!res.getEstado()) {
                return (UsuariosDto) res.getResultado("Usuario");
            }

            UsuariosDto users = (UsuariosDto) res.getResultado("Usuario");
            return users;
        } catch (Exception e) {
            Logger.getLogger(SigeceSoapWS.class.getName()).log(Level.SEVERE, "Error al consultar los usuarios", e);
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
            Logger.getLogger(SigeceSoapWS.class.getName()).log(Level.SEVERE, "Error al eliminar el usuario", e);
            return null;

        }
    }

    @WebMethod(operationName = "getAllUsers")
    public List<UsuariosDto> getAllUsers() {
        try {
            Respuesta res = usuariosService.getAllUser();

            if (!res.getEstado()) {
                return (List<UsuariosDto>) res.getResultado("Usuarios");
            }

            List<UsuariosDto> UsuarioDto = (List<UsuariosDto>) res.getResultado("Usuario");
            return UsuarioDto;// TODO
        } catch (Exception e) {
            Logger.getLogger(SigeceSoapWS.class.getName()).log(Level.SEVERE, "Error al consultar los usuarios", e);
            return null;

        }
    }

    @WebMethod(operationName = "getUser")
    public UsuariosDto getUser(@WebParam(name = "usuario") Long id) {
        try {
            Respuesta res = usuariosService.getUsuario(id);

            if (!res.getEstado()) {
                return (UsuariosDto) res.getResultado("Usuario");
            }

            UsuariosDto users = (UsuariosDto) res.getResultado("Usuario");
            return users;
        } catch (Exception e) {
            Logger.getLogger(SigeceSoapWS.class.getName());
            return null;
        }
    }

    @WebMethod(operationName = "getUserByMail")
    public UsuariosDto getUserByMail(@WebParam(name = "mail") String mail) {
        try {
            Respuesta res = usuariosService.getUserByMail(mail);

            if (!res.getEstado()) {
                return (UsuariosDto) res.getResultado("Usuario");
            }

            UsuariosDto users = (UsuariosDto) res.getResultado("Usuario");
            return users;
        } catch (Exception e) {
            Logger.getLogger(SigeceSoapWS.class.getName());
            return null;
        }
    }

    /*
     * 
     * 
     * Controller methods from the ws Soap for systems
     * 
     * 
     */

    @WebMethod(operationName = "saveSystem")
    public SistemasDto saveSystem(SistemasDto sistema) {
        try {
            Respuesta res = sistemasService.saveSystem(sistema);

            if (!res.getEstado()) {
                return (SistemasDto) res.getResultado("Sistema");
            }

            SistemasDto sistemas = (SistemasDto) res.getResultado("Sistema");
            return sistemas;
        } catch (Exception e) {
            Logger.getLogger(SigeceSoapWS.class.getName());
            return null;
        }
    }

    @WebMethod(operationName = "deleteSystem")
    public SistemasDto deleteSystem(@WebParam(name = "sistema") Long id) {
        try {
            Respuesta res = sistemasService.deleteSystem(id);

            if (!res.getEstado()) {
                return (SistemasDto) res.getResultado("Sistema");
            }

            SistemasDto sistemas = (SistemasDto) res.getResultado("Sistema");
            return sistemas;
        } catch (Exception e) {
            Logger.getLogger(SigeceSoapWS.class.getName());
            return null;
        }
    }

    @WebMethod(operationName = "getAllSystems")
    public List<SistemasDto> getAllSystems() {
        try {
            Respuesta res = sistemasService.getSistemas();

            if (!res.getEstado()) {
                return (List<SistemasDto>) res.getResultado("Sistemas");
            }

            List<SistemasDto> sistemasDto = (List<SistemasDto>) res.getResultado("Sistemas");
            return sistemasDto;
        } catch (Exception e) {
            Logger.getLogger(SigeceSoapWS.class.getName());
            return null;
        }
    }

    @WebMethod(operationName = "getSystem")
    public SistemasDto getSystem(@WebParam(name = "sistema") Long id) {
        try {
            Respuesta res = sistemasService.getSystem(id);

            if (!res.getEstado()) {
                return (SistemasDto) res.getResultado("Sistema");
            }

            SistemasDto sistemas = (SistemasDto) res.getResultado("Sistema");
            return sistemas;
        } catch (Exception e) {
            Logger.getLogger(SigeceSoapWS.class.getName());
            return null;
        }
    }

    /*
     * 
     * 
     * Controller methods from the ws Soap for roles
     * 
     * 
     */

    @WebMethod(operationName = "saveRole")
    public RolesDto saveRole(RolesDto rolesDto) {
        try {
            Respuesta res = rolesService.saveRol(rolesDto);

            if (!res.getEstado()) {
                return (RolesDto) res.getResultado("Rol");
            }

            RolesDto rol = (RolesDto) res.getResultado("Rol");
            return rol;
        } catch (Exception e) {
            Logger.getLogger(SigeceSoapWS.class.getName());
            return null;
        }
    }

    @WebMethod(operationName = "deleteRole")
    public RolesDto deleteRole(@WebParam(name = "rol") Long id) {
        try {
            Respuesta res = rolesService.deleteRol(id);

            if (!res.getEstado()) {
                return (RolesDto) res.getResultado("Rol");
            }

            RolesDto rol = (RolesDto) res.getResultado("Rol");
            return rol;
        } catch (Exception e) {
            Logger.getLogger(SigeceSoapWS.class.getName());
            return null;
        }
    }

    @WebMethod(operationName = "getAllRoles")
    public List<RolesDto> getAllRoles() {
        try {
            Respuesta res = rolesService.getRoles();

            if (!res.getEstado()) {
                return (List<RolesDto>) res.getResultado("Roles");
            }

            List<RolesDto> rolesDto = (List<RolesDto>) res.getResultado("Roles");
            return rolesDto;
        } catch (Exception e) {
            Logger.getLogger(SigeceSoapWS.class.getName());
            return null;
        }
    }

    @WebMethod(operationName = "getRole")
    public RolesDto getRole(@WebParam(name = "rol") Long id) {
        try {
            Respuesta res = rolesService.getRol(id);

            if (!res.getEstado()) {
                return (RolesDto) res.getResultado("Rol");
            }

            RolesDto rol = (RolesDto) res.getResultado("Rol");
            return rol;
        } catch (Exception e) {
            Logger.getLogger(SigeceSoapWS.class.getName());
            return null;
        }
    }

}
