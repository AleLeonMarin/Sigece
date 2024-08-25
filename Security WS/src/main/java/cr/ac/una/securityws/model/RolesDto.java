package cr.ac.una.securityws.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RolesDto  implements Serializable{

    private Long id;
    private String nombre;
    private Long version;
    List<SistemasRolesUsuariosDto> sistemasRolesUsuariosList;
    private SistemasDto sistemas;
    private Boolean modificado;

    public RolesDto() {
        this.modificado = false;
        sistemasRolesUsuariosList = new ArrayList<>();
    }

    public RolesDto(Roles roles) {
        this.id = roles.getId();
        this.nombre = roles.getNombre();
        this.version = roles.getVersion();
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

    public List<SistemasRolesUsuariosDto> getSistemasRolesUsuariosList() {
        return sistemasRolesUsuariosList;
    }

    public SistemasDto getSistemas() {
        return sistemas;
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

    public void setSistemasRolesUsuariosList(List<SistemasRolesUsuariosDto> sistemasRolesUsuariosList) {
        this.sistemasRolesUsuariosList = sistemasRolesUsuariosList;
    }

    public void setSistemas(SistemasDto sistemas) {
        this.sistemas = sistemas;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }
    
}
