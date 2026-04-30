package com.duoc.LearningPlatformValidation.service.impl;

import com.duoc.LearningPlatformValidation.model.Curso;
import com.duoc.LearningPlatformValidation.model.Rol;
import com.duoc.LearningPlatformValidation.model.Usuario;
import com.duoc.LearningPlatformValidation.repository.CursoRepository;
import com.duoc.LearningPlatformValidation.repository.UsuarioRepository;
import com.duoc.LearningPlatformValidation.service.contrato.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementación del servicio de Curso.
 * Valida que el usuario asignado como profesor tenga el rol PROFESOR
 * antes de guardar o actualizar un curso.
 */

@Service
public class CursoServiceImpl implements CursoService {

    private final CursoRepository cursoRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public CursoServiceImpl(CursoRepository cursoRepository, UsuarioRepository usuarioRepository) {
        this.cursoRepository = cursoRepository;
        this.usuarioRepository = usuarioRepository;
    }


    @Override
    public List<Curso> findAll() {
        return cursoRepository.findAll();
    }

    @Override
    public Optional<Curso> findById(Long id) {
        return cursoRepository.findById(id);
    }

    /**
     * Guarda un nuevo curso validando que el usuario asignado sea profesor.
     * @param curso el curso a guardar
     * @return el curso guardado
     * @throws RuntimeException si el profesor no existe o no tiene rol PROFESOR
     */
    @Override
    public Curso save(Curso curso) {
        Usuario profesor = usuarioRepository.findById(curso.getProfesor().getId())
                .orElseThrow(()->new RuntimeException("Profesor no encontrado"));
        if(profesor.getRol() == Rol.PROFESOR){
            curso.getProfesor().setNombre(profesor.getNombre());
            curso.getProfesor().setCorreo(profesor.getCorreo());
            curso.getProfesor().setRol(profesor.getRol());
            return cursoRepository.save(curso);
        }
        return null;
    }

    /**
     * Actualiza un curso existente validando que el curso y el profesor existan.
     * @param id el id del curso a actualizar
     * @param curso los nuevos datos del curso
     * @return el curso actualizado
     * @throws RuntimeException si el curso no existe, el profesor no existe o no tiene rol PROFESOR
     */
    @Override
    public Optional<Curso> update(Long id, Curso curso) {
        //Existe el curso
        if (!cursoRepository.existsById(id)) {
            throw new RuntimeException("Curso no encontrado");
        }

        //Rescatamos al profesor
        Usuario profesor = usuarioRepository.findById(curso.getProfesor().getId())
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));

        //Si el profesor cumple con el ROL
        if (profesor.getRol() != Rol.PROFESOR) {
            throw new RuntimeException("El usuario no es un profesor");
        }

        return cursoRepository.findById(id).map(c -> {
            c.setNombre(curso.getNombre());
            c.setDescripcion(curso.getDescripcion());
            c.setProfesor(profesor);
            return cursoRepository.save(c);
        });
    }

    @Override
    public Boolean delete(Long id) {
        if(cursoRepository.existsById(id)){
            cursoRepository.deleteById(id);
            return true;
        }
        throw new RuntimeException("Curso no encontrado");
    }
}
