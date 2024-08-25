package cr.ac.una.securityws.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UsuariosDto implements Serializable {

    private Long id;
    private String nombre;
    private String apellidos;
    private String correo;
    private String telefono;
    private String celular;
    private String idioma;
    private byte[] foto;
    private String usuario;
    private String clave;
    private String estado;
    private String status;
    private Long version;
    private Boolean modificado;
    List<SistemasRolesUsuariosDto> sistemasRolesUsuariosList;

    public UsuariosDto() {

        this.modificado = false;
        sistemasRolesUsuariosList = new ArrayList<>();
    }

    public UsuariosDto(Usuarios usuarios) {

        this.id = usuarios.getId();
        this.nombre = usuarios.getNombre();
        this.apellidos = usuarios.getApellidos();
        this.correo = usuarios.getCorreo();
        this.telefono = usuarios.getTelefono();
        this.celular = usuarios.getCelular();
        this.idioma = usuarios.getIdioma();
        this.foto = (byte[])usuarios.getFoto();
        this.usuario = usuarios.getUsuario();
        this.clave = usuarios.getClave();
        this.estado = usuarios.getEstado();
        this.status = usuarios.getStatus();
        this.version = usuarios.getVersion();
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCelular() {
        return celular;
    }

    public String getIdioma() {
        return idioma;
    }

    public byte[] getFoto() {
        return foto;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getClave() {
        return clave;
    }

    public String getEstado() {
        return estado;
    }

    public String getStatus() {
        return status;
    }

    public Long getVersion() {
        return version;
    }

    public Boolean getModificado() {
        return modificado;
    }

    public List<SistemasRolesUsuariosDto> getSistemasRolesUsuariosList() {
        return sistemasRolesUsuariosList;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }

    public void setSistemasRolesUsuariosList(List<SistemasRolesUsuariosDto> sistemasRolesUsuariosList) {
        this.sistemasRolesUsuariosList = sistemasRolesUsuariosList;
    }

}
