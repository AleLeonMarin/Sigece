/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.chatandmailapi.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 *
 * @author Kendall Fonseca
 */
@Entity
@Table(name = "SIS_SISTEMAS_ROLES_USUARIOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SisSistemasRolesUsuarios.findAll", query = "SELECT s FROM SisSistemasRolesUsuarios s"),
    @NamedQuery(name = "SisSistemasRolesUsuarios.findBySrsId", query = "SELECT s FROM SisSistemasRolesUsuarios s WHERE s.srsId = :srsId"),
    @NamedQuery(name = "SisSistemasRolesUsuarios.findBySrsNombre", query = "SELECT s FROM SisSistemasRolesUsuarios s WHERE s.srsNombre = :srsNombre"),
    @NamedQuery(name = "SisSistemasRolesUsuarios.findBySrsVersion", query = "SELECT s FROM SisSistemasRolesUsuarios s WHERE s.srsVersion = :srsVersion")})
public class SisSistemasRolesUsuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "SRS_ID")
    private BigDecimal srsId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "SRS_NOMBRE")
    private String srsNombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SRS_VERSION")
    private BigInteger srsVersion;
    @JoinColumn(name = "SRS_ROL_ID", referencedColumnName = "ROL_ID")
    @ManyToOne(optional = false)
    private SisRoles srsRolId;
    @JoinColumn(name = "SRS_SIS_ID", referencedColumnName = "SIS_ID")
    @ManyToOne(optional = false)
    private SisSistemas srsSisId;
    @JoinColumn(name = "SRS_USU_ID", referencedColumnName = "USU_ID")
    @ManyToOne(optional = false)
    private SisUsuarios srsUsuId;

    public SisSistemasRolesUsuarios() {
    }

    public SisSistemasRolesUsuarios(BigDecimal srsId) {
        this.srsId = srsId;
    }

    public SisSistemasRolesUsuarios(BigDecimal srsId, String srsNombre, BigInteger srsVersion) {
        this.srsId = srsId;
        this.srsNombre = srsNombre;
        this.srsVersion = srsVersion;
    }

    public BigDecimal getSrsId() {
        return srsId;
    }

    public void setSrsId(BigDecimal srsId) {
        this.srsId = srsId;
    }

    public String getSrsNombre() {
        return srsNombre;
    }

    public void setSrsNombre(String srsNombre) {
        this.srsNombre = srsNombre;
    }

    public BigInteger getSrsVersion() {
        return srsVersion;
    }

    public void setSrsVersion(BigInteger srsVersion) {
        this.srsVersion = srsVersion;
    }

    public SisRoles getSrsRolId() {
        return srsRolId;
    }

    public void setSrsRolId(SisRoles srsRolId) {
        this.srsRolId = srsRolId;
    }

    public SisSistemas getSrsSisId() {
        return srsSisId;
    }

    public void setSrsSisId(SisSistemas srsSisId) {
        this.srsSisId = srsSisId;
    }

    public SisUsuarios getSrsUsuId() {
        return srsUsuId;
    }

    public void setSrsUsuId(SisUsuarios srsUsuId) {
        this.srsUsuId = srsUsuId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (srsId != null ? srsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SisSistemasRolesUsuarios)) {
            return false;
        }
        SisSistemasRolesUsuarios other = (SisSistemasRolesUsuarios) object;
        if ((this.srsId == null && other.srsId != null) || (this.srsId != null && !this.srsId.equals(other.srsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.chatandmailapi.SisSistemasRolesUsuarios[ srsId=" + srsId + " ]";
    }
    
}
