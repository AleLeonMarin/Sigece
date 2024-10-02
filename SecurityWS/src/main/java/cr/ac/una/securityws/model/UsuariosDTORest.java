package cr.ac.una.securityws.model;

import jakarta.json.bind.annotation.JsonbPropertyOrder;
import java.io.Serializable;
import java.util.Base64;
import java.util.Objects;

@JsonbPropertyOrder({
    "usuId", "usuNombre", "usuApellidos", "usuCedula", "usuCorreo", "usuTelefono",
    "usuCelular", "usuIdioma", "usuUsuario", "usuClave", "usuEstado", "usuStatus", 
    "usuVersion", "usuFoto"
})

public class UsuariosDTORest implements Serializable {

   
    private Long usuId;

   
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

    public UsuariosDTORest() {}


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

    
     public byte[] getUsuFoto() {
        return usuFoto;
    }

    public void setUsuFoto(byte[] usuFoto) {
        this.usuFoto = usuFoto;
    }
     public String getUsuFotoBase64() {
        return usuFoto != null ? Base64.getEncoder().encodeToString(usuFoto) : null;
    }

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
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.usuId);
        return hash;
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
                ", usuClave='" + usuClave + '\'' +
                ", usuEstado='" + usuEstado + '\'' +
                ", usuStatus='" + usuStatus + '\'' +
                ", usuVersion=" + usuVersion +
                ", usuFoto=" + usuFoto +

                '}';
    }
}
