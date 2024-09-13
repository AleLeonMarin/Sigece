package cr.ac.una.chatandmailapi.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.json.bind.annotation.JsonbPropertyOrder;

import java.io.Serializable;

/**
 * DTO para la entidad Variables.
 */
@JsonbPropertyOrder({
        "varId", "varNombre", "tipo", "varValor", "varVersion", "varNotId"
})
@Schema(description = "Esta clase contiene la información de las variables")
public class VariablesDTO implements Serializable {

    @Schema(description = "Identificador de la variable", example = "1")
    private long varId;
    @Schema(description = "Nombre de la variable", example = "nombre")
    private String varNombre;
    @Schema(description = "Tipo de la variable", example = "String")
    private String tipo;
    @Schema(description = "Valor de la variable", example = "valor")
    private String varValor;
    @Schema(description = "Versión de la variable", example = "1")
    private long varVersion;
    @Schema(description = "Notificación de la variable")
    private NotificacionDTO varNotId;

    public VariablesDTO() {}

    public VariablesDTO(Variables variables) {
        this.varId = variables.getVarId();
        this.varNombre = variables.getVarNombre();
        this.tipo = variables.getTipo();
        this.varValor = variables.getVarValor();
        this.varVersion = variables.getVarVersion();
        if (variables.getVarNotId() != null) {
            this.varNotId = new NotificacionDTO(variables.getVarNotId());
        }
    }

    // Getters y Setters
    public long getVarId() {
        return varId;
    }

    public void setVarId(long varId) {
        this.varId = varId;
    }

    public String getVarNombre() {
        return varNombre;
    }

    public void setVarNombre(String varNombre) {
        this.varNombre = varNombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getVarValor() {
        return varValor;
    }

    public void setVarValor(String varValor) {
        this.varValor = varValor;
    }

    public long getVarVersion() {
        return varVersion;
    }

    public void setVarVersion(long varVersion) {
        this.varVersion = varVersion;
    }

    public NotificacionDTO getVarNotId() {
        return varNotId;
    }

    public void setVarNotId(NotificacionDTO varNotId) {
        this.varNotId = varNotId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Long.hashCode(varId);
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
        VariablesDTO other = (VariablesDTO) obj;
        return this.varId == other.varId;
    }

    @Override
    public String toString() {
        return "VariablesDTO{" +
                "varId=" + varId +
                ", varNombre='" + varNombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", varValor='" + varValor + '\'' +
                ", varVersion=" + varVersion +
                ", varNotId=" + varNotId +
                '}';
    }
}
