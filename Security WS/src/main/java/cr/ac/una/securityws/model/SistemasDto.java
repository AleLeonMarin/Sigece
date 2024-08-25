package cr.ac.una.securityws.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SistemasDto implements Serializable {

    private Long id;
    private String nombre;
    private Long version;
    private List<RolesDto> rolesDto;
    private List<SistemasRolesUsuariosDto> sistemasRolesUsuariosDto;
    private Boolean modificado;

    public SistemasDto() {
        this.modificado = false;
        rolesDto = new ArrayList<>();
        sistemasRolesUsuariosDto = new ArrayList<>();
    }

    public SistemasDto(Sistemas sistemas){
        this.id = sistemas.getId();
        this.nombre = sistemas.getNombre();
        this.version = sistemas.getVersion();
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

    public List<RolesDto> getRolesDto() {
        return rolesDto;
    }

    public List<SistemasRolesUsuariosDto> getSistemasRolesUsuariosDto() {
        return sistemasRolesUsuariosDto;
    }

    public Boolean getModificado() {
        return modificado;
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

    public void setRolesDto(List<RolesDto> rolesDto) {
        this.rolesDto = rolesDto;
    }

    public void setSistemasRolesUsuariosDto(List<SistemasRolesUsuariosDto> sistemasRolesUsuariosDto) {
        this.sistemasRolesUsuariosDto = sistemasRolesUsuariosDto;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }

}
