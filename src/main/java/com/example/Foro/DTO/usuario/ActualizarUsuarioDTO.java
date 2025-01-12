package com.example.Foro.DTO.usuario;

public record ActualizarUsuarioDTO(Long id,
                                   String usuario,
                                   String passw,
                                   String nombreUsuario,
                                   boolean estado) {
}
