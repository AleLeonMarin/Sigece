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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "SIS_ROLES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SisRoles.findAll", query = "SELECT s FROM SisRoles s"),
    @NamedQuery(name = "SisRoles.findByRolId", query = "SELECT s FROM SisRoles s WHERE s.rolId = :rolId"),
    @NamedQuery(name = "SisRoles.findByRolNombre", query = "SELECT s FROM SisRoles s WHERE s.rolNombre = :rolNombre"),
    @NamedQuery(name = "SisRoles.findByRolVersion", query = "SELECT s FROM SisRoles s WHERE s.rolVersion = :rolVersion")})
public class Roles implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ROL_ID")
    private BigDecimal rolId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "ROL_NOMBRE")
    private String rolNombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ROL_VERSION")
    private BigInteger rolVersion;
    @JoinColumn(name = "ROL_SIS_ID", referencedColumnName = "SIS_ID")
    @ManyToOne(optional = false)
    private Sistemas rolSisId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "srsRolId")
    private List<SistemasRolesUsuarios> sisSistemasRolesUsuariosList;

    public Roles() {
    }

    public Roles(BigDecimal rolId) {
        this.rolId = rolId;
    }

    public Roles(BigDecimal rolId, String rolNombre, BigInteger rolVersion) {
        this.rolId = rolId;
        this.rolNombre = rolNombre;
        this.rolVersion = rolVersion;
    }

    public BigDecimal getRolId() {
        return rolId;
    }

    public void setRolId(BigDecimal rolId) {
        this.rolId = rolId;
    }

    public String getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(String rolNombre) {
        this.rolNombre = rolNombre;
    }

    public BigInteger getRolVersion() {
        return rolVersion;
    }

    public void setRolVersion(BigInteger rolVersion) {
        this.rolVersion = rolVersion;
    }

    public Sistemas getRolSisId() {
        return rolSisId;
    }

    public void setRolSisId(Sistemas rolSisId) {
        this.rolSisId = rolSisId;
    }

    @XmlTransient
    public List<SistemasRolesUsuarios> getSisSistemasRolesUsuariosList() {
        return sisSistemasRolesUsuariosList;
    }

    public void setSisSistemasRolesUsuariosList(List<SistemasRolesUsuarios> sisSistemasRolesUsuariosList) {
        this.sisSistemasRolesUsuariosList = sisSistemasRolesUsuariosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rolId != null ? rolId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Roles)) {
            return false;
        }
        Roles other = (Roles) object;
        if ((this.rolId == null && other.rolId != null) || (this.rolId != null && !this.rolId.equals(other.rolId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.chatandmailapi.SisRoles[ rolId=" + rolId + " ]";
    }
    
}
