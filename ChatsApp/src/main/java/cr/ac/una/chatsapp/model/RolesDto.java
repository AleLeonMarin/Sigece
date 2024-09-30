package cr.ac.una.chatsapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;

public class RolesDto implements Serializable {

    public SimpleStringProperty id;
    public SimpleStringProperty nombre;
    public Long version;
    public SistemasDto sistema;
    public Boolean modificado;
    List<UsuariosDTO> usuariosDto;

    public RolesDto() {
        this.id = new SimpleStringProperty("");
        this.nombre = new SimpleStringProperty("");
        this.modificado = false;
        usuariosDto = new ArrayList<>();
    }

    public RolesDto(cr.ac.una.securityws.controller.RolesDto rol) {
        this();
        this.id.set(rol.getId().toString());
        this.nombre.set(rol.getNombre());
        this.version = rol.getVersion();
        this.modificado = rol.isModificado();

        if (rol.getSistema() != null) {
            this.sistema = new SistemasDto(rol.getSistema());
        } else {
            this.sistema = null;
        }

        if (!rol.getUsuariosDto().isEmpty()) {
            List<cr.ac.una.securityws.controller.UsuariosDto> usuarios = rol.getUsuariosDto();
            for (cr.ac.una.securityws.controller.UsuariosDto usuario : usuarios) {
                this.usuariosDto.add(new UsuariosDTO(usuario));
            }
        }
    }

    public cr.ac.una.securityws.controller.RolesDto register() {
        cr.ac.una.securityws.controller.RolesDto rol = new cr.ac.una.securityws.controller.RolesDto();
        rol.setId(this.getId());
        rol.setNombre(this.getNombre());
        rol.setVersion(this.getVersion());

        if (this.getSistema() != null) {
            rol.setSistema(this.getSistema().registers());
        }

        return rol;
    }

    public Long getId() {
        if (id != null && id.get() != null && !id.get().isEmpty()) {
            return Long.valueOf(id.get());
        } else {
            return null;
        }
    }

    public void setId(Long id) {
        this.id.set(id.toString());
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public SistemasDto getSistema() {
        return sistema;
    }

    public void setSistema(SistemasDto sistema) {
        this.sistema = sistema;
    }

    public Boolean isModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }

    public List<UsuariosDTO> getUsuariosDto() {
        return usuariosDto;
    }

    public void setUsuariosDto(List<UsuariosDTO> usuariosDto) {
        this.usuariosDto = usuariosDto;
    }

}
