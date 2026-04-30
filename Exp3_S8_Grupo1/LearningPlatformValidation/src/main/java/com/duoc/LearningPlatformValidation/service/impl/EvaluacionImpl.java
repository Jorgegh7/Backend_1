package com.duoc.LearningPlatformValidation.service.impl;

import com.duoc.LearningPlatformValidation.model.Curso;
import com.duoc.LearningPlatformValidation.model.Evaluacion;
import com.duoc.LearningPlatformValidation.repository.CursoRepository;
import com.duoc.LearningPlatformValidation.repository.EvaluacionRepository;
import com.duoc.LearningPlatformValidation.service.contrato.EvaluacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementación del servicio de Evaluacion.
 * Valida que el curso exista antes de guardar o actualizar una evaluación.
 */

@Service
public class EvaluacionImpl implements EvaluacionService {
    
    private final EvaluacionRepository evaluacionRepository;
    private final CursoRepository cursoRepository;

    @Autowired
    public EvaluacionImpl(EvaluacionRepository evaluacionRepository, CursoRepository cursoRepository) {
        this.evaluacionRepository = evaluacionRepository;
        this.cursoRepository = cursoRepository;
    }


    @Override
    public List<Evaluacion> findAll() {
        return evaluacionRepository.findAll();
    }

    @Override
    public List<Evaluacion> findAllByCurso(Long cursoId) {
        cursoRepository.findById(cursoId)
                .orElseThrow(()-> new RuntimeException("Curso no encontrado"));
        return evaluacionRepository.findByCursoId(cursoId);
    }

    @Override
    public Optional<Evaluacion> save(Evaluacion evaluacion) {
        Curso curso = cursoRepository.findById(evaluacion.getCurso().getId())
                .orElseThrow(()-> new RuntimeException("Curso no encontrado"));
        evaluacion.setCurso(curso);
        return Optional.of(evaluacionRepository.save(evaluacion));
    }

    @Override
    public Optional<Evaluacion> update(Long id, Evaluacion evaluacion) {
        Evaluacion evaluacionActualizada = evaluacionRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Evaluacion no encontrada"));

        if (evaluacion.getCurso() == null || evaluacion.getCurso().getId() == null) {
            throw new RuntimeException("Debe indicar el curso");
        }

        Curso curso = cursoRepository.findById(evaluacion.getCurso().getId())
                        .orElseThrow(()-> new RuntimeException("Curso no encontrado"));

        evaluacionActualizada.setNombre(evaluacion.getNombre());
        evaluacionActualizada.setCurso(curso);
        evaluacionActualizada.setPuntajeMaximo(evaluacion.getPuntajeMaximo());
        evaluacionActualizada.setFechaAplicacion(evaluacion.getFechaAplicacion());

        return Optional.of(evaluacionRepository.save(evaluacionActualizada));
    }
}
