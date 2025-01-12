package com.example.Foro.DTO.comentario;

import com.example.Foro.domain.models.comentario.Comentario;
import com.example.Foro.domain.models.estado.EstadoComentario;

import java.time.LocalDate;

public record NuevoComentarioDTO(Long id,
                                 String titulo,
                                 String contenido,
                                 EstadoComentario estado,
                                 String curso,
                                 String usuario,
                                 LocalDate fecha
                                 ) {
    public NuevoComentarioDTO(Comentario c){
        this(c.getIdComentario(), c.getTitulo(),c.getContenido(), c.getEstadoComentario(), c.getCurso().getNombreCurso(),
                c.getUsuario().getNombreUsuario(), c.getFecha());
    }
}
