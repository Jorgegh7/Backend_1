package com.duoc.GamerHeaven.Service;

import com.duoc.GamerHeaven.model.entity.VideoJuego;
import com.duoc.GamerHeaven.repository.VideoJuegoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideoJuegoServiceImpl implements VideoJuegoService{

    private final VideoJuegoRepository videoJuegoRepository;

    @Autowired
    public VideoJuegoServiceImpl(VideoJuegoRepository videoJuegoRepository) {
        this.videoJuegoRepository = videoJuegoRepository;
    }


    @Override
    public List<VideoJuego> findAll() {
        return videoJuegoRepository.findAll();
    }

    @Override
    public Optional<VideoJuego> findById(Integer id) {
        return videoJuegoRepository.findById(id);
    }

    @Override
    public Optional<VideoJuego> findByTituloIgnoreCase(String titulo) {
        return  videoJuegoRepository.findByTituloIgnoreCase(titulo);
    }

    @Override
    public List<VideoJuego> findByPlataformaIgnoreCase(String plataforma) {
        return videoJuegoRepository.findByPlataformaIgnoreCase(plataforma);
    }

    @Override
    public VideoJuego save(VideoJuego videoJuego) {
        return videoJuegoRepository.save(videoJuego);
    }

    @Override
    public Optional<VideoJuego> update(Integer id, VideoJuego videoJuego) {
        Optional<VideoJuego> videoJuegoActualizado = videoJuegoRepository.findById(id);
        return videoJuegoActualizado.map(v->{
            v.setTitulo(videoJuego.getTitulo());
            v.setPrecio(videoJuego.getPrecio());
            v.setPlataforma(videoJuego.getPlataforma());
            v.setDisponibilidad(videoJuego.getDisponibilidad());
            return videoJuegoRepository.save(v);
        });
    }

    @Override
    public Boolean deleteById(Integer id) {
        if(videoJuegoRepository.findById(id).isPresent()){
            videoJuegoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
