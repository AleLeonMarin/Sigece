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
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 *
 * @author Kendall Fonseca
 */
@Entity
@Table(name = "SIS_CORREOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SisCorreos.findAll", query = "SELECT s FROM SisCorreos s"),
    @NamedQuery(name = "SisCorreos.findByCorId", query = "SELECT s FROM SisCorreos s WHERE s.corId = :corId"),
    @NamedQuery(name = "SisCorreos.findByCorAsunto", query = "SELECT s FROM SisCorreos s WHERE s.corAsunto = :corAsunto"),
    @NamedQuery(name = "SisCorreos.findByCorDestinatario", query = "SELECT s FROM SisCorreos s WHERE s.corDestinatario = :corDestinatario"),
    @NamedQuery(name = "SisCorreos.findByCorEstado", query = "SELECT s FROM SisCorreos s WHERE s.corEstado = :corEstado"),
    @NamedQuery(name = "SisCorreos.findByCorFecha", query = "SELECT s FROM SisCorreos s WHERE s.corFecha = :corFecha"),
    @NamedQuery(name = "SisCorreos.findByCorVersion", query = "SELECT s FROM SisCorreos s WHERE s.corVersion = :corVersion")})
public class Correos implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "COR_ID")
    private Long corId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "COR_ASUNTO")
    private String corAsunto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "COR_DESTINATARIO")
    private String corDestinatario;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "COR_PLANTILLA")
    private String corPlantilla;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "COR_ESTADO")
    private String corEstado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COR_FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date corFecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COR_VERSION")
    private Long corVersion;
    @JoinColumn(name = "COR_NOT_ID", referencedColumnName = "NOT_ID")
    @ManyToOne(optional = false)
    private Notificacion corNotId;

    public Correos() {
    }

    public Correos(Long corId) {
        this.corId = corId;
    }

    public Correos(Long corId, String corAsunto, String corDestinatario, String corPlantilla, String corEstado, Date corFecha, Long corVersion) {
        this.corId = corId;
        this.corAsunto = corAsunto;
        this.corDestinatario = corDestinatario;
        this.corPlantilla = corPlantilla;
        this.corEstado = corEstado;
        this.corFecha = corFecha;
        this.corVersion = corVersion;
    }

    public Long getCorId() {
        return corId;
    }

    public void setCorId (Long corId) {
        this.corId = corId;
    }

    public String getCorAsunto() {
        return corAsunto;
    }

    public void setCorAsunto(String corAsunto) {
        this.corAsunto = corAsunto;
    }

    public String getCorDestinatario() {
        return corDestinatario;
    }

    public void setCorDestinatario(String corDestinatario) {
        this.corDestinatario = corDestinatario;
    }

    public String getCorPlantilla() {
        return corPlantilla;
    }

    public void setCorPlantilla(String corPlantilla) {
        this.corPlantilla = corPlantilla;
    }

    public String getCorEstado() {
        return corEstado;
    }

    public void setCorEstado(String corEstado) {
        this.corEstado = corEstado;
    }

    public Date getCorFecha() {
        return corFecha;
    }

    public void setCorFecha(Date corFecha) {
        this.corFecha = corFecha;
    }

    public Long getCorVersion() {
        return corVersion;
    }

    public void setCorVersion(Long corVersion) {
        this.corVersion = corVersion;
    }

    public Notificacion getCorNotId() {
        return corNotId;
    }

    public void setCorNotId(Notificacion corNotId) {
        this.corNotId = corNotId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (corId != null ? corId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Correos)) {
            return false;
        }
        Correos other = (Correos) object;
        if ((this.corId == null && other.corId != null) || (this.corId != null && !this.corId.equals(other.corId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.chatandmailapi.SisCorreos[ corId=" + corId + " ]";
    }
    
}
