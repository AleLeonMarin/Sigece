package cr.ac.una.chatandmailapi.model;

import java.io.Serializable;

/**
 * DTO para la entidad VariablesCondicionales.
 */
public class VariablesCondicionalesDTO implements Serializable {

    private long vconId;
    private String vcondValor;
    private String vcondResultado;
    private long vconVersion;
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
