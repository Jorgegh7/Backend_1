package com.duoc.GamerHeaven.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "videojuegos")
@Getter
@Setter
@NoArgsConstructor
public class VideoJuego {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, length = 150)
    private String titulo;

    @Min(1)
    @Column(nullable = false)
    private Integer precio;

    @Column(nullable = false, length = 50)
    private String plataforma;

    @Column(nullable = false)
    private Boolean disponibilidad;


    public VideoJuego(String titulo, Integer precio, String plataforma, Boolean disponibilidad) {
        this.titulo = titulo;
        this.precio = precio;
        this.plataforma = plataforma;
        this.disponibilidad = disponibilidad;
    }

}
