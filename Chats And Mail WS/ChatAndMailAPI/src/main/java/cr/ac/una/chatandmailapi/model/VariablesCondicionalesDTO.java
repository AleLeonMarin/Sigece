package cr.ac.una.chatandmailapi.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.json.bind.annotation.JsonbPropertyOrder;

import java.io.Serializable;

@JsonbPropertyOrder({
        "vconId", "vcondValor", "vcondResultado", "vconVersion", "vconVarId"
})
@Schema(description = "Esta clase contiene la información de las variables condicionales")
public class VariablesCondicionalesDTO implements Serializable {

    @Schema(description = "Identificador de la variable condicional", example = "1")
    private long vconId;
    @Schema(description = "Valor de la variable condicional", example = "1")
    private String vcondValor;
    @Schema(description = "Resultado de la variable condicional", example = "Aceptado")
    private String vcondResultado;
    @Schema(description = "Versión de la variable condicional", example = "1")
    private long vconVersion;
    @Schema(description = "Variable de la variable condicional")
    private VariablesDTO vconVarId;

    public VariablesCondicionalesDTO() {}

    public VariablesCondicionalesDTO(VariablesCondicionales variablesCondicionales) {
        this.vconId = variablesCondicionales.getVconId();
        this.vcondValor = variablesCondicionales.getVcondValor();
        this.vcondResultado = variablesCondicionales.getVcondResultado();
        this.vconVersion = variablesCondicionales.getVconVersion();
        if (variablesCondicionales.getVconVarId() != null) {
            this.vconVarId = new VariablesDTO(variablesCondicionales.getVconVarId());
        }
    }

    // Getters y Setters
    public long getVconId() {
        return vconId;
    }

    public void setVconId(long vconId) {
        this.vconId = vconId;
    }

    public String getVcondValor() {
        return vcondValor;
    }

    public void setVcondValor(String vcondValor) {
        this.vcondValor = vcondValor;
    }

    public String getVcondResultado() {
        return vcondResultado;
    }

    public void setVcondResultado(String vcondResultado) {
        this.vcondResultado = vcondResultado;
    }

    public long getVconVersion() {
        return vconVersion;
    }

    public void setVconVersion(long vconVersion) {
        this.vconVersion = vconVersion;
    }

    public VariablesDTO getVconVarId() {
        return vconVarId;
    }

    public void setVconVarId(VariablesDTO vconVarId) {
        this.vconVarId = vconVarId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Long.hashCode(vconId);
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
        VariablesCondicionalesDTO other = (VariablesCondicionalesDTO) obj;
        return this.vconId == other.vconId;
    }

    @Override
    public String toString() {
        return "VariablesCondicionalesDTO{" +
                "vconId=" + vconId +
                ", vcondValor='" + vcondValor + '\'' +
                ", vcondResultado='" + vcondResultado + '\'' +
                ", vconVersion=" + vconVersion +
                ", vconVarId=" + vconVarId +
                '}';
    }
}
