package com.odegant.IndustriaTextil.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    @NotBlank(message = "{tamanioNombre.Empleado.nombre}")
    private String nombre;

    @Column(name = "ape_pat", nullable = false, length = 30)
    @NotBlank(message = "{tamanioApePat.Empleado.ape_pat}")
    private String ape_pat;

    @Column(name = "ape_mat", nullable = false, length = 30)
    @NotBlank(message = "{tamanioApeMat.Empleado.ape_mat}")
    private String ape_mat;

    @Column(name = "registro", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Calendar registro;

    @NotNull(message = "{tamanioNacimiento.Empleado.nacimiento}")
    @Column(name = "nacimiento", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Calendar nacimiento;

    @JoinColumn(name = "fkec", referencedColumnName = "id_empleado", nullable = true)
    @OneToMany(targetEntity = Cortes.class, cascade = CascadeType.ALL)
    private List<Cortes> cortes;
}
