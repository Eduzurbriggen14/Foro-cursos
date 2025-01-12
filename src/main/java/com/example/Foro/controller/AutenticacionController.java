package com.example.Foro.controller;

import com.example.Foro.DTO.usuario.AutenticacionUsuarioDTO;
import com.example.Foro.domain.models.usuario.Usuario;
import com.example.Foro.insfrastructure.security.JwtTokenDTO;
import com.example.Foro.insfrastructure.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<JwtTokenDTO> autenticarUsusario(@RequestBody @Valid AutenticacionUsuarioDTO autenticacionUsuarioDTO){

        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(autenticacionUsuarioDTO.usuario(),autenticacionUsuarioDTO.passw());
        var usuarioAutenticado = authenticationManager.authenticate(authenticationToken).getPrincipal();

        var jwtToken = tokenService.generateToken((Usuario) authenticationToken);
        return ResponseEntity.ok(new JwtTokenDTO(jwtToken));



    }
}
