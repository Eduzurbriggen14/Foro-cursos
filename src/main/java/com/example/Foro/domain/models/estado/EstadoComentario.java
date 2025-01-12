package com.example.Foro.domain.models.estado;

public enum EstadoComentario {

    PENDIENTE("Pendiente"),
    RESUELTO("Resuelto");

    private String estadoComentario;

    EstadoComentario(String estadoComentario){
        this.estadoComentario = estadoComentario;
    }

    public String getEstadoComentario() {
        return estadoComentario;
    }

    public void setEstadoComentario(String estadoComentario) {
        this.estadoComentario = estadoComentario;
    }
}
