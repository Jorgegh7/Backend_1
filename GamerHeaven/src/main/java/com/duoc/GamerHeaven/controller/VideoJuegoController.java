package com.duoc.GamerHeaven.controller;


import com.duoc.GamerHeaven.Service.VideoJuegoService;
import com.duoc.GamerHeaven.model.entity.VideoJuego;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/videojuegos")
public class VideoJuegoController {

    private final VideoJuegoService videoJuegoService;

    @Autowired
    public VideoJuegoController(VideoJuegoService videoJuegoService) {
        this.videoJuegoService = videoJuegoService;
    }

    @GetMapping
    public ResponseEntity<List<VideoJuego>> listarVideoJuegos(){
        return ResponseEntity.ok(videoJuegoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<VideoJuego>> buscarVideoJuego(@PathVariable Integer id){
        return ResponseEntity.ok(videoJuegoService.findById(id));
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<Optional<VideoJuego>> buscarVideoJuegoPorTitulo(@PathVariable String titulo){
        return ResponseEntity.ok(videoJuegoService.findByTituloIgnoreCase(titulo));
    }

    @GetMapping("/plataforma/{plataforma}")
    public ResponseEntity<List<VideoJuego>> listarVideoJuegoPorPlataforma(@PathVariable String plataforma){
        return ResponseEntity.ok(videoJuegoService.findByPlataformaIgnoreCase(plataforma));
    }

    @PostMapping
    public ResponseEntity<VideoJuego> agregarVideoJuegos(@RequestBody VideoJuego videoJuego){
        return ResponseEntity.status(201).body(videoJuegoService.save(videoJuego));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<VideoJuego>> actualizarVideoJuego(@PathVariable Integer id, @RequestBody VideoJuego videoJuego){
        return ResponseEntity.ok(videoJuegoService.update(id, videoJuego));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> eliminarVideoJuego(@PathVariable Integer id){
        if(videoJuegoService.deleteById(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
