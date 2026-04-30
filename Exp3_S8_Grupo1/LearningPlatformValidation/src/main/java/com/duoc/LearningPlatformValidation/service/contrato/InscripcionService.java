package com.duoc.LearningPlatformValidation.service.contrato;

import com.duoc.LearningPlatformValidation.model.Curso;
import com.duoc.LearningPlatformValidation.model.Inscripcion;

import java.util.List;

public interface InscripcionService {

    List<Inscripcion> findByCursoId(Long id);
    Inscripcion save(Inscripcion inscripcion);
    Boolean delete(Long id);
}
