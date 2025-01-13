package com.example.Foro.service.validations.posteo;

import com.example.Foro.DTO.comentario.RegistrarComentarioDTO;
import com.example.Foro.domain.repository.ComentarioRepository;
import com.example.Foro.service.validations.ValidarComentario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadComentariosDuplicados implements ValidarComentario {

    @Autowired
    private ComentarioRepository comentarioRepository;


    @Override
    public void validar(RegistrarComentarioDTO datos) {
        System.out.println("Validando comentarios duplicados");
    }
}
