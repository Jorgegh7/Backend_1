package com.duoc.learningPlatform.service.contrato;

import com.duoc.learningPlatform.model.Curso;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz que define las operaciones de negocio para la entidad Curso.
 */

public interface CursoService {

    List<Curso> findAll();
    Optional<Curso> findById(Long id);
    Curso save(Curso curso);
    Optional<Curso> update(Long id, Curso curso);
    Boolean delete(Long id);

}