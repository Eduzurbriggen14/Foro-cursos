package com.example.Foro.DTO.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record RegistrarUsuarioDTO(@NotBlank String usuario,
                                  @NotBlank String passw,
                                  @NotBlank String nombreUsuario,
                                  @NotBlank boolean activo,
                                  @NotNull List<PerfilDTO> perfiles) {
}
