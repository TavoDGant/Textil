package com.odegant.IndustriaTextil.serviceImpl;

import com.odegant.IndustriaTextil.dao.CorteDAO;
import com.odegant.IndustriaTextil.dao.EmpleadoDAO;
import com.odegant.IndustriaTextil.entity.Cortes;
import com.odegant.IndustriaTextil.entity.Empleado;
import com.odegant.IndustriaTextil.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {

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
        empleado.setRegistro(Calendar.getInstance());
        try {
            empleadoDAO.save(empleado);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<ArrayList<Empleado>> empleadoID(Integer id){
        Optional<Empleado> optionalEmpleado = empleadoDAO.findById(id);
        ArrayList<Empleado> filteredList = (ArrayList<Empleado>) optionalEmpleado.stream().collect(Collectors.toList());
        try {
            return ResponseEntity.ok(filteredList);
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

    public List<Integer> totalMesCards() {
        List<Cortes> cortesList = corteDAO.findAll();
        ArrayList<Integer> cortesTarjetas = new ArrayList<Integer>();
        int sumaMesActual = cortesList.stream()
                .filter(cortes -> cortes.getFecha_corte().get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR)
                        && cortes.getFecha_corte().get(Calendar.MONTH) == Calendar.getInstance().get(Calendar.MONTH))
                .mapToInt(Cortes::getCortes).sum();
        int sumaMesAnterior = cortesList.stream()
                .filter(cortes -> cortes.getFecha_corte().get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR)
                        && cortes.getFecha_corte().get(Calendar.MONTH) == Calendar.getInstance().get((Calendar.MONTH))-1)
                .mapToInt(Cortes::getCortes).sum();
        int sumaDelAnio = cortesList.stream()
                .filter(cortes -> cortes.getFecha_corte().get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR))
                .mapToInt(Cortes::getCortes).sum();
        cortesTarjetas.add(sumaMesAnterior);
        cortesTarjetas.add(sumaMesActual);
        cortesTarjetas.add(sumaDelAnio);
        return cortesTarjetas;
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

    public ResponseEntity<List<Cortes>> cortesID(Integer fkec){
        List<Cortes> cortes = corteDAO.findByFkec(fkec);
        if(cortes.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(cortes);
        }
    }
}