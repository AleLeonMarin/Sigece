package cr.ac.una.chatsapp.model;

import jakarta.json.bind.annotation.JsonbProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import java.io.Serializable;
import java.util.Base64;
import java.util.List;

/**
 * DTO para la entidad Usuarios, contiene la información de los usuarios.
 */
public class UsuariosDTO implements Serializable {

    public SimpleStringProperty usuId;
    public SimpleStringProperty usuNombre;
    public SimpleStringProperty usuApellidos;
    public SimpleStringProperty usuCedula;
    public SimpleStringProperty usuCorreo;
    public SimpleStringProperty usuTelefono;
    public SimpleStringProperty usuCelular;
    public SimpleStringProperty usuIdioma;
    public byte[] usuFoto; // Almacenamos el byte[] directamente
    public SimpleStringProperty usuUsuario;
    public SimpleStringProperty usuClave;
    public SimpleStringProperty usuEstado;
    public SimpleStringProperty usuStatus;
    public Long usuVersion;
    public List<RolesDto> rolesDto;

    public UsuariosDTO() {
        this.usuId = new SimpleStringProperty("");
        this.usuNombre = new SimpleStringProperty("");
        this.usuApellidos = new SimpleStringProperty("");
        this.usuCedula = new SimpleStringProperty("");
        this.usuCorreo = new SimpleStringProperty("");
        this.usuTelefono = new SimpleStringProperty("");
        this.usuCelular = new SimpleStringProperty("");
        this.usuIdioma = new SimpleStringProperty("");
        this.usuFoto = null;
        this.usuUsuario = new SimpleStringProperty("");
        this.usuClave = new SimpleStringProperty("");
        this.usuEstado = new SimpleStringProperty("");
        this.usuStatus = new SimpleStringProperty("");
        this.usuVersion = 0L;
        this.rolesDto = FXCollections.observableArrayList();
    }

    public UsuariosDTO(cr.ac.una.securityws.controller.UsuariosDto usuarios) {
        this();
        this.usuId.set(usuarios.getId().toString());
        this.usuNombre.set(usuarios.getNombre());
        this.usuApellidos.set(usuarios.getApellidos());
        this.usuCedula.set(usuarios.getCedula());
        this.usuCorreo.set(usuarios.getCorreo());
        this.usuTelefono.set(usuarios.getTelefono());
        this.usuCelular.set(usuarios.getCelular());
        this.usuIdioma.set(usuarios.getIdioma());
        this.usuFoto = usuarios.getFoto(); // Asignación directa del byte[]
        this.usuUsuario.set(usuarios.getUsuario());
        this.usuClave.set(usuarios.getClave());
        this.usuEstado.set(usuarios.getEstado());
        this.usuStatus.set(usuarios.getStatus());
        this.usuVersion = usuarios.getVersion();
    
        if (usuarios.getRolesDto() != null && !usuarios.getRolesDto().isEmpty()) {
            for (cr.ac.una.securityws.controller.RolesDto rol : usuarios.getRolesDto()) {
                this.rolesDto.add(new RolesDto(rol));
            }
        }
    }

    public cr.ac.una.securityws.controller.UsuariosDto registers() {
        cr.ac.una.securityws.controller.UsuariosDto usuarios = new cr.ac.una.securityws.controller.UsuariosDto();

        usuarios.setId(this.getUsuId());
        usuarios.setNombre(this.getUsuNombre());
        usuarios.setApellidos(this.getUsuApellidos());
        usuarios.setCedula(this.getUsuCedula());
        usuarios.setCorreo(this.getUsuCorreo());
        usuarios.setTelefono(this.getUsuTelefono());
        usuarios.setCelular(this.getUsuCelular());
        usuarios.setIdioma(this.getUsuIdioma());
        usuarios.setFoto(this.getUsuFoto());
        usuarios.setUsuario(this.getUsuUsuario());
        usuarios.setClave(this.getUsuClave());
        usuarios.setEstado(this.getUsuEstado());
        usuarios.setStatus(this.getUsuStatus());
        usuarios.setVersion(this.getUsuVersion());

        // Aquí se asignan los roles directamente a la lista de rolesDto
        if (this.getRolesDto() != null && !this.getRolesDto().isEmpty()) {
            for (RolesDto rol : this.getRolesDto()) {
                usuarios.getRolesDto().add(rol.register()); // Agregar directamente a la lista
            }
        }

        return usuarios;
    }

