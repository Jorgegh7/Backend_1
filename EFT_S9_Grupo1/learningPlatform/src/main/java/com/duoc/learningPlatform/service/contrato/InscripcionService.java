package com.duoc.learningPlatform.service.contrato;

import com.duoc.learningPlatform.model.Inscripcion;

import java.util.List;

/**
 * Interfaz que define las operaciones de negocio para la entidad Inscripcion.
 */

public interface InscripcionService {

    List<Inscripcion> findByCursoId(Long id);
    Inscripcion save(Inscripcion inscripcion);
    Boolean delete(Long id);
}