package com.example.Foro.domain.repository;

import com.example.Foro.domain.models.usuario.UsuarioPerfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioPerfilRepository extends JpaRepository<UsuarioPerfil, Long> {
}
