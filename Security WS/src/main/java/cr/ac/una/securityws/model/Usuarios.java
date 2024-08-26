package cr.ac.una.securityws.model;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.QueryHint;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aleon
 */

@Entity
@Table(name = "SIS_USUARIOS")
@NamedQueries({
        @NamedQuery(name = "SisUsuarios.findAll", query = "SELECT s FROM SisUsuarios s"),
        @NamedQuery(name = "SisUsuarios.findByUsuId", query = "SELECT s FROM SisUsuarios s WHERE s.usuId = :usuId"),
        @NamedQuery(name = "Usuarios,findByUsuClave", query = "SELECT s FROM SisUsuarios s WHERE s.usuario = :usuUsuario and s.usuClave = :clave", hints = @QueryHint(name = "eclipselink.refresh", value = "true")) }
/*
 * @NamedQuery(name = "SisUsuarios.findByUsuNombre", query =
 * "SELECT s FROM SisUsuarios s WHERE s.usuNombre = :usuNombre"),
 * 
 * @NamedQuery(name = "SisUsuarios.findByUsuApellidos", query =
 * "SELECT s FROM SisUsuarios s WHERE s.usuApellidos = :usuApellidos"),
 * 
 * @NamedQuery(name = "SisUsuarios.findByUsuCorreo", query =
 * "SELECT s FROM SisUsuarios s WHERE s.usuCorreo = :usuCorreo"),
 * 
 * @NamedQuery(name = "SisUsuarios.findByUsuTelefono", query =
 * "SELECT s FROM SisUsuarios s WHERE s.usuTelefono = :usuTelefono"),
 * 
 * @NamedQuery(name = "SisUsuarios.findByUsuCelular", query =
 * "SELECT s FROM SisUsuarios s WHERE s.usuCelular = :usuCelular"),
 * 
 * @NamedQuery(name = "SisUsuarios.findByUsuIdioma", query =
 * "SELECT s FROM SisUsuarios s WHERE s.usuIdioma = :usuIdioma"),
 * 
 * @NamedQuery(name = "SisUsuarios.findByUsuUsuario", query =
 * "SELECT s FROM SisUsuarios s WHERE s.usuUsuario = :usuUsuario"),
 * 
 * @NamedQuery(name = "SisUsuarios.findByUsuClave", query =
 * "SELECT s FROM SisUsuarios s WHERE s.usuClave = :usuClave"),
 * 
 * @NamedQuery(name = "SisUsuarios.findByUsuEstado", query =
 * "SELECT s FROM SisUsuarios s WHERE s.usuEstado = :usuEstado"),
 * 
 * @NamedQuery(name = "SisUsuarios.findByUsuStatus", query =
 * "SELECT s FROM SisUsuarios s WHERE s.usuStatus = :usuStatus"),
 * 
 * @NamedQuery(name = "SisUsuarios.findByUsuVersion", query =
 * "SELECT s FROM SisUsuarios s WHERE s.usuVersion = :usuVersion") }
 */)
public class Usuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?) @Min(value=?)//if you know range of your decimal fields
    // consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "SIS_USUARIOS_ID_GENERATOR", sequenceName = "sigeceuna.SIS_USUARIOS_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIS_USUARIOS_ID_GENERATOR")
    @Basic(optional = false)
    @Column(name = "USU_ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "USU_NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "USU_APELLIDOS")
    private String apellidos;
    @Basic(optional = false)
    @Column(name = "USU_CORREO")
    private String correo;
    @Basic(optional = false)
    @Column(name = "USU_TELEFONO")
    private String telefono;
    @Basic(optional = false)
    @Column(name = "USU_CELULAR")
    private String celular;
    @Basic(optional = false)
    @Column(name = "USU_IDIOMA")
    private String idioma;
    @Basic(optional = false)
    @Lob
    @Column(name = "USU_FOTO")
    private Serializable foto;
    @Basic(optional = false)
    @Column(name = "USU_USUARIO")
    private String usuario;
    @Basic(optional = false)
    @Column(name = "USU_CLAVE")
    private String clave;
    @Basic(optional = false)
    @Column(name = "USU_ESTADO")
    private String estado;
    @Basic(optional = false)
    @Column(name = "USU_STATUS")
    private String status;
    @Version
    @Column(name = "USU_VERSION")
    private Long version;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "srsUsuId")
    private List<SistemasRolesUsuarios> sisRolesUsuarios = new ArrayList<>();

    public Usuarios() {
    }

    public Usuarios(Long id) {
        this.id = id;
    }

    public Usuarios(UsuariosDto usuariosDto) {
        this.id = usuariosDto.getId();
        actualizarUsuario(usuariosDto);
    }

    public void actualizarUsuario(UsuariosDto usuariosDto) {
        this.nombre = usuariosDto.getNombre();
        this.apellidos = usuariosDto.getApellidos();
        this.correo = usuariosDto.getCorreo();
        this.telefono = usuariosDto.getTelefono();
        this.celular = usuariosDto.getCelular();
        this.idioma = usuariosDto.getIdioma();
        this.foto = usuariosDto.getFoto();
        this.usuario = usuariosDto.getUsuario();
        this.clave = usuariosDto.getClave();
        this.estado = usuariosDto.getEstado();
        this.status = usuariosDto.getStatus();
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

    public Serializable getFoto() {
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

    public List<SistemasRolesUsuarios> getSisRolesUsuarios() {
        return sisRolesUsuarios;
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

    public void setFoto(Serializable foto) {
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

    public void setSisRolesUsuarios(List<SistemasRolesUsuarios> sisRolesUsuarios) {
        this.sisRolesUsuarios = sisRolesUsuarios;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }

        return true;
    }

    @Override

    public String toString() {
        return "cr.ac.una.securityws.model.Usuarios[ usuId=" + id + " ]";
    }

}
