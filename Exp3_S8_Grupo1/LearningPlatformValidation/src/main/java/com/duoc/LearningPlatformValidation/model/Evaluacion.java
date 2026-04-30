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

/**
 * Entidad que representa una evaluación asociada a un curso.
 * Contiene nombre, puntaje máximo y fecha de aplicación.
 * La fecha debe ser presente o futura.
 */

@Entity
@Table(name = "evaluaciones")
@NoArgsConstructor
@Getter
@Setter
public class Evaluacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Curso al que pertenece esta evaluación */
    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String nombre;

    /** Puntaje máximo permitido entre 1 y 100 */
    @Column(nullable = false)
    @Min(1)
    @Max(100)
    private Integer puntajeMaximo;

    /** Fecha programada para la evaluación, no puede ser pasada */
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
