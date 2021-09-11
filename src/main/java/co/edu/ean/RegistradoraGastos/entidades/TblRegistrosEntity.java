package co.edu.ean.RegistradoraGastos.entidades;


import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@NamedQueries( {
    @NamedQuery(name = "TblRegistrosEntity.findAll", query="select o from TblRegistrosEntity o"),
})
@Table(name = "TBL_REGISTROS")
public class TblRegistrosEntity implements Serializable {

    @Id
    @Column(name = "ID_REGISTRO", nullable = false)
//    @JsonProperty(value = "Id_Registro", required = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQ")
    @SequenceGenerator(name = "ID_SEQ", sequenceName = "ID_SEQ", allocationSize = 1)
    private BigDecimal idRegistro;

    @Column(name = "TIPO_REGISTRO", nullable = false, length = 1)
//    @JsonProperty(value = "Tipo_Registro", required = true)
    private String tipoRegistro;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_REGISTRO", nullable = false)
//    @JsonProperty(value = "Fecha_Registro", required = true)
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private Date fechaRegistro;

    @Column(name = "MONTO_REGISTRO", nullable = false, length = 20)
//    @JsonProperty(value = "Monto_Registro", required = true)
    private String montoRegistro;

    @Column(name = "DESCRIP_REGISTRO", length = 250)
//    @JsonProperty(value = "Descripcion_Registro", required = false)
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

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getMontoRegistro() {
        return montoRegistro;
    }

    public void setMontoRegistro(String montoRegistro) {
        this.montoRegistro = montoRegistro;
    }

    public String getDescripcionRegistro() {
        return descripcionRegistro;
    }

    public void setDescripcionRegistro(String descripcionRegistro) {
        this.descripcionRegistro = descripcionRegistro;
    }

    @Override
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
