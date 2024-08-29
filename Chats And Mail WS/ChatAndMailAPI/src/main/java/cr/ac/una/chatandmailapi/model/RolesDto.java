package cr.ac.una.chatandmailapi.model;

import java.io.Serializable;

/**
 * DTO for Roles entity
 * 
 * @author Kendall Fonseca
 */
public class RolesDto implements Serializable {

    private Long rolId;
    private String rolNombre;
    private Long rolSisId;
    private Long rolVersion;

    public RolesDto() {
    }

    public RolesDto(Long rolId, String rolNombre, Long rolSisId, Long rolVersion) {
        this.rolId = rolId;
        this.rolNombre = rolNombre;
        this.rolSisId = rolSisId;
        this.rolVersion = rolVersion;
    }

    public Long getRolId() {
        return rolId;
    }

    public void setRolId(Long rolId) {
        this.rolId = rolId;
    }

    public String getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(String rolNombre) {
        this.rolNombre = rolNombre;
    }

    public Long getRolSisId() {
        return rolSisId;
    }

    public void setRolSisId(Long rolSisId) {
        this.rolSisId = rolSisId;
    }

    public Long getRolVersion() {
        return rolVersion;
    }

    public void setRolVersion(Long rolVersion) {
        this.rolVersion = rolVersion;
    }

    @Override
    public String toString() {
        return "RolesDto{" +
                "rolId=" + rolId +
                ", rolNombre='" + rolNombre + '\'' +
                ", rolSisId=" + rolSisId +
                ", rolVersion=" + rolVersion +
                '}';
    }
}
