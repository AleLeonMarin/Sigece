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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Kendall Fonseca
 */
@Entity
@Table(name = "SIS_CHATS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SisChats.findAll", query = "SELECT s FROM SisChats s"),
    @NamedQuery(name = "SisChats.findByChtId", query = "SELECT s FROM SisChats s WHERE s.chtId = :chtId"),
    @NamedQuery(name = "SisChats.findByChtFecha", query = "SELECT s FROM SisChats s WHERE s.chtFecha = :chtFecha"),
    @NamedQuery(name = "SisChats.findByChtVersion", query = "SELECT s FROM SisChats s WHERE s.chtVersion = :chtVersion")})
public class SisChats implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CHT_ID")
    private BigDecimal chtId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CHT_FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date chtFecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CHT_VERSION")
    private BigInteger chtVersion;
    @JoinColumn(name = "CHT_RECEPTOR_ID", referencedColumnName = "USU_ID")
    @ManyToOne(optional = false)
    private SisUsuarios chtReceptorId;
    @JoinColumn(name = "CHT_EMISOR_ID", referencedColumnName = "USU_ID")
    @ManyToOne(optional = false)
    private SisUsuarios chtEmisorId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "smsChatId")
    private List<SisMensajes> sisMensajesList;

    public SisChats() {
    }

    public SisChats(BigDecimal chtId) {
        this.chtId = chtId;
    }

    public SisChats(BigDecimal chtId, Date chtFecha, BigInteger chtVersion) {
        this.chtId = chtId;
        this.chtFecha = chtFecha;
        this.chtVersion = chtVersion;
    }

    public BigDecimal getChtId() {
        return chtId;
    }

    public void setChtId(BigDecimal chtId) {
        this.chtId = chtId;
    }

    public Date getChtFecha() {
        return chtFecha;
    }

    public void setChtFecha(Date chtFecha) {
        this.chtFecha = chtFecha;
    }

    public BigInteger getChtVersion() {
        return chtVersion;
    }

    public void setChtVersion(BigInteger chtVersion) {
        this.chtVersion = chtVersion;
    }

    public SisUsuarios getChtReceptorId() {
        return chtReceptorId;
    }

    public void setChtReceptorId(SisUsuarios chtReceptorId) {
        this.chtReceptorId = chtReceptorId;
    }

    public SisUsuarios getChtEmisorId() {
        return chtEmisorId;
    }

    public void setChtEmisorId(SisUsuarios chtEmisorId) {
        this.chtEmisorId = chtEmisorId;
    }

    @XmlTransient
    public List<SisMensajes> getSisMensajesList() {
        return sisMensajesList;
    }

    public void setSisMensajesList(List<SisMensajes> sisMensajesList) {
        this.sisMensajesList = sisMensajesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (chtId != null ? chtId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SisChats)) {
            return false;
        }
        SisChats other = (SisChats) object;
        if ((this.chtId == null && other.chtId != null) || (this.chtId != null && !this.chtId.equals(other.chtId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.chatandmailapi.SisChats[ chtId=" + chtId + " ]";
    }
    
}
