package com.duoc.Semana6.service;

import com.duoc.Semana6.model.VideoJuego;
import com.duoc.Semana6.repository.VideoJuegoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideoJuegoServiceImpl {

    private final VideoJuegoRepository videoJuegoRepository;

    @Autowired
    public VideoJuegoServiceImpl(VideoJuegoRepository videoJuegoRepository) {
        this.videoJuegoRepository = videoJuegoRepository;
    }

    public List<VideoJuego> getVideoJuegos(){
        return videoJuegoRepository.findAll();
    }

    public Optional<VideoJuego> findById(int id){
        return videoJuegoRepository.findById(id);
    }

    public List<VideoJuego> findByTitulo(String titulo){
        return videoJuegoRepository.findByTitulo(titulo);
    }

    public List<VideoJuego> findByPlatforma(String plataforma){
        return videoJuegoRepository.findByPlataforma(plataforma);
    }

}
