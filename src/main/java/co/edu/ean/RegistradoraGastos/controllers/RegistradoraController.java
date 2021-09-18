package co.edu.ean.RegistradoraGastos.controllers;

import co.edu.ean.RegistradoraGastos.daos.TblRegistrosDAO;
import co.edu.ean.RegistradoraGastos.daos.TblRegistrosDAOImpl;
import co.edu.ean.RegistradoraGastos.dtos.TblRegistrosDTO;
import co.edu.ean.RegistradoraGastos.entidades.TblRegistrosEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

// http://localhost:8089/RegistradoraGastos/Crear

@RestController
@RequestMapping("/RegistradoraGastos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RegistradoraController {

    TblRegistrosDAO tblRegistrosDAO = new TblRegistrosDAOImpl();

    private ModelMapper modelMapper = new ModelMapper();


    @PostMapping(value = "/Crear")
    public String crearRegistro(@RequestBody @Valid TblRegistrosDTO input) {
        TblRegistrosEntity TblRegistrosEntity = modelMapper.map(input, TblRegistrosEntity.class);
        tblRegistrosDAO.crear(TblRegistrosEntity);
        return "Registro Creado";
    }

    @GetMapping(value = "/BuscarTodos")
    public List<TblRegistrosDTO> buscarTodos() {
        List<TblRegistrosEntity> listado = tblRegistrosDAO.buscarTodos();
        List<TblRegistrosDTO> listado2 = new ArrayList<>();
        for (TblRegistrosEntity registro: listado) {
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
}
