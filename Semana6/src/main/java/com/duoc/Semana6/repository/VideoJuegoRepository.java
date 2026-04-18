package com.duoc.Semana6.repository;

import com.duoc.Semana6.model.VideoJuego;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class VideoJuegoRepository {

    private final List<VideoJuego> videoJuegos = new ArrayList<>(Arrays.asList(
            new VideoJuego(1, "The Legend of Zelda: Tears of the Kingdom", 49990, "Nintendo Switch", true),
            new VideoJuego(2, "God of War Ragnarök", 39990, "PlayStation 5", true),
            new VideoJuego(3, "Halo Infinite", 29990, "Xbox Series X", false),
            new VideoJuego(4, "Resident Evil 4 Remake", 44990, "PlayStation 5", true),
            new VideoJuego(5, "Super Mario Bros. Wonder", 42990, "Nintendo Switch", false)
    ));

    public List<VideoJuego> findAll(){
        return videoJuegos;
    }

    public Optional<VideoJuego> findById(int id){
        return videoJuegos.stream()
                .filter(v-> v.getId() == id)
                .findFirst();
    }

    public List<VideoJuego> findByTitulo(String titulo){
        return videoJuegos.stream()
                .filter(v -> v.getTitulo().equalsIgnoreCase(titulo))
                .collect(Collectors.toList());
    }

    public List<VideoJuego> findByPlataforma(String plataforma){
        return videoJuegos.stream()
                .filter(v -> v.getPlataforma().equalsIgnoreCase(plataforma))
                .collect(Collectors.toList());
    }

}
