package com.odegant.IndustriaTextil.controller;

import com.odegant.IndustriaTextil.entity.Cortes;
import com.odegant.IndustriaTextil.entity.Empleado;
import com.odegant.IndustriaTextil.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("")
    public String admin(Model model){
        model.addAttribute("empleados",adminService.listaEmpleados());
        model.addAttribute("tarjetas", adminService.totalMesCards());
        return "admin";
    }

    @GetMapping("/detalles/{id}")
    public String detallesEmpleado(Model model, @PathVariable Integer id){
        ResponseEntity<ArrayList<Empleado>> empleados = adminService.empleadoID(id);
        int fk = Objects.requireNonNull(empleados.getBody()).get(0).getId_empleado();
        Cortes corte = new Cortes();
        corte.setFkec(fk);
        model.addAttribute("detalleEmpleado", empleados);
        model.addAttribute("corte", corte);
        return "detalles";
    }

    @PostMapping("/detalles/agregarCorte")
    public String agregarCorte(@Valid Cortes corte, BindingResult result){
        if(result.hasErrors()){
            return "redirect:/admin/detalles/"+corte.getFkec();
        }
        else{
            adminService.guardarCorte(corte);
            return "redirect:/admin/detalles/"+corte.getFkec();
        }
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEmpleado(@PathVariable("id") Integer id, Model model){
        adminService.eliminarEmpleado(id);
        return "redirect:/admin";
    }

    @GetMapping("/detalles/eliminar/{id}/{fkec}")
    public String eliminarCorte(@PathVariable Integer id, @PathVariable Integer fkec, Model model){
        adminService.eliminarCorte(id);
        return "redirect:/admin/detalles/"+fkec;
    }

    @GetMapping("/nuevo-empleado")
    public String nuevoEmpleado(Model model){
        Empleado empleado = new Empleado();
        model.addAttribute("empleado", empleado);
        return "nuevo-empleado";
    }

    //@ModelAttribute("empleado")
    @PostMapping("/agregar")
    public String agregarEmpleado(@Valid Empleado empleado, BindingResult result) {
        if (result.hasErrors()) {
            return "nuevo-empleado";
        }
        adminService.guardarEmpleado(empleado);
        return "redirect:/admin";
    }

    @GetMapping("/editar-empleado/{id}")
    public String vistaEditarEmpleado(Model model, @PathVariable Integer id){
        ResponseEntity<ArrayList<Empleado>> list = adminService.empleadoID(id);
        Empleado empleado = new Empleado();
        empleado.setId_empleado(id);
        empleado.setNombre(Objects.requireNonNull(list.getBody()).get(0).getNombre());
        empleado.setApe_pat(list.getBody().get(0).getApe_pat());
        empleado.setApe_mat(list.getBody().get(0).getApe_mat());
        empleado.setNacimiento(list.getBody().get(0).getNacimiento());
        model.addAttribute("empleado", empleado);
        return "editar-empleado";
    }

    @PostMapping("/editar-empleado/actualizar")
    public String actualizarEmpleado(@Valid Empleado empleado, BindingResult result){
        if(result.hasErrors()){
            return "editar-empleado";
        }
        adminService.actualizarEmpleado(empleado);
        return "redirect:/admin";
    }

    @GetMapping("/editar-corte/{id}")
    public String vistaEditarCorte(Model model, @PathVariable Integer id){
        Optional<Cortes> cortesList = adminService.cortesID(id);
        Cortes cortes = new Cortes();
        cortes.setId_cortes(id);
        cortes.setCortes(cortesList.get().getCortes());
        cortes.setFecha_corte(cortesList.get().getFecha_corte());
        cortes.setSueldo(cortesList.get().getSueldo());
        cortes.setFkec(cortesList.get().getFkec());
        model.addAttribute("cortes", cortes);
        return "editar-corte";
    }

    @PostMapping("/editar-corte/actualizar")
    public String actualizarCorte(@Valid Cortes cortes, BindingResult result){
        if(result.hasErrors()){
            return "editar-corte";
        }
        adminService.actualizarCorte(cortes);
        return "redirect:/admin/detalles/"+cortes.getFkec();
    }
}
