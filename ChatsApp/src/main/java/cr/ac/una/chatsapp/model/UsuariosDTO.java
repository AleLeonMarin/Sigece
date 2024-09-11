package cr.ac.una.chatsapp.model;

import jakarta.json.bind.annotation.JsonbProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;
import java.util.Base64;

/**
 * DTO para la entidad Usuarios, contiene la información de los usuarios.
 */
public class UsuariosDTO implements Serializable {

    private SimpleStringProperty usuId;
    private SimpleStringProperty usuNombre;
    private SimpleStringProperty usuApellidos;
    private SimpleStringProperty usuCedula;
    private SimpleStringProperty usuCorreo;
    private SimpleStringProperty usuTelefono;
    private SimpleStringProperty usuCelular;
    private SimpleStringProperty usuIdioma;
    private byte[] usuFoto; // Almacenamos el byte[] directamente
    private SimpleStringProperty usuUsuario;
    private SimpleStringProperty usuClave;
    private SimpleStringProperty usuEstado;
    private SimpleStringProperty usuStatus;
    private Long usuVersion;

    // Constructor vacío
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

    // Método para obtener la foto como Base64 (para transferencias)
    public String getUsuFotoBase64() {
        return usuFoto != null ? Base64.getEncoder().encodeToString(usuFoto) : null;
    }

    // Setter para establecer la foto desde una cadena Base64
    public void setUsuFotoBase64(String usuFotoBase64) {
        this.usuFoto = usuFotoBase64 != null ? Base64.getDecoder().decode(usuFotoBase64) : null;
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
