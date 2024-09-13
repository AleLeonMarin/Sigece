package cr.ac.una.chatandmailapi.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.json.bind.annotation.JsonbPropertyOrder;

import java.io.Serializable;

/**
 * DTO para la entidad VariablesMultimedia.
 */
@JsonbPropertyOrder({
        "mediaId", "mediaUrl", "mediaTipo", "mediaVersion", "mediaVarId"
})
@Schema(description = "Esta clase contiene la información de las variables multimedia")
public class VariablesMultimediaDTO implements Serializable {

    @Schema(description = "Identificador de la variable multimedia", example = "1")
    private long mediaId;
    @Schema(description = "URL de la variable multimedia", example = "https://www.google/img32.com")
    private Serializable mediaUrl;
    @Schema(description = "Tipo de la variable multimedia", example = "imagen")
    private String mediaTipo;
    @Schema(description = "Versión de la variable multimedia", example = "1")
    private long mediaVersion;
    @Schema(description = "Variable de la variable multimedia")
    private VariablesDTO mediaVarId;

    public VariablesMultimediaDTO() {}

    public VariablesMultimediaDTO(VariablesMultimedia variablesMultimedia) {
        this.mediaId = variablesMultimedia.getMediaId();
        this.mediaUrl = variablesMultimedia.getMediaUrl();
        this.mediaTipo = variablesMultimedia.getMediaTipo();
        this.mediaVersion = variablesMultimedia.getMediaVersion();
        if (variablesMultimedia.getMediaVarId() != null) {
            this.mediaVarId = new VariablesDTO(variablesMultimedia.getMediaVarId());
        }
    }

    // Getters y Setters
    public long getMediaId() {
        return mediaId;
    }

    public void setMediaId(long mediaId) {
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
        int hash = 7;
        hash = 31 * hash + Long.hashCode(mediaId);
        return hash;
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
        return this.mediaId == other.mediaId;
    }

    @Override
    public String toString() {
        return "VariablesMultimediaDTO{" +
                "mediaId=" + mediaId +
                ", mediaUrl=" + mediaUrl +
                ", mediaTipo='" + mediaTipo + '\'' +
                ", mediaVersion=" + mediaVersion +
                ", mediaVarId=" + mediaVarId +
                '}';
    }
}
