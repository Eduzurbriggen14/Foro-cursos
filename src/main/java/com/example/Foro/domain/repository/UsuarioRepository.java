package com.example.Foro.domain.repository;

import com.example.Foro.domain.models.usuario.Perfil;
import com.example.Foro.domain.models.usuario.Usuario;
import com.example.Foro.domain.models.usuario.UsuarioPerfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByUsuario(String usuario);

    @Query("""
            SELECT up.perfil
            FROM UsuarioPerfil up
            WHERE up.usuario.id = :usuarioId
           """)
    List<UsuarioPerfil> findPerfilesByUsuarioId(@Param("usuarioId") Long usuarioId);
}
