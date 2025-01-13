package com.example.Foro.domain.models.comentario;


import com.example.Foro.DTO.comentario.ActualizarComentarioDTO;
import com.example.Foro.DTO.comentario.RegistrarComentarioDTO;
import com.example.Foro.domain.models.curso.Curso;
import com.example.Foro.domain.models.estado.EstadoComentario;
import com.example.Foro.domain.models.usuario.Usuario;
import com.example.Foro.domain.models.respuesta.Respuesta;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "Comentarios")
@Table(name = "Comentario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idComentario")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idComentario;

    private String titulo;
    private String contenido;
    @Enumerated(EnumType.STRING)
    private EstadoComentario estadoComentario;

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    private LocalDate fecha = LocalDate.now();

    @OneToMany(mappedBy = "comentario", cascade = CascadeType.ALL)
    private List<Respuesta> respuestas;

    public Comentario(RegistrarComentarioDTO registrarComentarioDTO){
        this.titulo = registrarComentarioDTO.titulo();
        this.contenido = registrarComentarioDTO.contenido();
        if (registrarComentarioDTO.estado() ==null){
            this.estadoComentario = EstadoComentario.PENDIENTE;
        }
        else{
            this.estadoComentario = registrarComentarioDTO.estado();
        }
    }

    public void actualizarComentario(ActualizarComentarioDTO datos){
        if (datos.titulo() !=null){
            this.titulo = datos.titulo();
        }
        if (datos.contenido() != null){
            this.contenido = datos.contenido();
        }
        if (datos.estado()!=null){
            this.estadoComentario = datos.estado();
        }else{
            this.estadoComentario = EstadoComentario.PENDIENTE;
        }
    }

    public Long getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(Long idComentario) {
        this.idComentario = idComentario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public EstadoComentario getEstadoComentario() {
        return estadoComentario;
    }

    public void setEstadoComentario(EstadoComentario estadoComentario) {
        this.estadoComentario = estadoComentario;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public List<Respuesta> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<Respuesta> respuestas) {
        this.respuestas = respuestas;
    }
}
