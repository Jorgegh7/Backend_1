package com.duoc.LearningPlatformValidation.service.contrato;


import com.duoc.LearningPlatformValidation.model.Evaluacion;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz que define las operaciones de negocio para la entidad Evaluacion.
 */

public interface EvaluacionService {

    List<Evaluacion> findAll();
    List<Evaluacion> findAllByCurso(Long cursoId);
    Optional<Evaluacion> save(Evaluacion evaluacion);
    Optional<Evaluacion> update(Long id, Evaluacion evaluacion);
}
