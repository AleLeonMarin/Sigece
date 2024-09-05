package cr.ac.una.chatandmailapi.model;

import java.io.Serializable;

/**
 * DTO for Usuarios entity
 * 
 * @author Kendall Fonseca
 */
public class UsuariosDto implements Serializable {

    private Long usuId;
    private String usuNombre;
    private String usuApellidos;
    private String usuCorreo;
    private String usuTelefono;
    private String usuCelular;
    private String usuIdioma;
    private Serializable usuFoto;
    private String usuUsuario;
    private String usuClave;
    private String usuEstado;
    private String usuStatus;
    private Long usuVersion;

    public UsuariosDto() {
    }

    public UsuariosDto(Long usuId, String usuNombre, String usuApellidos, String usuCorreo, String usuTelefono, String usuCelular, String usuIdioma, Serializable usuFoto, String usuUsuario, String usuClave, String usuEstado, String usuStatus, Long usuVersion) {
        this.usuId = usuId;
        this.usuNombre = usuNombre;
        this.usuApellidos = usuApellidos;
        this.usuCorreo = usuCorreo;
        this.usuTelefono = usuTelefono;
        this.usuCelular = usuCelular;
        this.usuIdioma = usuIdioma;
        this.usuFoto = usuFoto;
        this.usuUsuario = usuUsuario;
        this.usuClave = usuClave;
        this.usuEstado = usuEstado;
        this.usuStatus = usuStatus;
        this.usuVersion = usuVersion;
    }
    
        public UsuariosDto(Usuarios usuario) {
        this();  
        this.usuId = usuario.getUsuId();
        this.usuNombre = usuario.getUsuNombre();
        this.usuApellidos = usuario.getUsuApellidos();
        this.usuCorreo = usuario.getUsuCorreo();
        this.usuTelefono = usuario.getUsuTelefono();
        this.usuCelular = usuario.getUsuCelular();
        this.usuIdioma = usuario.getUsuIdioma();
        this.usuFoto = usuario.getUsuFoto();
        this.usuUsuario = usuario.getUsuUsuario();
        this.usuClave = usuario.getUsuClave();
        this.usuEstado = usuario.getUsuEstado();
        this.usuStatus = usuario.getUsuStatus();
        this.usuVersion = usuario.getUsuVersion();
    }

    public Long getUsuId() {
        return usuId;
    }

    public void setUsuId(Long usuId) {
        this.usuId = usuId;
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

    public Serializable getUsuFoto() {
        return usuFoto;
    }

    public void setUsuFoto(Serializable usuFoto) {
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
        return "UsuariosDto{" +
                "usuId=" + usuId +
                ", usuNombre='" + usuNombre + '\'' +
                ", usuApellidos='" + usuApellidos + '\'' +
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
