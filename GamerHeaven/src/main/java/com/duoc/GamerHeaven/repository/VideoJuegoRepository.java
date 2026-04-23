package com.duoc.GamerHeaven.repository;

import com.duoc.GamerHeaven.model.entity.VideoJuego;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VideoJuegoRepository extends JpaRepository<VideoJuego, Integer> {

    List<VideoJuego> findByDisponibilidad(Boolean disponibilidad);

    List<VideoJuego> findByPlataformaIgnoreCase(String plataforma);

    Optional<VideoJuego> findByTituloIgnoreCase(String titulo);


}
