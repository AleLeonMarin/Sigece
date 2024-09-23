package cr.ac.una.mailapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NotificacionDTO implements Serializable {
    private Long notId;
    private String notNombre;
    private String notPlantilla;
    private Long notVersion;
    private List<VariablesDTO> sisVariablesList;
    private List<CorreosDTO> sisCorreosList;

    public NotificacionDTO() {
        this.notId = 0L;
        this.notNombre = "";
        this.notPlantilla = "";
        this.notVersion = 0L;
        this.sisVariablesList = new ArrayList<>();
        this.sisCorreosList = new ArrayList<>();
    }


    // Getters y Setters
    public Long getNotId() {
        if (this.notId!=null&& !this.notId.equals(0L)) {
            return notId;
        }else{
                return null;
            }
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

    public List<VariablesDTO> getSisVariablesList() {
        return sisVariablesList;
    }

    public void setSisVariablesList(List<VariablesDTO> sisVariablesList) {
        this.sisVariablesList = sisVariablesList;
    }

    public List<CorreosDTO> getSisCorreosList() {
        return sisCorreosList;
    }

    public void setSisCorreosList(List<CorreosDTO> sisCorreosList) {
        this.sisCorreosList = sisCorreosList;
    }

    @Override
    public int hashCode() {
        return notId != null ? notId.hashCode() : 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        NotificacionDTO other = (NotificacionDTO) obj;
        return notId != null ? notId.equals(other.notId) : other.notId == null;
    }

    @Override
    public String toString() {
        return "NotificacionDTO{" +
                "notId=" + notId +
                ", notNombre='" + notNombre + '\'' +
                ", notPlantilla='" + notPlantilla + '\'' +
                ", notVersion=" + notVersion +
                ", sisVariablesList=" + sisVariablesList +
                ", sisCorreosList=" + sisCorreosList +
                '}';
    }
}

