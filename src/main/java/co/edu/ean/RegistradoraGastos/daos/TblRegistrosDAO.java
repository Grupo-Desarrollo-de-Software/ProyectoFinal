package co.edu.ean.RegistradoraGastos.daos;

import co.edu.ean.RegistradoraGastos.entidades.TblRegistrosEntity;

import java.math.BigDecimal;
import java.util.List;

public interface TblRegistrosDAO {

    TblRegistrosEntity crear(TblRegistrosEntity tblRegistrosEntity);
    List<TblRegistrosEntity> buscarTodos();
    void actualizarRegistro(TblRegistrosEntity registro);
    void borrarRegistro(BigDecimal registro);
    void borrarTabla();

}
