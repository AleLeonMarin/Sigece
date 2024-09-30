package cr.ac.una.chatandmailapi.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.json.bind.annotation.JsonbAnnotation;
import jakarta.json.bind.annotation.JsonbPropertyOrder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@JsonbPropertyOrder({
        "notId", "notNombre", "notPlantilla", "notVersion", "sisVariablesList", "sisCorreosList"
})

@Schema(description = "Esta clase contiene la información de una notificación")
public class NotificacionDTO implements Serializable {

    private Long notId;
    private String notNombre;
    private String notPlantilla;
    private Long notVersion;
    private List<VariablesDTO> sisVariablesList;
    private List<CorreosDTO> sisCorreosList;

    public NotificacionDTO() {}

    public NotificacionDTO(Notificacion notificacion) {
    this.notId = notificacion.getNotId();
    this.notNombre = notificacion.getNotNombre();
    this.notPlantilla = notificacion.getNotPlantilla();
    this.notVersion = notificacion.getNotVersion();

    if (notificacion.getSisVariablesList() != null) {
        this.sisVariablesList = new ArrayList<>();
        for (Variables var : notificacion.getSisVariablesList()) {
            this.sisVariablesList.add(new VariablesDTO(var));
        }
    }
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
        int hash = 7;
        hash = 31 * hash + (notId != null ? notId.hashCode() : 0);
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
        NotificacionDTO other = (NotificacionDTO) obj;
        return (this.notId != null && this.notId.equals(other.notId));
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
