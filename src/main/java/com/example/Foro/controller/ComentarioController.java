package com.example.Foro.controller;


import com.example.Foro.DTO.comentario.*;
import com.example.Foro.domain.models.comentario.Comentario;
import com.example.Foro.domain.repository.ComentarioRepository;
import com.example.Foro.service.ComentarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("comentarios")
@SecurityRequirement(name = "bearerKey")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;
    @Autowired
    private ComentarioRepository comentarioRepository;

    @GetMapping
    public ResponseEntity<Page<ListarComentariosDTO>> getAllComentarios(@PageableDefault(size = 5, sort = "fecha") Pageable pageable){
        Page<Comentario> page = comentarioRepository.findAll(pageable);
        Page<ListarComentariosDTO> listarComentariosDTOPage = page.map(ListarComentariosDTO::new);
        return ResponseEntity.ok(listarComentariosDTOPage);
    }

    @GetMapping("/comentarios/{id}")
    public ResponseEntity<ComentarioDTO> getComentarioPorId(@PathVariable Long id){
        Comentario comentario = comentarioRepository.getReferenceById(id);

        return ResponseEntity.ok(new ComentarioDTO(comentario));
    }

    @PostMapping
    public ResponseEntity<NuevoComentarioDTO> registerComentario(@RequestBody @Valid RegistrarComentarioDTO datos,
                                                                 UriComponentsBuilder uriBuilder){
        var detallesComentario = comentarioService.newComentario(datos);
        URI url = uriBuilder.path("/comentarios/{id}").buildAndExpand(detallesComentario.id()).toUri();
        return ResponseEntity.created(url).body(detallesComentario);

    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<Void> deleteComentario(@PathVariable Long id){
        comentarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity<NuevoComentarioDTO> updateComentario(@RequestBody @Valid ActualizarComentarioDTO datos){
        return ResponseEntity.ok(comentarioService.updateComentario(datos));
    }

}
