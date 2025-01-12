package com.example.Foro.domain.repository;

import com.example.Foro.domain.models.curso.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso,Long> {
}
