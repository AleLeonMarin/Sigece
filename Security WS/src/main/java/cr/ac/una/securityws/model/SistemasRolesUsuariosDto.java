package cr.ac.una.securityws.model;

import java.io.Serializable;

public class SistemasRolesUsuariosDto implements Serializable{

    private Long id;
    private Long version;
    String nombre;
    private SistemasDto SistemasDto;
    private RolesDto RolesDto;
    private UsuariosDto UsuariosDto;
    private Boolean modificado;

    public SistemasRolesUsuariosDto() {
        this.modificado = false;
    }

    public SistemasRolesUsuariosDto(SistemasRolesUsuarios sistemasRolesUsuarios) {
        this.id = sistemasRolesUsuarios.getId();
        this.version = sistemasRolesUsuarios.getVersion();
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

    public SistemasDto getSistemasDto() {
        return SistemasDto;
    }

    public RolesDto getRolesDto() {
        return RolesDto;
    }

    public UsuariosDto getUsuariosDto() {
        return UsuariosDto;
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

    public void setSistemasDto(SistemasDto SistemasDto) {
        this.SistemasDto = SistemasDto;
    }

    public void setRolesDto(RolesDto RolesDto) {
        this.RolesDto = RolesDto;
    }

    public void setUsuariosDto(UsuariosDto UsuariosDto) {
        this.UsuariosDto = UsuariosDto;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }
    
    
}
