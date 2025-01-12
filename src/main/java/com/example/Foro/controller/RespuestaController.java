package com.example.Foro.controller;

import com.example.Foro.DTO.respuesta.RegistrarRespuestaDTO;
import com.example.Foro.DTO.respuesta.RespuestaDTO;
import com.example.Foro.domain.models.comentario.Comentario;
import com.example.Foro.domain.models.respuesta.Respuesta;
import com.example.Foro.domain.models.usuario.Usuario;
import com.example.Foro.domain.repository.ComentarioRepository;
import com.example.Foro.domain.repository.RespuestaRepository;
import com.example.Foro.domain.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/respuestas")
@SecurityRequirement(name = "bearer-key")
public class RespuestaController {

    @Autowired
    private RespuestaRepository respuestaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ComentarioRepository comentarioRepository;

    @GetMapping
    public ResponseEntity<Page<RespuestaDTO>> getAllRespuestas(Pageable pageable){
        return ResponseEntity.ok(respuestaRepository.findAll(pageable).map(RespuestaDTO::new));
    }

    @PostMapping
    public ResponseEntity<RespuestaDTO> postRespuesta(@RequestBody @Valid RegistrarRespuestaDTO datos, UriComponentsBuilder uriBuilder){
        Usuario usuario = usuarioRepository.getReferenceById(datos.usuarioId());
        Comentario comentario = comentarioRepository.getReferenceById(datos.comentarioId());

        Respuesta resp = new Respuesta(datos);
        resp.setUsuario(usuario);
        resp.setComentario(comentario);

        Respuesta respuestaGuardada = respuestaRepository.save(resp);
        URI url = uriBuilder.path("/respuestas/{id}").buildAndExpand(respuestaGuardada.getIdRespuesta()).toUri();

        return ResponseEntity.created(url).body(new RespuestaDTO(respuestaGuardada));

    }
}
