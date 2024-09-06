package cr.ac.una.chatandmailapi.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

/**
 * DTO para la entidad Usuarios, contiene la información de los usuarios.
 */
@Schema(description = "Esta clase contiene la información de un usuario")
public class UsuariosDTO implements Serializable {

    @Schema(description = "Identificador del usuario", example = "1")
    private Long usuId;

    @Schema(description = "Nombre del usuario", example = "Kendall")
    private String usuNombre;

    @Schema(description = "Apellidos del usuario", example = "Fonseca Rojas")
    private String usuApellidos;

    @Schema(description = "Cédula del usuario", example = "123456789")
    private String usuCedula;

    @Schema(description = "Correo del usuario", example = "kendall@example.com")
    private String usuCorreo;

    @Schema(description = "Teléfono del usuario", example = "22223333")
    private String usuTelefono;

    @Schema(description = "Celular del usuario", example = "88887777")
    private String usuCelular;

    @Schema(description = "Idioma del usuario", example = "es")
    private String usuIdioma;

    @Schema(description = "Foto del usuario")
    private Serializable usuFoto;

    @Schema(description = "Nombre de usuario", example = "kendallfonseca")
    private String usuUsuario;

    @Schema(description = "Clave del usuario", example = "123456")
    private String usuClave;

    @Schema(description = "Estado del usuario", example = "A", allowableValues = "A,I")
    private String usuEstado;

    @Schema(description = "Estado del usuario en el sistema", example = "Activo", allowableValues = "Activo,Inactivo")
    private String usuStatus;

    @Schema(description = "Versión del registro", example = "1")
    private Long usuVersion;

    // Constructor vacío
    public UsuariosDTO() {}

    // Constructor que recibe parámetros
    public UsuariosDTO(Long usuId, String usuNombre, String usuApellidos, String usuCedula, String usuCorreo, String usuTelefono, String usuCelular, String usuIdioma, Serializable usuFoto, String usuUsuario, String usuClave, String usuEstado, String usuStatus, Long usuVersion) {
        this.usuId = usuId;
        this.usuNombre = usuNombre;
        this.usuApellidos = usuApellidos;
        this.usuCedula = usuCedula;
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

    // Constructor que recibe una entidad Usuarios
    public UsuariosDTO(Usuarios usuario) {
        this();
        this.usuId = usuario.getUsuId();
        this.usuNombre = usuario.getUsuNombre();
        this.usuApellidos = usuario.getUsuApellidos();
        this.usuCedula = usuario.getUsuCedula();
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

    // Getters y Setters

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
