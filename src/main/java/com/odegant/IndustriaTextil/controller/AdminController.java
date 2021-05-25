package com.odegant.IndustriaTextil.controller;

import com.odegant.IndustriaTextil.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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


}
