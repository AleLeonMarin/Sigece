package cr.ac.una.chatandmailapi.model;

import java.io.Serializable;

public class VariablesMultimediaDto implements Serializable {

    private Long mediaId;
    private String mediaUrl;
    private String mediaTipo;
    private Long mediaVarId;
    private Long mediaVersion;

    public VariablesMultimediaDto() {
    }

    public VariablesMultimediaDto(Long mediaId, String mediaUrl, String mediaTipo, Long mediaVarId, Long mediaVersion) {
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
    public String toString() {
        return "VariablesMultimediaDto{" +
                "mediaId=" + mediaId +
                ", mediaUrl='" + mediaUrl + '\'' +
                ", mediaTipo='" + mediaTipo + '\'' +
                ", mediaVarId=" + mediaVarId +
                ", mediaVersion=" + mediaVersion +
                '}';
    }
}
