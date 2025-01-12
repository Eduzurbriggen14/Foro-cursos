package com.example.Foro.DTO.usuario;

import com.example.Foro.domain.models.usuario.Perfil;

public record PerfilDTO(
        Perfil perfil,
        boolean estado
) {
}
