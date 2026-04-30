package com.duoc.LearningPlatformValidation.repository;

import com.duoc.LearningPlatformValidation.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
