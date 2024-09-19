package cr.ac.una.tarea.service;

import java.util.logging.Level;
import java.util.logging.Logger;
import cr.ac.una.securityws.controller.SigeceSoapWS;
import cr.ac.una.securityws.controller.SigeceSoapWS_Service;
import cr.ac.una.tarea.model.SistemasDto;
import cr.ac.una.tarea.util.Respuesta;

public class SistemasService {

    SigeceSoapWS sigeceSoapWS;

    public Respuesta saveSystem(cr.ac.una.securityws.controller.SistemasDto sistema) {
        try {

            SigeceSoapWS_Service service = new SigeceSoapWS_Service();
            sigeceSoapWS = service.getSigeceSoapWSPort();

            cr.ac.una.securityws.controller.SistemasDto sistema2 = sigeceSoapWS.saveSystem(sistema);

            SistemasDto sistemaDto = new SistemasDto(sistema2);
            return new Respuesta(true, "", "", "Sistema", sistemaDto);
        
        } catch (Exception ex) {
            Logger.getLogger(SistemasService.class.getName()).log(Level.SEVERE, "Error guardando el sistema", ex);
            return new Respuesta(false, "Error guardando el sistema", "saveSystem" + ex.getMessage());
        }
    }

    public Respuesta getAll() {

        try {
            SigeceSoapWS_Service service = new SigeceSoapWS_Service();
            sigeceSoapWS = service.getSigeceSoapWSPort();
            java.util.List<cr.ac.una.securityws.controller.SistemasDto> sistemas = sigeceSoapWS.getAllSystems();
            java.util.List<SistemasDto> sistemasDto = new java.util.ArrayList<>();
            for (cr.ac.una.securityws.controller.SistemasDto sistema : sistemas) {
                SistemasDto sistemaDto = new SistemasDto(sistema);
                sistemasDto.add(sistemaDto);
            }
            return new Respuesta(true, "", "", "Sistemas", sistemasDto);
        } catch (Exception ex) {
            Logger.getLogger(SistemasService.class.getName()).log(Level.SEVERE, "Error obteniendo los sistemas", ex);
            return new Respuesta(false, "Error obteniendo los sistemas", "getAll" + ex.getMessage());
        }
    }

    public Respuesta getSystem(Long id) {
        try {
            SigeceSoapWS_Service service = new SigeceSoapWS_Service();
            sigeceSoapWS = service.getSigeceSoapWSPort();
            cr.ac.una.securityws.controller.SistemasDto sistema = sigeceSoapWS.getSystem(id);
            SistemasDto sistemaDto = new SistemasDto(sistema);
            return new Respuesta(true, "", "", "Sistema", sistemaDto);
        } catch (Exception ex) {
            Logger.getLogger(SistemasService.class.getName()).log(Level.SEVERE, "Error obteniendo el sistema", ex);
            return new Respuesta(false, "Error obteniendo el sistema", "getSystem" + ex.getMessage());
        }
    }

    public Respuesta deleteSystem(Long id) {
        try {
            SigeceSoapWS_Service service = new SigeceSoapWS_Service();
            sigeceSoapWS = service.getSigeceSoapWSPort();
            return new Respuesta(true, "", "", "Sistema", sigeceSoapWS.deleteSystem(id));
        } catch (Exception ex) {
            Logger.getLogger(SistemasService.class.getName()).log(Level.SEVERE, "Error eliminando el sistema", ex);
            return new Respuesta(false, "Error eliminando el sistema", "deleteSystem" + ex.getMessage());
        }
    }

}
