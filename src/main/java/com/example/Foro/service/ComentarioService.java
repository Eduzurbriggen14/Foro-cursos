package com.example.Foro.service;

import com.example.Foro.DTO.comentario.ActualizarComentarioDTO;
import com.example.Foro.DTO.comentario.NuevoComentarioDTO;
import com.example.Foro.DTO.comentario.RegistrarComentarioDTO;
import com.example.Foro.domain.models.comentario.Comentario;
import com.example.Foro.domain.models.curso.Curso;
import com.example.Foro.domain.models.usuario.Usuario;
import com.example.Foro.domain.repository.ComentarioRepository;
import com.example.Foro.domain.repository.CursoRepository;
import com.example.Foro.domain.repository.UsuarioRepository;
import com.example.Foro.insfrastructure.errors.SearchErrorValidationsException;
import com.example.Foro.service.validations.ValidarComentario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Optional;

public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private List<ValidarComentario> validarComentarios;


    public NuevoComentarioDTO newComentario(RegistrarComentarioDTO datos){

        Optional<Usuario> usuarioBuscado = usuarioRepository.findById(datos.usuarioId());
        if (usuarioBuscado.isEmpty()){
            throw new SearchErrorValidationsException("El usuario no es valido, no se encuenta en nuestros registros");
        }
        Optional<Curso> cursoBuscado = cursoRepository.findById(datos.cursoId());
        if (cursoBuscado.isEmpty()){
            throw new SearchErrorValidationsException("El curso no es valido, no se encuenta en nuestros registros");
        }

        validarComentarios.forEach(c->c.validar(datos));

        Usuario usuario = usuarioBuscado.get();
        Curso curso = cursoBuscado.get();
        var usuarioAutentificado = getUsuarioAutentificado();

        if (!datos.usuarioId().equals(usuarioAutentificado.getIdUsuario())){
            throw new SearchErrorValidationsException(("Error, el usuario no puede realizar comentarios"));
        }

        Comentario comentario = new Comentario(datos);
        comentario.setUsuario(usuarioAutentificado);
        comentario.setCurso(cursoBuscado.get());

        return new NuevoComentarioDTO(comentarioRepository.save(comentario));

    }

    public NuevoComentarioDTO updateComentario(ActualizarComentarioDTO datos){
        var usuarioAutentificado = getUsuarioAutentificado();
        validarUsuarioComentario(datos.id(), usuarioAutentificado.getIdUsuario());

        Comentario comentario = comentarioRepository.getReferenceById(datos.id());
        comentario.actualizarComentario(datos);
        return  new NuevoComentarioDTO(comentarioRepository.save(comentario));

    }

    public void deleteComentario(Long id){
        var usuarioAutenticado = getUsuarioAutentificado();
        validarUsuarioComentario(id, usuarioAutenticado.getIdUsuario());
        comentarioRepository.deleteById(id);
    }




    private void validarUsuarioComentario(Long idComentario, Long idUsuario ){
        Comentario comentario = comentarioRepository.getReferenceById(idComentario);
        if (comentario == null){
            throw new SearchErrorValidationsException("No existe comentario con ese ID");
        }
        if (!comentario.getUsuario().getIdUsuario().equals(idUsuario)){
            throw new SearchErrorValidationsException("El usuario no puede realizar esta operacion, No tiene permisos");
        }
    }

    private Usuario getUsuarioAutentificado(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario;
        if (auth == null || !auth.isAuthenticated()){
            throw new SearchErrorValidationsException("ERROR... El usuario no esta autentificado");
        }

        try{
            usuario = usuarioRepository.findByUsuario(auth.getName());
        }catch(RuntimeException e){
            throw new SearchErrorValidationsException("El usuario no esta registrado");
        }

        return usuario;
    }
}
