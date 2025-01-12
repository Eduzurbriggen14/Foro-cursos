package com.example.Foro.DTO.comentario;

import com.example.Foro.DTO.respuesta.RespuestaDTO;
import com.example.Foro.domain.models.comentario.Comentario;
import com.example.Foro.domain.models.estado.EstadoComentario;

import java.time.LocalDate;
import java.util.List;

public record ComentarioDTO(Long id,
                            String titulo,
                            String contenido,
                            EstadoComentario estado,
                            String curso,
                            String usuario,
                            LocalDate fecha,
                            List<RespuestaDTO> respuestas
                            ) {
    public ComentarioDTO(Comentario comentario){
        this(comentario.getIdComentario(), comentario.getTitulo(),comentario.getContenido(),comentario.getEstadoComentario(),
                comentario.getCurso().getNombreCurso(),comentario.getUsuario().getNombreUsuario(),
                comentario.getFecha(), comentario.getRespuestas().stream().map(RespuestaDTO::new).toList());
    }
}
