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
        if (registrarComentarioDTO ==null){
            this.estadoComentario = EstadoComentario.PENDIENTE;
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

}
