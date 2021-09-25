package co.edu.ean.RegistradoraGastos.controllers;

import co.edu.ean.RegistradoraGastos.daos.TblRegistrosDAO;
import co.edu.ean.RegistradoraGastos.daos.TblRegistrosDAOImpl;
import co.edu.ean.RegistradoraGastos.dtos.OrdenamientoDTO;
import co.edu.ean.RegistradoraGastos.dtos.TblRegistrosDTO;
import co.edu.ean.RegistradoraGastos.entidades.TblRegistrosEntity;
import co.edu.ean.RegistradoraGastos.enums.TipoOrdenamientoEnum;
import co.edu.ean.RegistradoraGastos.logicanegocio.CalculoDineroNeto;
import co.edu.ean.RegistradoraGastos.logicanegocio.OrdenamientoRegistros;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// http://localhost:8089/RegistradoraGastos/Crear

@RestController
@RequestMapping("/RegistradoraGastos")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods= {RequestMethod.GET,
        RequestMethod.HEAD, RequestMethod.PATCH, RequestMethod.POST, RequestMethod.PUT})

public class RegistradoraController {

    TblRegistrosDAO tblRegistrosDAO = new TblRegistrosDAOImpl();
    @Autowired
    CalculoDineroNeto calculoDineroNeto;
    @Autowired
    OrdenamientoRegistros ordenamientoRegistros;

    private ModelMapper modelMapper = new ModelMapper();


    @PostMapping(value = "/Crear")
    public String crearRegistro(@RequestBody @Valid TblRegistrosDTO input) {
        TblRegistrosEntity tblRegistrosEntity = modelMapper.map(input, TblRegistrosEntity.class);
        tblRegistrosDAO.crear(tblRegistrosEntity);
        return "Registro Creado";
    }

    @Transactional
    @GetMapping(value = "/BuscarTodos")
    public List<TblRegistrosDTO> buscarTodos() {
        List<TblRegistrosEntity> listado = tblRegistrosDAO.buscarTodos();
        List<TblRegistrosDTO> listado2 = new ArrayList<>();
        for (TblRegistrosEntity registro : listado) {
            listado2.add(modelMapper.map(registro, TblRegistrosDTO.class));
        }
        System.out.println(listado);
        System.out.println(listado2);
        return listado2;
    }

    @PutMapping(value = "/Actualizar")
    public String actualizarRegistro(@RequestBody @Valid TblRegistrosDTO input) {
        TblRegistrosEntity TblRegistrosEntity = modelMapper.map(input, TblRegistrosEntity.class);
        tblRegistrosDAO.actualizarRegistro(TblRegistrosEntity);
        return "Registro Actualizado";
    }

    @DeleteMapping(value = "/Eliminar")
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
        ordenamientoRegistros.ordenar(listado, infoOrd);
        return listado;
    }

    @GetMapping(value = "/BorrarTabla")
    public String borrarTotalRegistros() {
        tblRegistrosDAO.borrarTabla();
        return "Se han borrado todos los registros";
    }
}
