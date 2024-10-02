/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.securityws.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
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
@Table(name = "SIS_USUARIOS", schema = "SigeceUNA")
@NamedQueries({
        @NamedQuery(name = "Usuarios.findAll", query = "SELECT s FROM Usuarios s"),
        @NamedQuery(name = "Usuarios.findByUsuClave", query = "SELECT s FROM Usuarios s LEFT JOIN FETCH s.roles WHERE s.usuario = :usuario AND s.clave = :clave"),
        @NamedQuery(name = "Usuarios.findById", query = "SELECT s FROM Usuarios s WHERE s.id = :id"),
        @NamedQuery(name = "Usuarios.findByCorreo", query = "SELECT s FROM Usuarios s WHERE s.correo = :correo"),
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
 * "SELECT s FROM SisUsuarios s WHERE s.usuVersion = :usuVersion")
 */ })
public class Usuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?) @Min(value=?)//if you know range of your decimal fields
    // consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "GENERATOR_USUARIOS_SEQUENCE", sequenceName = "sigeceuna.SIS_USUARIOS_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GENERATOR_USUARIOS_SEQUENCE")
    @Column(name = "USU_ID")
    private Long id;

    @Basic(optional = false)
    @Column(name = "USU_NOMBRE")
    private String nombre;

    @Basic(optional = false)
    @Column(name = "USU_APELLIDOS")
    private String apellidos;

    @Basic(optional = false)
    @Column(name = "USU_CEDULA")
    private String cedula;

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
    private byte[] foto;

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

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "usuarios")
    private List<Roles> roles;

    public Usuarios() {
        this.roles = new ArrayList<>();
    }

    public Usuarios(Long id) {
        this();
        this.id = id;
    }

    public Usuarios(UsuariosDto usuariosDto) {
        this();
        this.id = usuariosDto.getId();
        actualizar(usuariosDto);
    }

    public void actualizar(UsuariosDto usuariosDto) {
        this.nombre = usuariosDto.getNombre();
        this.apellidos = usuariosDto.getApellidos();
        this.cedula = usuariosDto.getCedula();
        this.correo = usuariosDto.getCorreo();
        this.telefono = usuariosDto.getTelefono();
        this.celular = usuariosDto.getCelular();
        this.idioma = usuariosDto.getIdioma();
        this.foto = usuariosDto.getFoto();
        this.usuario = usuariosDto.getUsuario();
        this.clave = usuariosDto.getClave();
        this.estado = usuariosDto.getEstado();
        this.status = usuariosDto.getStatus();
        this.version = usuariosDto.getVersion();
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

    public String getCedula() {
        return cedula;
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

    public List<Roles> getRoles() {
        return roles;
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

    public void setCedula(String cedula) {
        this.cedula = cedula;
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

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
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
        return "cr.ac.una.securityws.model.SisUsuarios[ id=" + id + " ]";
    }

}
