package com.odegant.IndustriaTextil.controller;

import com.odegant.IndustriaTextil.entity.Cortes;
import com.odegant.IndustriaTextil.entity.Empleado;
import com.odegant.IndustriaTextil.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    public String agregarCorte(@ModelAttribute("corte") Cortes corte){
        adminService.guardarCorte(corte);
        return "redirect:/admin/detalles/"+corte.getFkec();
    }

    @GetMapping("/nuevo-empleado")
    public String nuevoEmpleado(Model model){
        Empleado empleado = new Empleado();
        model.addAttribute("empleado", empleado);
        return "nuevo-empleado";
    }

    @PostMapping("/agregar")
    public String agregarEmpleado(@ModelAttribute("empleado") Empleado empleado){
        adminService.guardarEmpleado(empleado);
        return "redirect:/admin";
    }
}
