package com.duoc.learningPlatform.repository;

import com.duoc.learningPlatform.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
