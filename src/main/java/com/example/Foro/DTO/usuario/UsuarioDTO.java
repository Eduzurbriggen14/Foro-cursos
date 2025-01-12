package com.example.Foro.DTO.usuario;

import com.example.Foro.domain.models.usuario.Usuario;
import com.example.Foro.domain.models.usuario.UsuarioPerfil;

import java.util.List;

public record UsuarioDTO(Long id,
                         String usuario,
                         String nombreUsuario,
                         List<PerfilDTO> perfiles,
                         boolean activo) {

    public UsuarioDTO(Usuario usuario) {
        this(
                usuario.getIdUsuario(),
                usuario.getUsuario(),
                usuario.getNombreUsuario(),
                usuario.getUsuarioPerfiles().stream()
                        .map(UsuarioPerfil::toPerfilDTO)
                        .toList(),
                usuario.isActivo()
        );
    }
}
