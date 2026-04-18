package com.duoc.Semana6.controller;

import com.duoc.Semana6.model.VideoJuego;
import com.duoc.Semana6.service.VideoJuegoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/videojuegos/")
public class VideoJuegoController {

    private final VideoJuegoServiceImpl videoJuegoService;

    @Autowired
    public VideoJuegoController(VideoJuegoServiceImpl videoJuegoService) {
        this.videoJuegoService = videoJuegoService;
    }

    @GetMapping
    public ResponseEntity<List<VideoJuego>> getVideoJuegos(){
        return ResponseEntity.ok(videoJuegoService.getVideoJuegos());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getById(@PathVariable int id){
        return ResponseEntity.ok(videoJuegoService.findById(id));
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<VideoJuego>> getByTitulo(@PathVariable String titulo){
        return ResponseEntity.ok(videoJuegoService.findByTitulo(titulo));
    }

    @GetMapping("/plataforma/{plataforma}")
    public ResponseEntity<List<VideoJuego>> getByPlataforma(@PathVariable String plataforma){
        return ResponseEntity.ok(videoJuegoService.findByPlatforma(plataforma));
    }

}
