package com.example.Foro.domain.models.curso;

public enum CategoriaCurso {
    PROGRAMACION("Programación"),
    BASES_DE_DATOS("Bases de Datos"),
    FRONTEND("Frontend"),
    BACKEND("Backend"),
    DEVOPS("DevOps"),
    MOBILE("Desarrollo Móvil"),
    INTELIGENCIA_ARTIFICIAL("Inteligencia Artificial"),
    SEGURIDAD("Seguridad Informática");

    private String categoriaCurso;

    CategoriaCurso(String categoriaCurso) {
        this.categoriaCurso = categoriaCurso;
    }

    public String getCategoriaCurso() {
        return categoriaCurso;
    }

    public void setCategoriaCurso(String categoriaCurso) {
        this.categoriaCurso = categoriaCurso;
    }

}
