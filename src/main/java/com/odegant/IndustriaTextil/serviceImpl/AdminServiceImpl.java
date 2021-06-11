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
        try {
            return ResponseEntity.ok(empleadoArrayList(id));
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
        Empleado nuevoEmpleado = empleadoDAO.getOne(empleado.getId_empleado());
        nuevoEmpleado.setNombre(empleado.getNombre());
        nuevoEmpleado.setApe_pat(empleado.getApe_pat());
        nuevoEmpleado.setApe_mat(empleado.getApe_mat());
        nuevoEmpleado.setNacimiento(empleado.getNacimiento());
        try {
            empleadoDAO.save(nuevoEmpleado);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    public Map<String, Integer> totalMesCards() {
        List<Cortes> cortesList = corteDAO.findAll();
        Map<String, Integer> cortesTarjetas = new HashMap<>();
        cortesTarjetas.put( "Cortes_Mes_Actual",cortesList.stream()
                .filter(cortes -> cortes.getFecha_corte().get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR)
                        && cortes.getFecha_corte().get(Calendar.MONTH) == Calendar.getInstance().get(Calendar.MONTH))
                .mapToInt(Cortes::getCortes).sum());
        cortesTarjetas.put("Cortes_Mes_Anterior",cortesList.stream()
                .filter(cortes -> cortes.getFecha_corte().get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR)
                        && cortes.getFecha_corte().get(Calendar.MONTH) == Calendar.getInstance().get((Calendar.MONTH))-1)
                .mapToInt(Cortes::getCortes).sum());
        cortesTarjetas.put("Cortes_Anio",cortesList.stream()
                .filter(cortes -> cortes.getFecha_corte().get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR))
                .mapToInt(Cortes::getCortes).sum());
        return cortesTarjetas;
    }

    public Map<Integer, List> cortesMensuales(){
        List<Cortes> cortesList = corteDAO.findAll();
        Map<Integer, List> mapa = new HashMap<>();
        mapa.put(1, cortesList.stream()
                .filter(cortes -> cortes.getFecha_corte().get(Calendar.MONTH) == 4)
                .collect(Collectors.toList()));
        System.out.println(mapa);
        return mapa;
    }

    //-----------------CORTES---------------------------------------------
    public ResponseEntity<HttpStatus> guardarCorte(Cortes cortes){
        try {
            cortes.setSueldo(cortes.getCortes()*0.25);
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
            c.setSueldo(cortes.getCortes()*0.25);
            c.setFkec(cortes.getFkec());
            corteDAO.save(c);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<List<Cortes>> cortesFkec(Integer fkec){
        List<Cortes> cortes = corteDAO.findByFkec(fkec);
        if(cortes.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(cortes);
        }
    }

    public Optional<Cortes> cortesID(Integer id) {
        return corteDAO.findById(id);
    }

    public ResponseEntity<HttpStatus> eliminarCorte(Integer id){
        try {
            corteDAO.deleteById(id);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    //--------------Herramienta
    private ArrayList<Empleado> empleadoArrayList(Integer id){
        Optional<Empleado> empleados = empleadoDAO.findById(id);
        return (ArrayList<Empleado>) empleados.stream().collect(Collectors.toList());
    }
}