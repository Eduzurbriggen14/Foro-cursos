package com.example.Foro.domain.models.usuarioCurso;
import com.example.Foro.domain.models.usuario.Usuario;
import com.example.Foro.domain.models.curso.Curso;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "UsuarioCurso")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idUsuarioCurso")
public class UsuarioCurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuarioCurso;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    private LocalDate fechaInscripcion;

    public Long getIdUsuarioCurso() {
        return idUsuarioCurso;
    }

    public void setIdUsuarioCurso(Long idUsuarioCurso) {
        this.idUsuarioCurso = idUsuarioCurso;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(LocalDate fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }
}