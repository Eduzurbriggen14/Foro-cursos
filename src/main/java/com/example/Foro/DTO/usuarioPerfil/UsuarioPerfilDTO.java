package com.example.Foro.DTO.usuarioPerfil;

import com.example.Foro.domain.models.usuario.Perfil;
import com.example.Foro.domain.models.usuario.Usuario;
import com.example.Foro.domain.models.usuario.UsuarioPerfil;
import jakarta.validation.constraints.NotNull;

public record UsuarioPerfilDTO(@NotNull Long id,
                               @NotNull Usuario usuario,
                               @NotNull Perfil perfil,
                               boolean estadoPerfil) {

    public UsuarioPerfilDTO(UsuarioPerfil up){
        this(up.getIdUsuarioPerfil(), up.getUsuario(), up.getPerfil(), up.isEstadoPerfil());
    }
}
