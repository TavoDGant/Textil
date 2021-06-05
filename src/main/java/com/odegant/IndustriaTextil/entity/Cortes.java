package com.odegant.IndustriaTextil.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NegativeOrZero;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    @NotNull(message = "{numeroCorte.Cortes.cortes}")
    private Integer cortes;

    @Column(name = "fecha_corte", nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "{fechaCorte.Cortes.fecha_corte}")
    private Calendar fecha_corte;

    @Column(name = "sueldo", nullable = true)
    private Double sueldo;

    @Column(name = "fkec", nullable = true)
    private Integer fkec;

}
