package com.example.Foro.DTO.curso;

import com.example.Foro.domain.models.curso.CategoriaCurso;
import com.example.Foro.domain.models.curso.Curso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CursoDTO(@NotBlank Long id,
                       @NotBlank String nombreCurso,
                       @NotNull CategoriaCurso categoriaCurso) {
    public CursoDTO(Curso curso){
        this(curso.getIdCurso(), curso.getNombreCurso(), curso.getCategoria());
    }
}
