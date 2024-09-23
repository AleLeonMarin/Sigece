package cr.ac.una.mailapp.model;

import java.io.Serializable;

public class VariablesCondicionalesDTO implements Serializable {
    private long vconId;
    private String vcondValor;
    private String vcondResultado;
    private long vconVersion;
    private VariablesDTO vconVarId;

    public VariablesCondicionalesDTO() {}

    public VariablesCondicionalesDTO(long vconId, String vcondValor, String vcondResultado, long vconVersion, VariablesDTO vconVarId) {
        this.vconId = vconId;
        this.vcondValor = vcondValor;
        this.vcondResultado = vcondResultado;
        this.vconVersion = vconVersion;
        this.vconVarId = vconVarId;
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
        return Long.hashCode(vconId);
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
        return vconId == other.vconId;
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
