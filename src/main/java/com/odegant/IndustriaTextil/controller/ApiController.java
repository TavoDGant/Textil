package com.odegant.IndustriaTextil.controller;

import com.odegant.IndustriaTextil.entity.Cortes;
import com.odegant.IndustriaTextil.entity.Empleado;
import com.odegant.IndustriaTextil.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("v1/api/admin/empleado")
public class ApiController {

    @Autowired
    private AdminService adminService;

    @GetMapping("")
    public ResponseEntity<List<Empleado>> empleados(){
        return adminService.listaEmpleados();
    }

    @PostMapping("nuevo")
    public ResponseEntity<HttpStatus> nuevoEmpleado(@RequestBody Empleado empleado){
        return adminService.guardarEmpleado(empleado);
    }

    @GetMapping("{id}")
    public ResponseEntity<ArrayList<Empleado>> buscarEmpleado(@PathVariable Integer id){
        return adminService.empleadoID(id);
    }

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<HttpStatus> borrarEmpleado(@PathVariable("id") Integer id){
        return adminService.eliminarEmpleado(id);
    }

    @PutMapping("actualizar")
    public ResponseEntity<HttpStatus> actualizarEmpleado(@RequestBody Empleado empleado){
        return adminService.actualizarEmpleado(empleado);
    }

    @GetMapping("mensuales")
    public Map<Integer, List> mensulaes(){
        return adminService.cortesMensuales();
    }

    //-----------------------------CORTES--------------------------------------------------------
    @GetMapping("corte/{fkec}")
    public ResponseEntity<List<Cortes>> corteFkec(@PathVariable Integer fkec){
        return adminService.cortesFkec(fkec);
    }

    @GetMapping("corte/id/{id}")
    public Optional<Cortes> corteID(@PathVariable Integer id) {return adminService.cortesID(id);}

    @PostMapping("corte/nuevo")
    public ResponseEntity<HttpStatus> guardarCorte(@RequestBody Cortes cortes){
        return adminService.guardarCorte(cortes);
    }

    @DeleteMapping("corte/eliminar/{id}")
    public ResponseEntity<HttpStatus> eliminarCorte(@PathVariable("id") Integer id){
        return adminService.eliminarCorte(id);
    }

    @PutMapping("corte/actualizar")
    public ResponseEntity<HttpStatus> actualizarCorte(@RequestBody Cortes cortes){
        return adminService.actualizarCorte(cortes);
    }
}
