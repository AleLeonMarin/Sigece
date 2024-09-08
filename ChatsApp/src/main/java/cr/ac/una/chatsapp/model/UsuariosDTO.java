package cr.ac.una.chatsapp.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;

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
    private byte[] usuFoto;
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
        this.usuFoto = null;
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
        return this.usuCorreo;
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

    public byte[] getUsuFoto() {
        return usuFoto;
    }

    public void setUsuFoto(byte[] usuFoto) {
        this.usuFoto = usuFoto;
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
                ", usuFoto=" + usuFoto +
                ", usuUsuario='" + usuUsuario + '\'' +
                ", usuClave='" + usuClave + '\'' +
                ", usuEstado='" + usuEstado + '\'' +
                ", usuStatus='" + usuStatus + '\'' +
                ", usuVersion=" + usuVersion +
                '}';
    }
}

