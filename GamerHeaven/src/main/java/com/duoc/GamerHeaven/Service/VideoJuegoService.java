package com.duoc.GamerHeaven.Service;

import com.duoc.GamerHeaven.model.entity.VideoJuego;

import java.util.List;
import java.util.Optional;

public interface VideoJuegoService {

    List<VideoJuego> findAll();
    Optional<VideoJuego> findById(Integer id);
    Optional<VideoJuego> findByTituloIgnoreCase(String titulo);
    List<VideoJuego> findByPlataformaIgnoreCase(String plataforma);
    VideoJuego save(VideoJuego videoJuego);
    Optional<VideoJuego> update(Integer id, VideoJuego videoJuego);
    Boolean deleteById(Integer id);

}
