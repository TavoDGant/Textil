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
import java.util.Objects;

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
    public String agregarEmpleado(@Valid Empleado empleado, BindingResult result){
        if(result.hasErrors()){
            return "nuevo-empleado";
        }else{
            adminService.guardarEmpleado(empleado);
            return "redirect:/admin";
        }
    }
}
