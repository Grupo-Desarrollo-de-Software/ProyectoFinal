package co.edu.ean.RegistradoraGastos.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.math.BigDecimal;

@JsonPropertyOrder({"idRegistro", "tipoRegistro", "fechaRegistro", "montoRegistro", "descripcionRegistro"})
public class TblRegistrosDTO {

    @JsonProperty(value = "Id_Registro", required = false)
    private BigDecimal idRegistro;

    @JsonProperty(value = "Tipo_Registro", required = true)
    private String tipoRegistro;

    @JsonProperty(value = "Fecha_Registro", required = true)
    private String fechaRegistro;

    @JsonProperty(value = "Monto_Registro", required = true)
    private BigDecimal montoRegistro;

    @JsonProperty(value = "Descripcion_Registro", required = false)
    private String descripcionRegistro;

    public BigDecimal getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(BigDecimal idRegistro) {
        this.idRegistro = idRegistro;
    }

    public String getTipoRegistro() {
        return tipoRegistro;
    }

    public void setTipoRegistro(String tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public BigDecimal getMontoRegistro() {
        return montoRegistro;
    }

    public void setMontoRegistro(BigDecimal montoRegistro) {
        this.montoRegistro = montoRegistro;
    }

    public String getDescripcionRegistro() {
        return descripcionRegistro;
    }

    public void setDescripcionRegistro(String descripcionRegistro) {
        this.descripcionRegistro = descripcionRegistro;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getClass().getName() + "@" + Integer.toHexString(hashCode()));
        buffer.append('[');
        buffer.append("Id_Registro= ");
        buffer.append(getIdRegistro());
        buffer.append(", ");
        buffer.append("Tipo_Registro= ");
        buffer.append(getTipoRegistro());
        buffer.append(", ");
        buffer.append("Fecha_Registro= ");
        buffer.append(getFechaRegistro());
        buffer.append(", ");
        buffer.append("Monto_Registro= ");
        buffer.append(getMontoRegistro());
        buffer.append(", ");
        buffer.append("Descripcion_Registro= ");
        buffer.append(getDescripcionRegistro());
        buffer.append(']');
        return buffer.toString();
    }
}
