package cr.ac.una.chatandmailapi.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
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
@Table(name = "SIS_VARIABLES_MULTIMEDIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VariablesMultimedia.findAll", query = "SELECT s FROM VariablesMultimedia s"),
    @NamedQuery(name = "VariablesMultimedia.findByMediaId", query = "SELECT s FROM VariablesMultimedia s WHERE s.mediaId = :mediaId"),
    @NamedQuery(name = "VariablesMultimedia.findByMediaTipo", query = "SELECT s FROM VariablesMultimedia s WHERE s.mediaTipo = :mediaTipo"),
    @NamedQuery(name = "VariablesMultimedia.findByMediaVarId", query = "SELECT s FROM VariablesMultimedia s WHERE s.mediaVarId = :mediaVarId"),
    @NamedQuery(name = "VariablesMultimedia.findByMediaVersion", query = "SELECT s FROM VariablesMultimedia s WHERE s.mediaVersion = :mediaVersion")})
public class VariablesMultimedia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "MEDIA_ID")
    private Long mediaId;

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
    @Column(name = "MEDIA_VAR_ID")
    private Long mediaVarId;

    @Basic(optional = false)
    @NotNull
    @Column(name = "MEDIA_VERSION")
    private Long mediaVersion;

    public VariablesMultimedia() {
    }

    public VariablesMultimedia(Long mediaId) {
        this.mediaId = mediaId;
    }

    public VariablesMultimedia(Long mediaId, Serializable mediaUrl, String mediaTipo, Long mediaVarId, Long mediaVersion) {
        this.mediaId = mediaId;
        this.mediaUrl = mediaUrl;
        this.mediaTipo = mediaTipo;
        this.mediaVarId = mediaVarId;
        this.mediaVersion = mediaVersion;
    }

    public Long getMediaId() {
        return mediaId;
    }

    public void setMediaId(Long mediaId) {
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

    public Long getMediaVarId() {
        return mediaVarId;
    }

    public void setMediaVarId(Long mediaVarId) {
        this.mediaVarId = mediaVarId;
    }

    public Long getMediaVersion() {
        return mediaVersion;
    }

    public void setMediaVersion(Long mediaVersion) {
        this.mediaVersion = mediaVersion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mediaId != null ? mediaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
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
        return "cr.ac.una.chatandmailapi.model.SisVariablesMultimedia[ mediaId=" + mediaId + " ]";
    }
}
