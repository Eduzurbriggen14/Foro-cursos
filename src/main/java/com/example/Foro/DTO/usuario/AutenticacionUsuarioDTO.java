package com.example.Foro.DTO.usuario;

import jakarta.validation.constraints.NotBlank;

public record AutenticacionUsuarioDTO(@NotBlank String usuario,
                                      @NotBlank String passw) {
}
