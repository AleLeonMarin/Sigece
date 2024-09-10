package cr.ac.una.chatsapp.model;

import java.io.Serializable;
import java.util.Base64;

/**
 * DTO para la entidad Usuarios, contiene la información de los usuarios.
 */
public class UsuariosDTO implements Serializable {

    private String usuId;
    private String usuNombre;
    private String usuApellidos;
    private String usuCedula;
    private String usuCorreo;
    private String usuTelefono;
    private String usuCelular;
    private String usuIdioma;
    private byte[] usuFoto; // Almacenamos el byte[] directamente
    private String usuUsuario;
    private String usuClave;
    private String usuEstado;
    private String usuStatus;
    private Long usuVersion;

    // Constructor vacío
    public UsuariosDTO() {
        this.usuId = "";
        this.usuNombre = "";
        this.usuApellidos = "";
        this.usuCedula = "";
        this.usuCorreo = "";
        this.usuTelefono = "";
        this.usuCelular = "";
        this.usuIdioma = "";
        this.usuFoto = null;  // Iniciamos el byte[] como null
        this.usuUsuario = "";
        this.usuClave = "";
        this.usuEstado = "";
        this.usuStatus = "";
    }

    // Getters and Setters

    public Long getUsuId() {
        return usuId != null && !usuId.isEmpty() ? Long.parseLong(usuId) : null;
    }

    public void setUsuId(Long usuId) {
        this.usuId = usuId != null ? usuId.toString() : "";
    }

    public String getUsuNombre() {
        return usuNombre;
    }

    public void setUsuNombre(String usuNombre) {
        this.usuNombre = usuNombre;
    }

    public String getUsuApellidos() {
        return usuApellidos;
    }

    public void setUsuApellidos(String usuApellidos) {
        this.usuApellidos = usuApellidos;
    }

    public String getUsuCedula() {
        return usuCedula;
    }

    public void setUsuCedula(String usuCedula) {
        this.usuCedula = usuCedula;
    }

    public String getUsuCorreo() {
        return usuCorreo;
    }

    public void setUsuCorreo(String usuCorreo) {
        this.usuCorreo = usuCorreo;
    }

    public String getUsuTelefono() {
        return usuTelefono;
    }

    public void setUsuTelefono(String usuTelefono) {
        this.usuTelefono = usuTelefono;
    }

    public String getUsuCelular() {
        return usuCelular;
    }

    public void setUsuCelular(String usuCelular) {
        this.usuCelular = usuCelular;
    }

    public String getUsuIdioma() {
        return usuIdioma;
    }

    public void setUsuIdioma(String usuIdioma) {
        this.usuIdioma = usuIdioma;
    }

    // Getter para obtener el byte[] de la foto
    public byte[] getUsuFoto() {
        return usuFoto;
    }

    // Setter para establecer la foto desde un byte[]
    public void setUsuFoto(byte[] usuFoto) {
        this.usuFoto = usuFoto;
    }

    // Método para obtener la foto como Base64 (para transferencias)
    public String getUsuFotoBase64() {
        return usuFoto != null ? Base64.getEncoder().encodeToString(usuFoto) : null;
    }

    // Setter para establecer la foto desde una cadena Base64
    public void setUsuFotoBase64(String usuFotoBase64) {
        this.usuFoto = usuFotoBase64 != null ? Base64.getDecoder().decode(usuFotoBase64) : null;
    }

    public String getUsuUsuario() {
        return usuUsuario;
    }

    public void setUsuUsuario(String usuUsuario) {
        this.usuUsuario = usuUsuario;
    }

    public String getUsuClave() {
        return usuClave;
    }

    public void setUsuClave(String usuClave) {
        this.usuClave = usuClave;
    }

    public String getUsuEstado() {
        return usuEstado;
    }

    public void setUsuEstado(String usuEstado) {
        this.usuEstado = usuEstado;
    }

    public String getUsuStatus() {
        return usuStatus;
    }

    public void setUsuStatus(String usuStatus) {
        this.usuStatus = usuStatus;
    }

    public Long getUsuVersion() {
        return usuVersion;
    }

    public void setUsuVersion(Long usuVersion) {
        this.usuVersion = usuVersion;
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
}
