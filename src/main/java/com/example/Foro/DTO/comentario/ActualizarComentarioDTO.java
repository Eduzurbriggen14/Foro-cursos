package com.example.Foro.DTO.comentario;

import com.example.Foro.domain.models.estado.EstadoComentario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ActualizarComentarioDTO(@NotNull Long id,
                                      @NotBlank String titulo,
                                      @NotBlank String contenido,
                                      EstadoComentario estado) {
}
