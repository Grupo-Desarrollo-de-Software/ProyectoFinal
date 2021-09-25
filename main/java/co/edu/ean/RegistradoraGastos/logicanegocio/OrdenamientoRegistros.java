package co.edu.ean.RegistradoraGastos.logicanegocio;

import co.edu.ean.RegistradoraGastos.dtos.OrdenamientoDTO;
import co.edu.ean.RegistradoraGastos.entidades.TblRegistrosEntity;
import co.edu.ean.RegistradoraGastos.enums.TipoOrdenamientoEnum;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class OrdenamientoRegistros {

    public void ordenar(List<TblRegistrosEntity> listado, OrdenamientoDTO infoOrd) {
        if (infoOrd.getTipoOrd().equals(TipoOrdenamientoEnum.Monto)) {
            Comparator<TblRegistrosEntity> compareByValue = (TblRegistrosEntity o1, TblRegistrosEntity o2) ->
                    o1.getMontoRegistro().compareTo(o2.getMontoRegistro());
            if (infoOrd.getEstrategiaOrd().intValue() == 0) {
                Collections.sort(listado, compareByValue);
            } else {
                Collections.sort(listado, compareByValue.reversed());
            }
        } else if (infoOrd.getTipoOrd().equals(TipoOrdenamientoEnum.Id)) {
            if (infoOrd.getEstrategiaOrd().intValue() == 0) {
                Collections.sort(listado);
            } else {
                Collections.sort(listado, Collections.reverseOrder());
            }
        } else if (infoOrd.getTipoOrd().equals(TipoOrdenamientoEnum.Fecha)) {
            Comparator<TblRegistrosEntity> compareByDate = (TblRegistrosEntity o1, TblRegistrosEntity o2) ->
                    o1.getFechaRegistro().compareTo(o2.getFechaRegistro());
            if (infoOrd.getEstrategiaOrd().intValue() == 0) {
                Collections.sort(listado, compareByDate);
            } else {
                Collections.sort(listado, compareByDate.reversed());
            }
        }
    }
}
