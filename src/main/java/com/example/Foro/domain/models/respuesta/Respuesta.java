package com.example.Foro.domain.models.respuesta;

import com.example.Foro.DTO.respuesta.ActualizarRespuestaDTO;
import com.example.Foro.DTO.respuesta.RegistrarRespuestaDTO;
import com.example.Foro.domain.models.comentario.Comentario;
import com.example.Foro.domain.models.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity(name = "Respuestas")
@Table(name = "Respuesta")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "idRespuesta")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRespuesta;

    @Column(nullable = false)
    private String contenido;

    @Column(nullable = false)
    private LocalDate fecha = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "comentario_id", nullable = false)
    private Comentario comentario;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public Respuesta(RegistrarRespuestaDTO datos) {
        this.contenido = datos.contenido();
        this.fecha = datos.fecha();
    }

    public void updateRespuesta(ActualizarRespuestaDTO datos){
        if (datos.contenido() != null){
            this.contenido = datos.contenido();
        }
        if (datos.fecha() != null){
            this.fecha = datos.fecha();
        }
    }

    public Long getIdRespuesta() {
        return idRespuesta;
    }

    public void setIdRespuesta(Long idRespuesta) {
        this.idRespuesta = idRespuesta;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Comentario getComentario() {
        return comentario;
    }

    public void setComentario(Comentario comentario) {
        this.comentario = comentario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
