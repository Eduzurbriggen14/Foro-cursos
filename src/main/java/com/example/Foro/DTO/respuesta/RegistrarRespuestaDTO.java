package com.example.Foro.DTO.respuesta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.NonNull;

import java.time.LocalDate;

public record RegistrarRespuestaDTO(@NotBlank String contenido,
                                    @PastOrPresent LocalDate fecha,
                                    @NonNull Long comentarioId,
                                    @NotNull Long usuarioId) {
}
