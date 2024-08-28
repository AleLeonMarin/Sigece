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
@Table(name = "SIS_SISTEMAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SisSistemas.findAll", query = "SELECT s FROM SisSistemas s"),
    @NamedQuery(name = "SisSistemas.findBySisId", query = "SELECT s FROM SisSistemas s WHERE s.sisId = :sisId"),
    @NamedQuery(name = "SisSistemas.findBySisNombre", query = "SELECT s FROM SisSistemas s WHERE s.sisNombre = :sisNombre"),
    @NamedQuery(name = "SisSistemas.findBySisVersion", query = "SELECT s FROM SisSistemas s WHERE s.sisVersion = :sisVersion")})
public class Sistemas implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "SIS_ID")
    private BigDecimal sisId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "SIS_NOMBRE")
    private String sisNombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SIS_VERSION")
    private BigInteger sisVersion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rolSisId")
    private List<Roles> sisRolesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "srsSisId")
    private List<SistemasRolesUsuarios> sisSistemasRolesUsuariosList;

    public Sistemas() {
    }

    public Sistemas(BigDecimal sisId) {
        this.sisId = sisId;
    }

    public Sistemas(BigDecimal sisId, String sisNombre, BigInteger sisVersion) {
        this.sisId = sisId;
        this.sisNombre = sisNombre;
        this.sisVersion = sisVersion;
    }

    public BigDecimal getSisId() {
        return sisId;
    }

    public void setSisId(BigDecimal sisId) {
        this.sisId = sisId;
    }

    public String getSisNombre() {
        return sisNombre;
    }

    public void setSisNombre(String sisNombre) {
        this.sisNombre = sisNombre;
    }

    public BigInteger getSisVersion() {
        return sisVersion;
    }

    public void setSisVersion(BigInteger sisVersion) {
        this.sisVersion = sisVersion;
    }

    @XmlTransient
    public List<Roles> getSisRolesList() {
        return sisRolesList;
    }

    public void setSisRolesList(List<Roles> sisRolesList) {
        this.sisRolesList = sisRolesList;
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
        hash += (sisId != null ? sisId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sistemas)) {
            return false;
        }
        Sistemas other = (Sistemas) object;
        if ((this.sisId == null && other.sisId != null) || (this.sisId != null && !this.sisId.equals(other.sisId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.chatandmailapi.SisSistemas[ sisId=" + sisId + " ]";
    }
    
}
