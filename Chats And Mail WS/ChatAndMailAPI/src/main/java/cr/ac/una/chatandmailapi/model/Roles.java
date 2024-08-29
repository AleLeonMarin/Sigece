/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.chatandmailapi.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name = "SIS_ROLES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SisRoles.findAll", query = "SELECT s FROM SisRoles s"),
    @NamedQuery(name = "SisRoles.findByRolId", query = "SELECT s FROM SisRoles s WHERE s.rolId = :rolId"),
    @NamedQuery(name = "SisRoles.findByRolNombre", query = "SELECT s FROM SisRoles s WHERE s.rolNombre = :rolNombre"),
    @NamedQuery(name = "SisRoles.findByRolSisId", query = "SELECT s FROM SisRoles s WHERE s.rolSisId = :rolSisId"),
    @NamedQuery(name = "SisRoles.findByRolVersion", query = "SELECT s FROM SisRoles s WHERE s.rolVersion = :rolVersion")})
public class Roles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ROL_ID")
    private Long rolId;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "ROL_NOMBRE")
    private String rolNombre;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "ROL_SIS_ID")
    private Long rolSisId;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "ROL_VERSION")
    private Long rolVersion;

    public Roles() {
    }

    public Roles(Long rolId) {
        this.rolId = rolId;
    }

    public Roles(Long rolId, String rolNombre, Long rolSisId, Long rolVersion) {
        this.rolId = rolId;
        this.rolNombre = rolNombre;
        this.rolSisId = rolSisId;
        this.rolVersion = rolVersion;
    }

    public Long getRolId() {
        return rolId;
    }

    public void setRolId(Long rolId) {
        this.rolId = rolId;
    }

    public String getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(String rolNombre) {
        this.rolNombre = rolNombre;
    }

    public Long getRolSisId() {
        return rolSisId;
    }

    public void setRolSisId(Long rolSisId) {
        this.rolSisId = rolSisId;
    }

    public Long getRolVersion() {
        return rolVersion;
    }

    public void setRolVersion(Long rolVersion) {
        this.rolVersion = rolVersion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rolId != null ? rolId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
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
        return "cr.ac.una.chatandmailapi.model.SisRoles[ rolId=" + rolId + " ]";
    }
    
}
