package cr.ac.una.tarea.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;

public class SistemasDto implements Serializable {

    public SimpleStringProperty id;
    public SimpleStringProperty nombre;
    public Long version;
    public Boolean modificado;
    List<RolesDto> rolesDto;

    public SistemasDto() {
        this.id = new SimpleStringProperty("");
        this.nombre = new SimpleStringProperty("");
        this.modificado = false;
        rolesDto = new ArrayList<>();
    }

    public SistemasDto(cr.ac.una.securityws.controller.SistemasDto sistema) {
        this();
        this.id.set(sistema.getId().toString());
        this.nombre.set(sistema.getNombre());
        this.version = sistema.getVersion();
        this.modificado = false;

        if (!sistema.getRolesDto().isEmpty()) {
            List<cr.ac.una.securityws.controller.RolesDto> roles = sistema.getRolesDto();
            for (cr.ac.una.securityws.controller.RolesDto rol : roles) {
                this.rolesDto.add(new RolesDto(rol));
            }
        }
    }

    public cr.ac.una.securityws.controller.SistemasDto registers() {

        cr.ac.una.securityws.controller.SistemasDto sistema = new cr.ac.una.securityws.controller.SistemasDto();
        sistema.setId(this.getId());
        sistema.setNombre(this.getNombre());
        sistema.setVersion(this.getVersion());

        if (!this.rolesDto.isEmpty()) {
            List<cr.ac.una.securityws.controller.RolesDto> roles = new ArrayList<>();
            for (RolesDto rol : this.getRolesDto()) {
                roles.add(rol.register());
            }
        }
        return sistema;
    }

    public Long getId() {

        if (id != null && id.get() != null && !id.get().isEmpty()) {
            return Long.valueOf(id.get());
        } else {
            return null;
        }
    }

    public String getNombre() {
        return nombre.get();
    }

    public Long getVersion() {
        return version;
    }

    public Boolean isModificado() {
        return modificado;
    }

    public List<RolesDto> getRolesDto() {
        return rolesDto;
    }

    public void setId(Long id) {
        if (id != null) {
            this.id.set(id.toString());
        } else {
            this.id.set("");
        }
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }

    public void setRolesDto(List<RolesDto> rolesDto) {
        this.rolesDto = rolesDto;
    }

}
