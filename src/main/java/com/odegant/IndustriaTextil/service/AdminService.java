package com.odegant.IndustriaTextil.service;

import com.odegant.IndustriaTextil.entity.Cortes;
import com.odegant.IndustriaTextil.entity.Empleado;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface AdminService {

    ResponseEntity<List<Empleado>> listaEmpleados();

    ResponseEntity<HttpStatus> guardarEmpleado(Empleado empleado);

    ResponseEntity<Optional<Empleado>> empleadoID(Integer id);

    ResponseEntity<HttpStatus> eliminarEmpleado(Integer id);

    ResponseEntity<HttpStatus> actualizarEmpleado(Empleado empleado);

    //-----------------CORTES---------------------------------------------
    ResponseEntity<HttpStatus> guardarCorte(Cortes cortes);

    ResponseEntity<HttpStatus> actualizarCorte(Cortes cortes);
}
