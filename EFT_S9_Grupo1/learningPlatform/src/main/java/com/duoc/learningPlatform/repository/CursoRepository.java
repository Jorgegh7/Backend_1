package com.duoc.learningPlatform.repository;

import com.duoc.learningPlatform.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
