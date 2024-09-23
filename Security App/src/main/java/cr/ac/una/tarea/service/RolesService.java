package cr.ac.una.tarea.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import cr.ac.una.securityws.controller.SigeceSoapWS;
import cr.ac.una.securityws.controller.SigeceSoapWS_Service;
import cr.ac.una.tarea.model.RolesDto;
import cr.ac.una.tarea.util.Respuesta;

public class RolesService {


    SigeceSoapWS sigeceSoapWS;

    public Respuesta saveRol(cr.ac.una.securityws.controller.RolesDto rol) {
        try {

            SigeceSoapWS_Service service = new SigeceSoapWS_Service();
            sigeceSoapWS = service.getSigeceSoapWSPort();

            cr.ac.una.securityws.controller.RolesDto rol2 = sigeceSoapWS.saveRole(rol);

            RolesDto rolDto = new RolesDto(rol2);
            return new Respuesta(true, "", "", "Rol", rolDto);
        
        } catch (Exception ex) {
            Logger.getLogger(RolesService.class.getName()).log(Level.SEVERE, "Error guardando el rol", ex);
            return new Respuesta(false, "Error guardando el rol", "saveRol" + ex.getMessage());
        }
    }

    public Respuesta deleteRole(Long id) {

        try {
            SigeceSoapWS_Service service = new SigeceSoapWS_Service();
            sigeceSoapWS = service.getSigeceSoapWSPort();
            sigeceSoapWS.deleteRole(id);
            return new Respuesta(true, "", "", "Rol eliminado", "");
        } catch (Exception ex) {
            Logger.getLogger(RolesService.class.getName()).log(Level.SEVERE, "Error eliminando el rol", ex);
            return new Respuesta(false, "Error eliminando el rol", "deleteRole" + ex.getMessage());
        }
    }


    
}
