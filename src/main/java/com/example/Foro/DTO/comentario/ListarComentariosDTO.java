package com.example.Foro.DTO.comentario;

import com.example.Foro.domain.models.comentario.Comentario;
import com.example.Foro.domain.models.estado.EstadoComentario;

public record ListarComentariosDTO(Long id,
                                   String titulo,
                                   String contenido,
                                   EstadoComentario estado,
                                   String curso,
                                   String usuario
                                ) {
    public ListarComentariosDTO(Comentario comentario){
        this(comentario.getIdComentario(), comentario.getTitulo(), comentario.getContenido(),
                comentario.getEstadoComentario(), comentario.getCurso().getNombreCurso(), comentario.getUsuario().getNombreUsuario());
    }
}
