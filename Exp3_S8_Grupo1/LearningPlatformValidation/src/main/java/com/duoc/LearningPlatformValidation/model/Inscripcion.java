package com.duoc.LearningPlatformValidation.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Entidad que representa la inscripción de un estudiante en un curso.
 * La fecha de inscripción se genera automáticamente mediante @PrePersist
 * al momento de persistir el registro en la base de datos.
 */

@Entity
@Table(name = "inscripciones")
@Getter
@Setter
@NoArgsConstructor
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "estudiante_id", nullable = false)
    private Usuario estudiante;

    @Column(nullable = false)
    private Date fechaInscripcion;

    @PrePersist
    public void prePersist() {
        this.fechaInscripcion = new Date();
    }

    public Inscripcion(Curso curso, Usuario estudiante, Date fechaInscripcion) {
        this.curso = curso;
        this.estudiante = estudiante;
        this.fechaInscripcion = fechaInscripcion;
    }
}
