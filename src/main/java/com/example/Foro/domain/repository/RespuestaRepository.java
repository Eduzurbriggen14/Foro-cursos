package com.example.Foro.domain.repository;

import com.example.Foro.domain.models.comentario.Comentario;
import com.example.Foro.domain.models.respuesta.Respuesta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {

    Comentario findComentarioById(Long id);

    List<Comentario> findByCategoria(String categoria);

    List<Comentario> findByUsuarioId(Long usuarioId);


}
