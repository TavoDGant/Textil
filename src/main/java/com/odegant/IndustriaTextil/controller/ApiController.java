package com.odegant.IndustriaTextil.controller;

import com.odegant.IndustriaTextil.entity.Cortes;
import com.odegant.IndustriaTextil.entity.Empleado;
import com.odegant.IndustriaTextil.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public ResponseEntity<Optional<Empleado>> buscarEmpleado(@PathVariable Integer id){
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

    //-----------------------------CORTES--------------------------------------------------------
    @PostMapping("corte/nuevo")
    public ResponseEntity<HttpStatus> guardarCorte(@RequestBody Cortes cortes){
        return adminService.guardarCorte(cortes);
    }

    @PutMapping("corte/actualizar")
    public ResponseEntity<HttpStatus> actualizarCorte(Cortes cortes){
        return adminService.actualizarCorte(cortes);
    }

}
