package com.example.Foro.controller;

import com.example.Foro.DTO.curso.CursoDTO;
import com.example.Foro.DTO.curso.RegistrarCursoDTO;
import com.example.Foro.domain.models.curso.Curso;
import com.example.Foro.domain.repository.CursoRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/cursos")
@SecurityRequirement(name = "bearer-key")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public ResponseEntity<Page<CursoDTO>> listarCursos(Pageable pageable){
        return ResponseEntity.ok(cursoRepository.findAll(pageable).map(CursoDTO::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoDTO> obtenerCursoPorId(@PathVariable Long id){
        Curso curso = cursoRepository.getReferenceById(id);
        return ResponseEntity.ok(new CursoDTO(curso));
    }

    @PostMapping
    public ResponseEntity<RegistrarCursoDTO> registrarCurso(@RequestBody @Valid RegistrarCursoDTO datos, UriComponentsBuilder uriComponentsBuilder){
        Curso curso = new Curso(datos);
        curso = cursoRepository.save(curso);

        URI url = uriComponentsBuilder.path("/cursos/{id}").buildAndExpand(curso.getIdCurso()).toUri();

        return ResponseEntity.created(url).body(datos);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<CursoDTO> actualizarCurso(@RequestBody CursoDTO datos){
        Curso curso = cursoRepository.getReferenceById(datos.id());
        curso.actualizarCurso(datos);
        cursoRepository.save(curso);
        return ResponseEntity.ok(new CursoDTO(curso));
    }
}
