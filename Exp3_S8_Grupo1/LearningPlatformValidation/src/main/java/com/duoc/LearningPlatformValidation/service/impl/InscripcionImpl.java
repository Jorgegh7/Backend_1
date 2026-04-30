package com.duoc.LearningPlatformValidation.service.impl;

import com.duoc.LearningPlatformValidation.model.Curso;
import com.duoc.LearningPlatformValidation.model.Inscripcion;
import com.duoc.LearningPlatformValidation.model.Rol;
import com.duoc.LearningPlatformValidation.model.Usuario;
import com.duoc.LearningPlatformValidation.repository.CursoRepository;
import com.duoc.LearningPlatformValidation.repository.InscripcionRepository;
import com.duoc.LearningPlatformValidation.repository.UsuarioRepository;
import com.duoc.LearningPlatformValidation.service.contrato.InscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscripcionImpl implements InscripcionService {

    private final InscripcionRepository inscripcionRepository;
    private final CursoRepository cursoRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public InscripcionImpl(InscripcionRepository inscripcionRepository, CursoRepository cursoRepository, UsuarioRepository usuarioRepository) {
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
