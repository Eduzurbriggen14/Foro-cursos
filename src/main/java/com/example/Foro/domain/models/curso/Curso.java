package com.example.Foro.domain.models.curso;


import com.example.Foro.DTO.curso.CursoDTO;
import com.example.Foro.DTO.curso.RegistrarCursoDTO;
import com.example.Foro.domain.models.comentario.Comentario;
import com.example.Foro.domain.models.usuarioCurso.UsuarioCurso;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "Cursos")
@Table(name = "Curso")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idCurso")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCurso;
    private String nombreCurso;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoriaCurso categoria;
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
    private List<UsuarioCurso> usuarioCursos;
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
    private List<Comentario> comentarios;

    public Curso(RegistrarCursoDTO datos) {
        this.nombreCurso = datos.nombreCurso();
        this.categoria = datos.categoriaCurso();
    }

    public void actualizarCurso(CursoDTO datos){
        if (datos.nombreCurso() != null){
            this.nombreCurso = datos.nombreCurso();
        }
        if (datos.categoriaCurso() !=null){
            this.categoria = datos.categoriaCurso();
        }
    }

}
