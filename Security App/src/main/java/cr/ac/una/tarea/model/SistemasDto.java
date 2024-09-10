package cr.ac.una.tarea.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SistemasDto  implements Serializable {

    private Long id;
    private String nombre;
    private Long version;
    private Boolean modificado;
    List<RolesDto> rolesDto;


    public SistemasDto() {
        this.modificado = false;
        rolesDto = new ArrayList<>();
    }

    public SistemasDto(cr.ac.una.securityws.controller.SistemasDto sistema){
        this.id = sistema.getId();
        this.nombre = sistema.getNombre();
        this.version = sistema.getVersion();
        for(cr.ac.una.securityws.controller.RolesDto rol : sistema.getRolesDto()){
            this.rolesDto.add(new RolesDto(rol));
        }
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Long getVersion() {
        return version;
    }

    public Boolean getModificado() {
        return modificado;
    }

    public List<RolesDto> getRolesDto() {
        return rolesDto;
    }

    public void setRolesDto(List<RolesDto> rolesDto) {
        this.rolesDto = rolesDto;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }

    
}
