package com.example.Foro.domain.models.usuario;

public enum Perfil {
    ESTUDIANTE("Estudiante"),
    PROFESOR("Profesor"),
    ADMINISTRADOR("Administrador");

    private String perfil;

    Perfil(String perfil) {
        this.perfil = perfil;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
}