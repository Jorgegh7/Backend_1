package com.duoc.Semana6.model;

import jakarta.persistence.*;

@Entity
public class VideoJuego {

    @Id
    private int Id;

    @Column(nullable = false, length = 150)
    private String titulo;

    @Column(nullable = false)
    private int precio;

    @Column(nullable = false)
    private String plataforma;

    @Column(nullable = false)
    private Boolean disponibilidad;

    public VideoJuego(String titulo, int precio, String plataforma, Boolean disponibilidad) {
        this.titulo = titulo;
        this.precio = precio;
        this.plataforma = plataforma;
        this.disponibilidad = disponibilidad;
    }

    public VideoJuego(int Id, String titulo, int precio, String plataforma, Boolean disponibilidad) {
        this.Id = Id;
        this.titulo = titulo;
        this.precio = precio;
        this.plataforma = plataforma;
        this.disponibilidad = disponibilidad;
    }

    public int getId() {
        return Id;
    }

    public void setID(int Id) {
        this.Id = Id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public Boolean getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(Boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
}
