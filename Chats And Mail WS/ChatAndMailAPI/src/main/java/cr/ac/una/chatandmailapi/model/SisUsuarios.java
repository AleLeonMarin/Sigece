/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.chatandmailapi.model;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author Kendall Fonseca
 */
@Entity
@Table(name = "SIS_USUARIOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SisUsuarios.findAll", query = "SELECT s FROM SisUsuarios s"),
    @NamedQuery(name = "SisUsuarios.findByUsuId", query = "SELECT s FROM SisUsuarios s WHERE s.usuId = :usuId"),
    @NamedQuery(name = "SisUsuarios.findByUsuNombre", query = "SELECT s FROM SisUsuarios s WHERE s.usuNombre = :usuNombre"),
    @NamedQuery(name = "SisUsuarios.findByUsuApellidos", query = "SELECT s FROM SisUsuarios s WHERE s.usuApellidos = :usuApellidos"),
    @NamedQuery(name = "SisUsuarios.findByUsuCorreo", query = "SELECT s FROM SisUsuarios s WHERE s.usuCorreo = :usuCorreo"),
    @NamedQuery(name = "SisUsuarios.findByUsuTelefono", query = "SELECT s FROM SisUsuarios s WHERE s.usuTelefono = :usuTelefono"),
    @NamedQuery(name = "SisUsuarios.findByUsuCelular", query = "SELECT s FROM SisUsuarios s WHERE s.usuCelular = :usuCelular"),
    @NamedQuery(name = "SisUsuarios.findByUsuIdioma", query = "SELECT s FROM SisUsuarios s WHERE s.usuIdioma = :usuIdioma"),
    @NamedQuery(name = "SisUsuarios.findByUsuUsuario", query = "SELECT s FROM SisUsuarios s WHERE s.usuUsuario = :usuUsuario"),
    @NamedQuery(name = "SisUsuarios.findByUsuClave", query = "SELECT s FROM SisUsuarios s WHERE s.usuClave = :usuClave"),
    @NamedQuery(name = "SisUsuarios.findByUsuEstado", query = "SELECT s FROM SisUsuarios s WHERE s.usuEstado = :usuEstado"),
    @NamedQuery(name = "SisUsuarios.findByUsuStatus", query = "SELECT s FROM SisUsuarios s WHERE s.usuStatus = :usuStatus"),
    @NamedQuery(name = "SisUsuarios.findByUsuVersion", query = "SELECT s FROM SisUsuarios s WHERE s.usuVersion = :usuVersion")})
public class SisUsuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "USU_ID")
    private BigDecimal usuId;
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
    private Serializable usuFoto;
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
    private BigInteger usuVersion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chtReceptorId")
    private List<SisChats> sisChatsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chtEmisorId")
    private List<SisChats> sisChatsList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "smsUsuId")
    private List<SisMensajes> sisMensajesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "srsUsuId")
    private List<SisSistemasRolesUsuarios> sisSistemasRolesUsuariosList;

    public SisUsuarios() {
    }

    public SisUsuarios(BigDecimal usuId) {
        this.usuId = usuId;
    }

    public SisUsuarios(BigDecimal usuId, String usuNombre, String usuApellidos, String usuCorreo, String usuTelefono, String usuCelular, String usuIdioma, Serializable usuFoto, String usuUsuario, String usuClave, String usuEstado, String usuStatus, BigInteger usuVersion) {
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

    public BigDecimal getUsuId() {
        return usuId;
    }

    public void setUsuId(BigDecimal usuId) {
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

    public BigInteger getUsuVersion() {
        return usuVersion;
    }

    public void setUsuVersion(BigInteger usuVersion) {
        this.usuVersion = usuVersion;
    }

    @XmlTransient
    public List<SisChats> getSisChatsList() {
        return sisChatsList;
    }

    public void setSisChatsList(List<SisChats> sisChatsList) {
        this.sisChatsList = sisChatsList;
    }

    @XmlTransient
    public List<SisChats> getSisChatsList1() {
        return sisChatsList1;
    }

    public void setSisChatsList1(List<SisChats> sisChatsList1) {
        this.sisChatsList1 = sisChatsList1;
    }

    @XmlTransient
    public List<SisMensajes> getSisMensajesList() {
        return sisMensajesList;
    }

    public void setSisMensajesList(List<SisMensajes> sisMensajesList) {
        this.sisMensajesList = sisMensajesList;
    }

    @XmlTransient
    public List<SisSistemasRolesUsuarios> getSisSistemasRolesUsuariosList() {
        return sisSistemasRolesUsuariosList;
    }

    public void setSisSistemasRolesUsuariosList(List<SisSistemasRolesUsuarios> sisSistemasRolesUsuariosList) {
        this.sisSistemasRolesUsuariosList = sisSistemasRolesUsuariosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuId != null ? usuId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SisUsuarios)) {
            return false;
        }
        SisUsuarios other = (SisUsuarios) object;
        if ((this.usuId == null && other.usuId != null) || (this.usuId != null && !this.usuId.equals(other.usuId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.chatandmailapi.SisUsuarios[ usuId=" + usuId + " ]";
    }
    
}
