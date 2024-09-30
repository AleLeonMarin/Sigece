package cr.ac.una.tarea.model;

import java.io.Serializable;
import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;

public class UsuariosDto implements Serializable {

    private static final long serialVersionUID = 1L;

    public SimpleStringProperty id;
    public SimpleStringProperty nombre;
    public SimpleStringProperty apellidos;
    public SimpleStringProperty cedula;
    public SimpleStringProperty correo;
    public SimpleStringProperty telefono;
    public SimpleStringProperty celular;
    public ObjectProperty<String> idioma;
    public ObjectProperty<byte[]> foto;
    public SimpleStringProperty usuario;
    public SimpleStringProperty clave;
    public SimpleStringProperty estado;
    public SimpleStringProperty status;
    public Long version;
    public Boolean modificado;
    public List<RolesDto> rolesDto;
    public List<RolesDto> rolesDtoEliminados;

    public UsuariosDto() {
        this.id = new SimpleStringProperty("");
        this.nombre = new SimpleStringProperty("");
        this.apellidos = new SimpleStringProperty("");
        this.cedula = new SimpleStringProperty("");
        this.correo = new SimpleStringProperty("");
        this.telefono = new SimpleStringProperty("");
        this.celular = new SimpleStringProperty("");
        this.idioma = new SimpleObjectProperty<>("");
        this.foto = new SimpleObjectProperty<>(new byte[0]);
        this.usuario = new SimpleStringProperty("");
        this.clave = new SimpleStringProperty("");
        this.estado = new SimpleStringProperty("");
        this.status = new SimpleStringProperty("");
        this.modificado = false;
        this.rolesDto = FXCollections.observableArrayList();
        this.rolesDtoEliminados = FXCollections.observableArrayList();
    }

    public UsuariosDto(cr.ac.una.securityws.controller.UsuariosDto usuarios) {
        this();
        this.id.set(usuarios.getId().toString());
        this.nombre.set(usuarios.getNombre());
        this.apellidos.set(usuarios.getApellidos());
        this.cedula.set(usuarios.getCedula());
        this.correo.set(usuarios.getCorreo());
        this.telefono.set(usuarios.getTelefono());
        this.celular.set(usuarios.getCelular());
        this.idioma.set(usuarios.getIdioma());
        this.foto.set(usuarios.getFoto());
        this.usuario.set(usuarios.getUsuario());
        this.clave.set(usuarios.getClave());
        this.estado.set(usuarios.getEstado());
        this.status.set(usuarios.getStatus());
        this.version = usuarios.getVersion();
        this.modificado = usuarios.isModificado();

        if (!usuarios.getRolesDto().isEmpty()) {
            List<cr.ac.una.securityws.controller.RolesDto> roles = usuarios.getRolesDto();
            for (cr.ac.una.securityws.controller.RolesDto rol : roles) {
                this.rolesDto.add(new RolesDto(rol));
            }
        }

        if(!usuarios.getRolesEliminados().isEmpty()){
            List<cr.ac.una.securityws.controller.RolesDto> roles = usuarios.getRolesEliminados();
            for (cr.ac.una.securityws.controller.RolesDto rol : roles) {
                this.rolesDtoEliminados.add(new RolesDto(rol));
            }
        }
    }

    public cr.ac.una.securityws.controller.UsuariosDto registers() {
        cr.ac.una.securityws.controller.UsuariosDto usuarios = new cr.ac.una.securityws.controller.UsuariosDto();

        usuarios.setId(this.getId());
        usuarios.setNombre(this.getNombre());
        usuarios.setApellidos(this.getApellidos());
        usuarios.setCedula(this.getCedula());
        usuarios.setCorreo(this.getCorreo());
        usuarios.setTelefono(this.getTelefono());
        usuarios.setCelular(this.getCelular());
        usuarios.setIdioma(this.getIdioma());
        usuarios.setFoto(this.getFoto());
        usuarios.setUsuario(this.getUsuario());
        usuarios.setClave(this.getClave());
        usuarios.setEstado(this.getEstado());
        usuarios.setStatus(this.getStatus());
        usuarios.setVersion(this.getVersion());

        // Aquí se asignan los roles directamente a la lista de rolesDto
        if (this.getRolesDto() != null && !this.getRolesDto().isEmpty()) {
            for (RolesDto rol : this.getRolesDto()) {
                usuarios.getRolesDto().add(rol.register()); // Agregar directamente a la lista
            }
        }

        // Aquí se asignan los roles eliminados directamente a la lista de rolesDtoEliminados
        if (this.rolesDtoEliminados != null && !this.rolesDtoEliminados.isEmpty()) {
            for (RolesDto rol : this.rolesDtoEliminados) {
                usuarios.getRolesEliminados().add(rol.register()); // Agregar directamente a la lista
            }
        }

        return usuarios;
    }

    // Getters

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
        return foto.get();
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

    public List<RolesDto> getRolesDtoEliminados() {
        return rolesDtoEliminados;
    }

    // Setters

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
        this.foto.set(foto);
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
        this.rolesDto = FXCollections.observableArrayList(rolesDto);
    }

    public void setRolesDtoEliminados(List<RolesDto> rolesDtoEliminados) {
        this.rolesDtoEliminados = FXCollections.observableArrayList(rolesDtoEliminados);
    }

}
