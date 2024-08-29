package cr.ac.una.chatandmailapi.model;

import java.io.Serializable;

/**
 * DTO for Notificacion entity
 * 
 * @author Kendall Fonseca
 */
public class NotificacionDto implements Serializable {

    private Long notId;
    private String notNombre;
    private String notPlantilla;
    private Long notVersion;

    public NotificacionDto() {
    }

    public NotificacionDto(Long notId, String notNombre, String notPlantilla, Long notVersion) {
        this.notId = notId;
        this.notNombre = notNombre;
        this.notPlantilla = notPlantilla;
        this.notVersion = notVersion;
    }

    public Long getNotId() {
        return notId;
    }

    public void setNotId(Long notId) {
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

    public Long getNotVersion() {
        return notVersion;
    }

    public void setNotVersion(Long notVersion) {
        this.notVersion = notVersion;
    }

    @Override
    public String toString() {
        return "NotificacionDto{" +
                "notId=" + notId +
                ", notNombre='" + notNombre + '\'' +
                ", notPlantilla='" + notPlantilla + '\'' +
                ", notVersion=" + notVersion +
                '}';
    }
}

