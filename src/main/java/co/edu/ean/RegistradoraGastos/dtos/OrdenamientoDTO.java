package co.edu.ean.RegistradoraGastos.dtos;

import co.edu.ean.RegistradoraGastos.enums.TipoOrdenamientoEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.math.BigDecimal;

@JsonPropertyOrder({"tipoOrd", "estrategiaOrd"})
public class OrdenamientoDTO {

    @JsonProperty(value = "Tipo_Ordenamiento", required = true)
    private TipoOrdenamientoEnum tipoOrd;

    @JsonProperty(value = "Estrateg_Ordenamiento", required = true)
    private BigDecimal estrategiaOrd;

    public TipoOrdenamientoEnum getTipoOrd() {
        return tipoOrd;
    }

    public void setTipoOrd(TipoOrdenamientoEnum tipoOrd) {
        this.tipoOrd = tipoOrd;
    }

    public BigDecimal getEstrategiaOrd() {
        return estrategiaOrd;
    }

    public void setEstrategiaOrd(BigDecimal estrategiaOrd) {
        this.estrategiaOrd = estrategiaOrd;
    }
}
