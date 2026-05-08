package com.duoc.learningPlatform.service.impl;

import com.duoc.learningPlatform.model.Curso;
import com.duoc.learningPlatform.model.Inscripcion;
import com.duoc.learningPlatform.model.Rol;
import com.duoc.learningPlatform.model.Usuario;
import com.duoc.learningPlatform.repository.CursoRepository;
import com.duoc.learningPlatform.repository.InscripcionRepository;
import com.duoc.learningPlatform.repository.UsuarioRepository;
import com.duoc.learningPlatform.service.contrato.InscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementación del servicio de Inscripcion.
 * Valida que el estudiante tenga rol ESTUDIANTE y que el curso exista
 * antes de registrar una inscripción.
 */

@Service
public class InscripcionServiceImpl implements InscripcionService {

    private final InscripcionRepository inscripcionRepository;
    private final CursoRepository cursoRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public InscripcionServiceImpl(InscripcionRepository inscripcionRepository, CursoRepository cursoRepository, UsuarioRepository usuarioRepository) {
        this.inscripcionRepository = inscripcionRepository;
        this.cursoRepository = cursoRepository;
        this.usuarioRepository = usuarioRepository;
    }


    @Override
    public List<Inscripcion> findByCursoId(Long id) {
        if(cursoRepository.existsById(id)){
            return inscripcionRepository.findByCursoId(id);
        }
        throw new RuntimeException("Curso no encontrado");
    }

    @Override
    public Inscripcion save(Inscripcion inscripcion) {
        Usuario estudiante = usuarioRepository.findById(inscripcion.getEstudiante().getId())
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
        if(estudiante.getRol() != Rol.ESTUDIANTE){
            throw new RuntimeException("El usuario no es un estudiante");
        }
        Curso curso = cursoRepository.findById(inscripcion.getCurso().getId())
                .orElseThrow(()-> new RuntimeException("Curso no encontrado"));

        inscripcion.setEstudiante(estudiante);
        inscripcion.setCurso(curso);
        return inscripcionRepository.save(inscripcion);
    }

    @Override
    public Boolean delete(Long id) {
        if(inscripcionRepository.existsById(id)){
            inscripcionRepository.deleteById(id);
            return true;
        }
        throw new RuntimeException("Inscripción no encontrada");
    }
}
