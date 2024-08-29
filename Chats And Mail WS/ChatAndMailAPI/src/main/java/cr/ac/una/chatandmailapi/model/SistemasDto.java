package cr.ac.una.chatandmailapi.model;

import java.io.Serializable;

/**
 * DTO for Sistemas entity
 * 
 * @author Kendall Fonseca
 */
public class SistemasDto implements Serializable {

    private Long sisId;
    private String sisNombre;
    private Long sisVersion;

    public SistemasDto() {
    }

    public SistemasDto(Long sisId, String sisNombre, Long sisVersion) {
        this.sisId = sisId;
        this.sisNombre = sisNombre;
        this.sisVersion = sisVersion;
    }

    public Long getSisId() {
        return sisId;
    }

    public void setSisId(Long sisId) {
        this.sisId = sisId;
    }

    public String getSisNombre() {
        return sisNombre;
    }

    public void setSisNombre(String sisNombre) {
        this.sisNombre = sisNombre;
    }

    public Long getSisVersion() {
        return sisVersion;
    }

    public void setSisVersion(Long sisVersion) {
        this.sisVersion = sisVersion;
    }

    @Override
    public String toString() {
        return "SistemasDto{" +
                "sisId=" + sisId +
                ", sisNombre='" + sisNombre + '\'' +
                ", sisVersion=" + sisVersion +
                '}';
    }
}
