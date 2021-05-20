package com.odegant.IndustriaTextil.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "empleados")
@DynamicUpdate(value = true)
@Data
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado")
    private Integer id_empleado;

    @Column(name = "nombre", nullable = false, length = 30)
    private String nombre;

    @Column(name = "ape_pat", nullable = false, length = 30)
    private String ape_pat;

    @Column(name = "ape_mat", nullable = false, length = 30)
    private String ape_mat;

    @Column(name = "nacimiento", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Calendar nacimiento;

    @JoinColumn(name = "fkec", referencedColumnName = "id_empleado")
    @OneToMany(targetEntity = Cortes.class, cascade = CascadeType.ALL)
    private List<Cortes> cortes;
}