    // Getters and Setters

    public Long getUsuId() {
        if (this.usuId.get() != null && !this.usuId.get().isEmpty()) {
            return Long.valueOf(this.usuId.get());
        } else {
            return null;
        }
    }

    public void setUsuId(Long usuId) {
        this.usuId.set(usuId.toString());
    }

    public String getUsuNombre() {
        return usuNombre.get();
    }

    public void setUsuNombre(String usuNombre) {
        this.usuNombre.set(usuNombre);
    }

    public String getUsuApellidos() {
        return usuApellidos.get();
    }

    public void setUsuApellidos(String usuApellidos) {
        this.usuApellidos.set(usuApellidos);
    }

    public String getUsuCedula() {
        return usuCedula.get();
    }

    public void setUsuCedula(String usuCedula) {
        this.usuCedula.set(usuCedula);
    }

    public String getUsuCorreo() {
        return usuCorreo.get();
    }

    @JsonbProperty("correo")
    public void setUsuCorreo(String usuCorreo) {
        this.usuCorreo.set(usuCorreo);
    }

    public String getUsuTelefono() {
        return usuTelefono.get();
    }

    public void setUsuTelefono(String usuTelefono) {
        this.usuTelefono.set(usuTelefono);
    }

    public String getUsuCelular() {
        return usuCelular.get();
    }

    public void setUsuCelular(String usuCelular) {
        this.usuCelular.set(usuCelular);
    }

    public String getUsuIdioma() {
        return usuIdioma.get();
    }

    public void setUsuIdioma(String usuIdioma) {
        this.usuIdioma.set(usuIdioma);
    }

    // Getter para obtener el byte[] de la foto
    public byte[] getUsuFoto() {
        return usuFoto;
    }

    // Setter para establecer la foto desde un byte[]
    public void setUsuFoto(byte[] usuFoto) {
        this.usuFoto = usuFoto;
    }

    public String getUsuUsuario() {
        return usuUsuario.get();
    }

    public void setUsuUsuario(String usuUsuario) {
        this.usuUsuario.set(usuUsuario);
    }

    public String getUsuClave() {
        return usuClave.get();
    }

    public void setUsuClave(String usuClave) {
        this.usuClave.set(usuClave);
    }

    public String getUsuEstado() {
        return usuEstado.get();
    }

    public void setUsuEstado(String usuEstado) {
        this.usuEstado.set(usuEstado);
    }

    public String getUsuStatus() {
        return usuStatus.get();
    }

    public void setUsuStatus(String usuStatus) {
        this.usuStatus.set(usuStatus);
    }

    public Long getUsuVersion() {
        return usuVersion;
    }

    public void setUsuVersion(Long usuVersion) {
        this.usuVersion = usuVersion;
    }

    public List<RolesDto> getRolesDto() {
        return rolesDto;
    }

    public void setRolesDto(List<RolesDto> rolesDto) {
        this.rolesDto = FXCollections.observableArrayList(rolesDto);
    }

    @Override
    public String toString() {
        return "UsuariosDTO{" +
                "usuId=" + usuId +
                ", usuNombre='" + usuNombre + '\'' +
                ", usuApellidos='" + usuApellidos + '\'' +
                ", usuCedula='" + usuCedula + '\'' +
                ", usuCorreo='" + usuCorreo + '\'' +
                ", usuTelefono='" + usuTelefono + '\'' +
                ", usuCelular='" + usuCelular + '\'' +
                ", usuIdioma='" + usuIdioma + '\'' +
                ", usuFoto(Base64)=" + getUsuFotoBase64() +
                ", usuUsuario='" + usuUsuario + '\'' +
                ", usuClave='" + usuClave + '\'' +
                ", usuEstado='" + usuEstado + '\'' +
                ", usuStatus='" + usuStatus + '\'' +
                ", usuVersion=" + usuVersion +
                '}';
    }

    public String getUsuFotoBase64() {
        return usuFoto != null ? Base64.getEncoder().encodeToString(usuFoto) : null;
    }

    // Setter para establecer la foto desde una cadena Base64
    public void setUsuFotoBase64(String usuFotoBase64) {
        this.usuFoto = usuFotoBase64 != null ? Base64.getDecoder().decode(usuFotoBase64) : null;
    }
}
