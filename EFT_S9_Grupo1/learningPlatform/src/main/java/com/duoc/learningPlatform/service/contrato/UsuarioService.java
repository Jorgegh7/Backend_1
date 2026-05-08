package com.duoc.learningPlatform.service.contrato;

import com.duoc.learningPlatform.model.Usuario;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz que define las operaciones de negocio para la entidad Usuario.
 */

public interface UsuarioService {

    List<Usuario> findAll();
    Optional<Usuario> findById(Long id);
    Usuario save(Usuario usuario);
    Optional<Usuario> update(Long id, Usuario usuario);
    Boolean delete(Long id);


}