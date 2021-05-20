package com.odegant.IndustriaTextil.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "cortes")
@DynamicUpdate(value = true)
@Data
public class Cortes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cortes")
    private Integer id_cortes;

    @Column(name = "cortes", nullable = true)
    private Integer cortes;

    @Column(name = "fecha_corte", nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Calendar fecha_corte;

    @Column(name = "sueldo", nullable = true)
    private Double sueldo;

    @Column(name = "fkec", nullable = true)
    private Integer fkec;

}
