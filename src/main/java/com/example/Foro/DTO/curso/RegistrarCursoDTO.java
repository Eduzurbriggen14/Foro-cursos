package com.example.Foro.DTO.curso;

import com.example.Foro.domain.models.curso.CategoriaCurso;
import com.example.Foro.domain.models.curso.Curso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegistrarCursoDTO(@NotBlank String nombreCurso,
                                @NotNull CategoriaCurso categoriaCurso) {
    public RegistrarCursoDTO(Curso curso){
        this(curso.getNombreCurso(), curso.getCategoria());
    }
}
