package cr.ac.una.chatandmailapi.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@Table(name = "SIS_VARIABLES_MULTIMEDIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VariablesMultimedia.findAll", query = "SELECT s FROM VariablesMultimedia s"),
    @NamedQuery(name = "VariablesMultimedia.findByMediaId", query = "SELECT s FROM VariablesMultimedia s WHERE s.mediaId = :mediaId"),
    @NamedQuery(name = "VariablesMultimedia.findByMediaTipo", query = "SELECT s FROM VariablesMultimedia s WHERE s.mediaTipo = :mediaTipo"),
    @NamedQuery(name = "VariablesMultimedia.findByMediaVersion", query = "SELECT s FROM VariablesMultimedia s WHERE s.mediaVersion = :mediaVersion")
})
public class VariablesMultimedia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sis_variables_multimedia_seq")
    @SequenceGenerator(name = "sis_variables_multimedia_seq", sequenceName = "SIS_VARIABLES_MULTIMEDIA_SEQ01", allocationSize = 1)
    @Column(name = "MEDIA_ID")
    private long mediaId;

    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "MEDIA_URL")
    private String mediaUrl;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "MEDIA_TIPO")
    private String mediaTipo;

    @Basic(optional = false)
    @NotNull
    @Column(name = "MEDIA_VERSION")
    private long mediaVersion;

    @JoinColumn(name = "MEDIA_VAR_ID", referencedColumnName = "VAR_ID")
    @ManyToOne(optional = false)
    private Variables mediaVarId;

    public VariablesMultimedia() {
    }

    public VariablesMultimedia(long mediaId) {
        this.mediaId = mediaId;
    }
    public VariablesMultimedia(VariablesMultimediaDTO variablesMultimediaDto) {
        this.mediaId = variablesMultimediaDto.getMediaId();
        actualizar(variablesMultimediaDto);
}


    public void actualizar(VariablesMultimediaDTO variablesMultimediaDto) {
        this.mediaUrl = (String) variablesMultimediaDto.getMediaUrl();
        this.mediaTipo = variablesMultimediaDto.getMediaTipo();
        this.mediaVersion = variablesMultimediaDto.getMediaVersion();

      if (variablesMultimediaDto.getMediaVarId() != null) {
        Variables variable = new Variables();
        variable.setVarId(variablesMultimediaDto.getMediaVarId().getVarId());
        this.mediaVarId = variable;
        }
    }


    public long getMediaId() {
        return mediaId;
    }

    public void setMediaId(long mediaId) {
        this.mediaId = mediaId;
    }

    public Serializable getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public String getMediaTipo() {
        return mediaTipo;
    }

    public void setMediaTipo(String mediaTipo) {
        this.mediaTipo = mediaTipo;
    }

    public long getMediaVersion() {
        return mediaVersion;
    }

    public void setMediaVersion(long mediaVersion) {
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
        hash += (mediaId != 0 ? Long.hashCode(mediaId) : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof VariablesMultimedia)) {
            return false;
        }
        VariablesMultimedia other = (VariablesMultimedia) object;
        return this.mediaId == other.mediaId;
    }

    @Override
    public String toString() {
        return "cr.ac.una.chatandmailapi.model.VariablesMultimedia[ mediaId=" + mediaId + " ]";
    }
}
