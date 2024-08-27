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
@Table(name = "SIS_NOTIFICACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SisNotificacion.findAll", query = "SELECT s FROM SisNotificacion s"),
    @NamedQuery(name = "SisNotificacion.findByNotId", query = "SELECT s FROM SisNotificacion s WHERE s.notId = :notId"),
    @NamedQuery(name = "SisNotificacion.findByNotNombre", query = "SELECT s FROM SisNotificacion s WHERE s.notNombre = :notNombre"),
    @NamedQuery(name = "SisNotificacion.findByNotVersion", query = "SELECT s FROM SisNotificacion s WHERE s.notVersion = :notVersion")})
public class SisNotificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "NOT_ID")
    private BigDecimal notId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "NOT_NOMBRE")
    private String notNombre;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "NOT_PLANTILLA")
    private String notPlantilla;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NOT_VERSION")
    private BigInteger notVersion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "varNotId")
    private List<SisVariables> sisVariablesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "corNotId")
    private List<SisCorreos> sisCorreosList;

    public SisNotificacion() {
    }

    public SisNotificacion(BigDecimal notId) {
        this.notId = notId;
    }

    public SisNotificacion(BigDecimal notId, String notNombre, String notPlantilla, BigInteger notVersion) {
        this.notId = notId;
        this.notNombre = notNombre;
        this.notPlantilla = notPlantilla;
        this.notVersion = notVersion;
    }

    public BigDecimal getNotId() {
        return notId;
    }

    public void setNotId(BigDecimal notId) {
        this.notId = notId;
    }

    public String getNotNombre() {
        return notNombre;
    }

    public void setNotNombre(String notNombre) {
        this.notNombre = notNombre;
    }

    public String getNotPlantilla() {
        return notPlantilla;
    }

    public void setNotPlantilla(String notPlantilla) {
        this.notPlantilla = notPlantilla;
    }

    public BigInteger getNotVersion() {
        return notVersion;
    }

    public void setNotVersion(BigInteger notVersion) {
        this.notVersion = notVersion;
    }

    @XmlTransient
    public List<SisVariables> getSisVariablesList() {
        return sisVariablesList;
    }

    public void setSisVariablesList(List<SisVariables> sisVariablesList) {
        this.sisVariablesList = sisVariablesList;
    }

    @XmlTransient
    public List<SisCorreos> getSisCorreosList() {
        return sisCorreosList;
    }

    public void setSisCorreosList(List<SisCorreos> sisCorreosList) {
        this.sisCorreosList = sisCorreosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (notId != null ? notId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SisNotificacion)) {
            return false;
        }
        SisNotificacion other = (SisNotificacion) object;
        if ((this.notId == null && other.notId != null) || (this.notId != null && !this.notId.equals(other.notId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.chatandmailapi.SisNotificacion[ notId=" + notId + " ]";
    }
    
}
