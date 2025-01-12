package com.example.Foro.DTO.respuesta;

import com.example.Foro.domain.models.respuesta.Respuesta;

import java.time.LocalDate;

public record RespuestaDTO(Long id,
                           String contenido,
                           LocalDate fecha,
                           Long idComentario,
                           String usuario
                           ) {
    public RespuestaDTO(Respuesta r){
        this(r.getIdRespuesta(), r.getContenido(), r.getFecha(), r.getComentario().getIdComentario(), r.getUsuario().getNombreUsuario());
    }
}
