package cr.ac.una.mailapp.model;

import java.io.Serializable;

public class VariablesMultimediaDTO implements Serializable {
    private long mediaId;
    private String mediaUrl;
    private String mediaTipo;
    private long mediaVersion;
    private VariablesDTO mediaVarId;

    public VariablesMultimediaDTO() {}

    public VariablesMultimediaDTO(long mediaId, String mediaUrl, String mediaTipo, long mediaVersion, VariablesDTO mediaVarId) {
        this.mediaId = mediaId;
        this.mediaUrl = mediaUrl;
        this.mediaTipo = mediaTipo;
        this.mediaVersion = mediaVersion;
        this.mediaVarId = mediaVarId;
    }

    // Getters y Setters
    public long getMediaId() {
        return mediaId;
    }

    public void setMediaId(long mediaId) {
        this.mediaId = mediaId;
    }

    public String getMediaUrl() {
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

    public VariablesDTO getMediaVarId() {
        return mediaVarId;
    }

    public void setMediaVarId(VariablesDTO mediaVarId) {
        this.mediaVarId = mediaVarId;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(mediaId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        VariablesMultimediaDTO other = (VariablesMultimediaDTO) obj;
        return mediaId == other.mediaId;
    }

    @Override
    public String toString() {
        return "VariablesMultimediaDTO{" +
                "mediaId=" + mediaId +
                ", mediaUrl='" + mediaUrl + '\'' +
                ", mediaTipo='" + mediaTipo + '\'' +
                ", mediaVersion=" + mediaVersion +
                ", mediaVarId=" + mediaVarId +
                '}';
    }
}

