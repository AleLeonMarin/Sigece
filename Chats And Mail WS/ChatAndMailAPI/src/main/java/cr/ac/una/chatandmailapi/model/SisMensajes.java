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
@Table(name = "SIS_MENSAJES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SisMensajes.findAll", query = "SELECT s FROM SisMensajes s"),
    @NamedQuery(name = "SisMensajes.findBySmsId", query = "SELECT s FROM SisMensajes s WHERE s.smsId = :smsId"),
    @NamedQuery(name = "SisMensajes.findBySmsTiempo", query = "SELECT s FROM SisMensajes s WHERE s.smsTiempo = :smsTiempo"),
    @NamedQuery(name = "SisMensajes.findBySmsVersion", query = "SELECT s FROM SisMensajes s WHERE s.smsVersion = :smsVersion")})
public class SisMensajes implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "SMS_ID")
    private BigDecimal smsId;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "SMS_TEXTO")
    private String smsTexto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SMS_TIEMPO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date smsTiempo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SMS_VERSION")
    private BigInteger smsVersion;
    @JoinColumn(name = "SMS_CHAT_ID", referencedColumnName = "CHT_ID")
    @ManyToOne(optional = false)
    private SisChats smsChatId;
    @JoinColumn(name = "SMS_USU_ID", referencedColumnName = "USU_ID")
    @ManyToOne(optional = false)
    private SisUsuarios smsUsuId;

    public SisMensajes() {
    }

    public SisMensajes(BigDecimal smsId) {
        this.smsId = smsId;
    }

    public SisMensajes(BigDecimal smsId, String smsTexto, Date smsTiempo, BigInteger smsVersion) {
        this.smsId = smsId;
        this.smsTexto = smsTexto;
        this.smsTiempo = smsTiempo;
        this.smsVersion = smsVersion;
    }

    public BigDecimal getSmsId() {
        return smsId;
    }

    public void setSmsId(BigDecimal smsId) {
        this.smsId = smsId;
    }

    public String getSmsTexto() {
        return smsTexto;
    }

    public void setSmsTexto(String smsTexto) {
        this.smsTexto = smsTexto;
    }

    public Date getSmsTiempo() {
        return smsTiempo;
    }

    public void setSmsTiempo(Date smsTiempo) {
        this.smsTiempo = smsTiempo;
    }

    public BigInteger getSmsVersion() {
        return smsVersion;
    }

    public void setSmsVersion(BigInteger smsVersion) {
        this.smsVersion = smsVersion;
    }

    public SisChats getSmsChatId() {
        return smsChatId;
    }

    public void setSmsChatId(SisChats smsChatId) {
        this.smsChatId = smsChatId;
    }

    public SisUsuarios getSmsUsuId() {
        return smsUsuId;
    }

    public void setSmsUsuId(SisUsuarios smsUsuId) {
        this.smsUsuId = smsUsuId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (smsId != null ? smsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SisMensajes)) {
            return false;
        }
        SisMensajes other = (SisMensajes) object;
        if ((this.smsId == null && other.smsId != null) || (this.smsId != null && !this.smsId.equals(other.smsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.chatandmailapi.SisMensajes[ smsId=" + smsId + " ]";
    }
    
}