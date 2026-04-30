package com.duoc.LearningPlatformValidation.repository;

import com.duoc.LearningPlatformValidation.model.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InscripcionRepository extends JpaRepository<Inscripcion,Long> {
    List<Inscripcion> findByCursoId(Long cursoId);
}
