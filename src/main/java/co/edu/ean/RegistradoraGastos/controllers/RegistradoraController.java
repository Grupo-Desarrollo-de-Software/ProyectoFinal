package co.edu.ean.RegistradoraGastos.controllers;

import co.edu.ean.RegistradoraGastos.daos.TblRegistrosDAO;
import co.edu.ean.RegistradoraGastos.daos.TblRegistrosDAOImpl;
import co.edu.ean.RegistradoraGastos.dtos.OrdenamientoDTO;
import co.edu.ean.RegistradoraGastos.dtos.TblRegistrosDTO;
import co.edu.ean.RegistradoraGastos.entidades.TblRegistrosEntity;
import co.edu.ean.RegistradoraGastos.enums.TipoOrdenamientoEnum;
import co.edu.ean.RegistradoraGastos.logicanegocio.CalculoDineroNeto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// http://localhost:8089/RegistradoraGastos/Crear

@RestController
@RequestMapping("/RegistradoraGastos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RegistradoraController {

    TblRegistrosDAO tblRegistrosDAO = new TblRegistrosDAOImpl();

    CalculoDineroNeto calculoDineroNeto = new CalculoDineroNeto();

    private ModelMapper modelMapper = new ModelMapper();


    @PostMapping(value = "/Crear")
    public String crearRegistro(@RequestBody @Valid TblRegistrosDTO input) {
        TblRegistrosEntity tblRegistrosEntity = modelMapper.map(input, TblRegistrosEntity.class);
        tblRegistrosDAO.crear(tblRegistrosEntity);
        return "Registro Creado";
    }

    @GetMapping(value = "/BuscarTodos")
    public List<TblRegistrosDTO> buscarTodos() {
        List<TblRegistrosEntity> listado = tblRegistrosDAO.buscarTodos();
        List<TblRegistrosDTO> listado2 = new ArrayList<>();
        for (TblRegistrosEntity registro : listado) {
            listado2.add(modelMapper.map(registro, TblRegistrosDTO.class));
        }
        return listado2;
    }

    @PostMapping(value = "/Actualizar")
    public String actualizarRegistro(@RequestBody @Valid TblRegistrosDTO input) {
        TblRegistrosEntity TblRegistrosEntity = modelMapper.map(input, TblRegistrosEntity.class);
        tblRegistrosDAO.actualizarRegistro(TblRegistrosEntity);
        return "Registro Actualizado";
    }

    @PostMapping(value = "/Eliminar")
    public String borrarRegistro(@RequestBody @Valid TblRegistrosDTO input) {
        tblRegistrosDAO.borrarRegistro(input.getIdRegistro());
        return "Registro Eliminado";
    }

    @GetMapping(value = "/Neto")
    public String netoRegistro() {
        int resultado = calculoDineroNeto.calcularNetos(tblRegistrosDAO.buscarTodos());
        return "El usuario tiene un monto neto de $" + resultado;
    }

    @PostMapping(value = "/Ordenar")
    public List<TblRegistrosEntity> ordenarRegistros(@RequestBody @Valid OrdenamientoDTO infoOrd) {
        List<TblRegistrosEntity> listado = tblRegistrosDAO.buscarTodos();
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
        } else if (infoOrd.getTipoOrd().equals(TipoOrdenamientoEnum.Fecha)){
            Comparator<TblRegistrosEntity> compareByDate = (TblRegistrosEntity o1, TblRegistrosEntity o2) ->
                    o1.getFechaRegistro().compareTo(o2.getFechaRegistro());
            if (infoOrd.getEstrategiaOrd().intValue() == 0) {
                Collections.sort(listado, compareByDate);
            } else {
                Collections.sort(listado, compareByDate.reversed());
            }
        }
        return listado;
    }
}
