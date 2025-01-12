package com.example.Foro.DTO.respuesta;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ActualizarRespuestaDTO(@NotNull Long id,
                                     String contenido,
                                     LocalDate fecha) {
}
