package cr.ac.una.chatandmailapi.model;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author Kendall Fonseca
 */
@Entity
@Table(name = "SIS_USUARIOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuarios.findAll", query = "SELECT s FROM Usuarios s"),
    @NamedQuery(name = "Usuarios.findByUsuId", query = "SELECT s FROM Usuarios s WHERE s.usuId = :usuId"),
    @NamedQuery(name = "Usuarios.findByUsuNombre", query = "SELECT s FROM Usuarios s WHERE s.usuNombre = :usuNombre"),
    @NamedQuery(name = "Usuarios.findByUsuApellidos", query = "SELECT s FROM Usuarios s WHERE s.usuApellidos = :usuApellidos"),
    @NamedQuery(name = "Usuarios.findByUsuCedula", query = "SELECT s FROM Usuarios s WHERE s.usuCedula = :usuCedula"),
    @NamedQuery(name = "Usuarios.findByUsuCorreo", query = "SELECT s FROM Usuarios s WHERE s.usuCorreo = :usuCorreo"),
    @NamedQuery(name = "Usuarios.findByUsuTelefono", query = "SELECT s FROM Usuarios s WHERE s.usuTelefono = :usuTelefono"),
    @NamedQuery(name = "Usuarios.findByUsuCelular", query = "SELECT s FROM Usuarios s WHERE s.usuCelular = :usuCelular"),
    @NamedQuery(name = "Usuarios.findByUsuIdioma", query = "SELECT s FROM Usuarios s WHERE s.usuIdioma = :usuIdioma"),
    @NamedQuery(name = "Usuarios.findByUsuUsuario", query = "SELECT s FROM Usuarios s WHERE s.usuUsuario = :usuUsuario"),
    @NamedQuery(name = "Usuarios.findByUsuClave", query = "SELECT s FROM Usuarios s WHERE s.usuClave = :usuClave"),
    @NamedQuery(name = "Usuarios.findByUsuEstado", query = "SELECT s FROM Usuarios s WHERE s.usuEstado = :usuEstado"),
    @NamedQuery(name = "Usuarios.findByUsuStatus", query = "SELECT s FROM Usuarios s WHERE s.usuStatus = :usuStatus"),
    @NamedQuery(name = "Usuarios.findByUsuVersion", query = "SELECT s FROM Usuarios s WHERE s.usuVersion = :usuVersion")})
public class Usuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "USU_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long usuId;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "USU_NOMBRE")
    private String usuNombre;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "USU_APELLIDOS")
    private String usuApellidos;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "USU_CEDULA")
    private String usuCedula;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "USU_CORREO")
    private String usuCorreo;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "USU_TELEFONO")
    private String usuTelefono;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "USU_CELULAR")
    private String usuCelular;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "USU_IDIOMA")
    private String usuIdioma;
    
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "USU_FOTO")
    @JsonbTransient
    private byte[] usuFoto;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "USU_USUARIO")
    private String usuUsuario;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "USU_CLAVE")
    private String usuClave;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "USU_ESTADO")
    private String usuEstado;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "USU_STATUS")
    private String usuStatus;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "USU_VERSION")
    private Long usuVersion;

    public Usuarios() {
    }

    public Usuarios(Long usuId) {
        this.usuId = usuId;
    }

    public Usuarios(UsuariosDTO usuarioDTO) {
        this.usuId = usuarioDTO.getUsuId();
        actualizar(usuarioDTO);
    }
    
    public void actualizar(UsuariosDTO usuariosDto) {
    this.usuNombre = usuariosDto.getUsuNombre();
    this.usuApellidos = usuariosDto.getUsuApellidos();
    this.usuCedula = usuariosDto.getUsuCedula();
    this.usuCorreo = usuariosDto.getUsuCorreo();
    this.usuTelefono = usuariosDto.getUsuTelefono();
    this.usuCelular = usuariosDto.getUsuCelular();
    this.usuIdioma = usuariosDto.getUsuIdioma();
  
    this.usuUsuario = usuariosDto.getUsuUsuario();
    this.usuClave = usuariosDto.getUsuClave();
    this.usuEstado = usuariosDto.getUsuEstado();
    this.usuStatus = usuariosDto.getUsuStatus();
    this.usuVersion = usuariosDto.getUsuVersion();
   
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
        int hash = 0;
        hash += (usuId != null ? usuId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        return !((this.usuId == null && other.usuId != null) || (this.usuId != null && !this.usuId.equals(other.usuId)));
    }

    @Override
    public String toString() {
        return "cr.ac.una.chatandmailapi.model.SisUsuarios[ usuId=" + usuId + " ]";
    }
}
