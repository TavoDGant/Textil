package com.odegant.IndustriaTextil.service;

import com.odegant.IndustriaTextil.dao.CorteDAO;
import com.odegant.IndustriaTextil.dao.EmpleadoDAO;
import com.odegant.IndustriaTextil.entity.Cortes;
import com.odegant.IndustriaTextil.entity.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private EmpleadoDAO empleadoDAO;

    @Autowired
    private CorteDAO corteDAO;

    public ResponseEntity<List<Empleado>> listaEmpleados(){
        List<Empleado> empleadoList = empleadoDAO.findAll();
        if(empleadoList.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(empleadoList);
        }
    }

    public ResponseEntity<HttpStatus> guardarEmpleado(Empleado empleado){
        try {
            for (Cortes c:empleado.getCortes()) {
                int cort = c.getCortes();
                Double sueldo = cort * 0.25;
                c.setSueldo(sueldo);
            }
            empleadoDAO.save(empleado);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<Optional<Empleado>> empleadoID(Integer id){
        try {
            return ResponseEntity.ok(empleadoDAO.findById(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<HttpStatus> eliminarEmpleado(Integer id){
        try {
            empleadoDAO.deleteById(id);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<HttpStatus> actualizarEmpleado(Empleado empleado){
        try {
            empleadoDAO.save(empleado);
            return ResponseEntity.ok().build();

        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    //-----------------CORTES---------------------------------------------
    public ResponseEntity<HttpStatus> guardarCorte(Cortes cortes){
        try {
            int cor = cortes.getCortes();
            Double sueldo = cor * 0.25;
            cortes.setSueldo(sueldo);
            corteDAO.save(cortes);
            return ResponseEntity.ok().build();

        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<HttpStatus> actualizarCorte(Cortes cortes){
        try {
            Cortes c = new Cortes();
            c.setId_cortes(cortes.getId_cortes());
            c.setCortes(cortes.getCortes());
            c.setFecha_corte(cortes.getFecha_corte());
            c.setSueldo(cortes.getSueldo());
            c.setFkec(cortes.getFkec());
            corteDAO.save(c);
            return ResponseEntity.ok().build();

        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
