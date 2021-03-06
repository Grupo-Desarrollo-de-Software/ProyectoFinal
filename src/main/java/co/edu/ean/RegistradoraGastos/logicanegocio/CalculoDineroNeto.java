package co.edu.ean.RegistradoraGastos.logicanegocio;

import co.edu.ean.RegistradoraGastos.entidades.TblRegistrosEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CalculoDineroNeto {

    public int calcularNetos(List<TblRegistrosEntity> listado){
        int netos = 0;
        for (TblRegistrosEntity registro: listado) {
            if (registro.getTipoRegistro().equals("+")){
                netos = netos + Integer.valueOf(registro.getMontoRegistro());
            } else if(registro.getTipoRegistro().equals("-")){
                netos = netos - Integer.valueOf(registro.getMontoRegistro());
            }
        }
        return netos;
    }
}
