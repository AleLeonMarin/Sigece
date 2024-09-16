package cr.ac.una.tarea.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class UsuariosDto implements Serializable {

    public SimpleStringProperty id;
    public SimpleStringProperty nombre;
    public SimpleStringProperty apellidos;
    public SimpleStringProperty cedula;
    public SimpleStringProperty correo;
    public SimpleStringProperty telefono;
    public SimpleStringProperty celular;
    public ObjectProperty<String> idioma;
    public byte[] foto;
    public SimpleStringProperty usuario;
    public SimpleStringProperty clave;
    public SimpleStringProperty estado;
    public SimpleStringProperty status;
    public Long version;
    public Boolean modificado;
    List<RolesDto> rolesDto;

    public UsuariosDto() {
        this.modificado = false;
        rolesDto = new ArrayList<>();
    }

    public UsuariosDto(cr.ac.una.securityws.controller.UsuariosDto usuarios) {

        this();
        this.id = new SimpleStringProperty("");
        this.nombre = new SimpleStringProperty("");
        this.apellidos = new SimpleStringProperty("");
        this.cedula = new SimpleStringProperty("");
        this.correo = new SimpleStringProperty("");
        this.telefono = new SimpleStringProperty("");
        this.celular = new SimpleStringProperty("");
        this.idioma = new SimpleObjectProperty<>("");
        this.foto = new byte[0];
        this.usuario = new SimpleStringProperty("");
        this.clave = new SimpleStringProperty("");
        this.estado = new SimpleStringProperty("");
        this.status = new SimpleStringProperty("");
        this.modificado = false;
        this.rolesDto = new ArrayList<>();
        if (usuarios.getRolesDto() != null) {
            for (cr.ac.una.securityws.controller.RolesDto rol : usuarios.getRolesDto()) {
                this.rolesDto.add(new RolesDto(rol));
            }
        }
    }

    public Long getId() {

        if (this.id.get() != null && !this.id.get().isEmpty()) {
            return Long.valueOf(this.id.get());
        } else {
            return null;
        }

    }

    public String getNombre() {
        return nombre.get();
    }

    public String getApellidos() {
        return apellidos.get();
    }

    public String getCedula() {
        return cedula.get();
    }

    public String getCorreo() {
        return correo.get();
    }

    public String getTelefono() {
        return telefono.get();
    }

    public String getCelular() {
        return celular.get();
    }

    public String getIdioma() {
        return idioma.get();
    }

    public byte[] getFoto() {
        return foto;
    }

    public String getUsuario() {
        return usuario.get();
    }

    public String getClave() {
        return clave.get();
    }

    public String getEstado() {
        return estado.get();
    }

    public String getStatus() {
        return status.get();
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

    public void setId(Long id) {
        this.id.set(id.toString());
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public void setApellidos(String apellidos) {
        this.apellidos.set(apellidos);
    }

    public void setCedula(String cedula) {
        this.cedula.set(cedula);
    }

    public void setCorreo(String correo) {
        this.correo.set(correo);
    }

    public void setTelefono(String telefono) {
        this.telefono.set(telefono);
    }

    public void setCelular(String celular) {
        this.celular.set(celular);
    }

    public void setIdioma(String idioma) {
        this.idioma.set(idioma);
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public void setUsuario(String usuario) {
        this.usuario.set(usuario);
    }

    public void setClave(String clave) {
        this.clave.set(clave);
    }

    public void setEstado(String estado) {
        this.estado.set(estado);
    }

    public void setStatus(String status) {
        this.status.set(status);
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
