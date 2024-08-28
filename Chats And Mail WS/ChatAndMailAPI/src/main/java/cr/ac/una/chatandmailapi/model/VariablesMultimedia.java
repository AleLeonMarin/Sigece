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
@Table(name = "SIS_VARIABLES_MULTIMEDIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SisVariablesMultimedia.findAll", query = "SELECT s FROM SisVariablesMultimedia s"),
    @NamedQuery(name = "SisVariablesMultimedia.findByMediaId", query = "SELECT s FROM SisVariablesMultimedia s WHERE s.mediaId = :mediaId"),
    @NamedQuery(name = "SisVariablesMultimedia.findByMediaTipo", query = "SELECT s FROM SisVariablesMultimedia s WHERE s.mediaTipo = :mediaTipo"),
    @NamedQuery(name = "SisVariablesMultimedia.findByMediaVersion", query = "SELECT s FROM SisVariablesMultimedia s WHERE s.mediaVersion = :mediaVersion")})
public class VariablesMultimedia implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "MEDIA_ID")
    private BigDecimal mediaId;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "MEDIA_URL")
    private Serializable mediaUrl;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "MEDIA_TIPO")
    private String mediaTipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MEDIA_VERSION")
    private BigInteger mediaVersion;
    @JoinColumn(name = "MEDIA_VAR_ID", referencedColumnName = "VAR_ID")
    @ManyToOne(optional = false)
    private Variables mediaVarId;

    public VariablesMultimedia() {
    }

    public VariablesMultimedia(BigDecimal mediaId) {
        this.mediaId = mediaId;
    }

    public VariablesMultimedia(BigDecimal mediaId, Serializable mediaUrl, String mediaTipo, BigInteger mediaVersion) {
        this.mediaId = mediaId;
        this.mediaUrl = mediaUrl;
        this.mediaTipo = mediaTipo;
        this.mediaVersion = mediaVersion;
    }

    public BigDecimal getMediaId() {
        return mediaId;
    }

    public void setMediaId(BigDecimal mediaId) {
        this.mediaId = mediaId;
    }

    public Serializable getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(Serializable mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public String getMediaTipo() {
        return mediaTipo;
    }

    public void setMediaTipo(String mediaTipo) {
        this.mediaTipo = mediaTipo;
    }

    public BigInteger getMediaVersion() {
        return mediaVersion;
    }

    public void setMediaVersion(BigInteger mediaVersion) {
        this.mediaVersion = mediaVersion;
    }

    public Variables getMediaVarId() {
        return mediaVarId;
    }

    public void setMediaVarId(Variables mediaVarId) {
        this.mediaVarId = mediaVarId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mediaId != null ? mediaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VariablesMultimedia)) {
            return false;
        }
        VariablesMultimedia other = (VariablesMultimedia) object;
        if ((this.mediaId == null && other.mediaId != null) || (this.mediaId != null && !this.mediaId.equals(other.mediaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.chatandmailapi.SisVariablesMultimedia[ mediaId=" + mediaId + " ]";
    }
    
}
