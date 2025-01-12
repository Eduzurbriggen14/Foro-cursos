package com.example.Foro.domain.repository;

import com.example.Foro.domain.models.comentario.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
}
