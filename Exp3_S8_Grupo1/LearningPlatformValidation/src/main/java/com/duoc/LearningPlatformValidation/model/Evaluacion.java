package com.duoc.LearningPlatformValidation.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "evaluaciones")
@NoArgsConstructor
@Getter
@Setter
public class Evaluacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false)
    @Min(1)
    @Max(100)
    private Integer puntajeMaximo;

    @Column(nullable = false)
    @FutureOrPresent(message = "La fecha de aplicación no puede ser pasada")
    private Date fechaAplicacion;


    public Evaluacion(Curso curso, String nombre, Integer puntajeMaximo, Date fechaAplicacion) {
        this.curso = curso;
        this.nombre = nombre;
        this.puntajeMaximo = puntajeMaximo;
        this.fechaAplicacion = fechaAplicacion;
    }

}
