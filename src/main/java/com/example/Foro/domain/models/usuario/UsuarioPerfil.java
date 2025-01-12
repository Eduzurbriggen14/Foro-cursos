package com.example.Foro.domain.models.usuario;

import com.example.Foro.DTO.usuario.PerfilDTO;
import com.example.Foro.DTO.usuarioPerfil.UsuarioPerfilDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "UsuarioPerfil")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idUsuarioPerfil")
public class UsuarioPerfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuarioPerfil;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    @Column(name = "perfil", nullable = false)
    private Perfil perfil;

    private boolean estadoPerfil;
    
    public UsuarioPerfil(UsuarioPerfilDTO datos){
        this.usuario = datos.usuario();
        this.perfil = datos.perfil();
        this.estadoPerfil = datos.estadoPerfil();
    }

    public PerfilDTO toPerfilDTO() {
        return new PerfilDTO(this.perfil, this.estadoPerfil);
    }
}
