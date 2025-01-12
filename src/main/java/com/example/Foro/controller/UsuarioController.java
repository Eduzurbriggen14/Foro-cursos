package com.example.Foro.controller;


import com.example.Foro.DTO.usuario.ActualizarUsuarioDTO;
import com.example.Foro.DTO.usuario.RegistrarUsuarioDTO;
import com.example.Foro.DTO.usuario.UsuarioDTO;
import com.example.Foro.DTO.usuarioPerfil.UsuarioPerfilDTO;
import com.example.Foro.domain.models.usuario.Usuario;
import com.example.Foro.domain.models.usuario.UsuarioPerfil;
import com.example.Foro.domain.repository.UsuarioPerfilRepository;
import com.example.Foro.domain.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuario")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {


    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioPerfilRepository usuarioPerfilRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<UsuarioDTO> registarUsuario(@RequestBody @Valid RegistrarUsuarioDTO datos, UriComponentsBuilder uri){
        Usuario usuario = new Usuario();
        usuario.setUsuario(datos.usuario());
        usuario.setPassw(datos.passw());
        usuario.setNombreUsuario(datos.nombreUsuario());
        usuario.setActivo(true);

        usuario = usuarioRepository.save(usuario);

        URI url = uri.path("/usuario/{id}").buildAndExpand(usuario.getIdUsuario()).toUri();

        List<UsuarioPerfil> usuarioPerfil = usuarioRepository.findPerfilesByUsuarioId(usuario.getIdUsuario());

        List<UsuarioPerfilDTO> usuarioPerfilDTO = usuarioPerfil.stream()
                .map(p -> new UsuarioPerfilDTO(p))
                .collect(Collectors.toList());

        var usuarioDTO = new UsuarioDTO(usuario.getIdUsuario(),
                usuario.getUsuario(),
                usuario.getNombreUsuario(),
                usuario.getUsuarioPerfiles().stream()
                        .map(UsuarioPerfil::toPerfilDTO)
                        .toList(),
                usuario.isActivo());

        return ResponseEntity.created(url).body(usuarioDTO);
    }

    @GetMapping
    public ResponseEntity<Page<UsuarioDTO>> getUsuarios(Pageable pageable){
        return ResponseEntity.ok(usuarioRepository.findAll(pageable).map(UsuarioDTO::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<UsuarioDTO> updateUsuario(@RequestBody @Valid ActualizarUsuarioDTO data){
        Usuario usuario = usuarioPerfilRepository.getReferenceById(data.id()).getUsuario();

        usuario.setUsuario(data.usuario());
        usuario.setPassw(data.passw());
        usuario.setNombreUsuario(data.nombreUsuario());

        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        var usuarioDTO = new UsuarioDTO(usuarioGuardado);
        return ResponseEntity.ok().body(usuarioDTO);

    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getUsuarioPorId(@PathVariable Long id){
        Usuario us = usuarioRepository.getReferenceById(id);

        return ResponseEntity.ok().body(new UsuarioDTO((us)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id){
        usuarioRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
