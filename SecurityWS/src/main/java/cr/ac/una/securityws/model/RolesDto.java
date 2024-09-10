package cr.ac.una.securityws.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RolesDto implements Serializable {
    
    private Long id;
    private String nombre;
    private Long version;
    private SistemasDto sistema;
    private Boolean modificado;
    List<UsuariosDto> usuariosDto;


    public RolesDto() {
        this.modificado = false;
        usuariosDto = new ArrayList<>();
    }

    public RolesDto(Roles rol){
        this();
        this.id = rol.getId();
        this.nombre = rol.getNombre();
        this.version = rol.getVersion();
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

    public List<UsuariosDto> getUsuariosDto() {
        return usuariosDto;
    }

    public void setUsuariosDto(List<UsuariosDto> usuariosDto) {
        this.usuariosDto = usuariosDto;
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

    public SistemasDto getSistema() {
        return sistema;
    }

    public void setSistema(SistemasDto sistema) {
        this.sistema = sistema;
    }



    
}
