package com.linkedin.apilinkedin.controller;

import com.linkedin.apilinkedin.entities.Usuario;
import com.linkedin.apilinkedin.exceptions.UsuarioNotFoundException;
import com.linkedin.apilinkedin.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping(value = "api/v1/usuario")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario criarUsuario(@RequestBody Usuario usuario){
        return usuarioService.postUsuario(usuario);
    }

    @GetMapping(name = "/{id}")
    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable Long id) throws UsuarioNotFoundException{
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorId(id).orElseThrow(() ->
                new UsuarioNotFoundException("Usuario não Encontrado !!!")));
    }

    @GetMapping
    public List<Usuario> buscarUsuarios(){
        return usuarioService.buscarUsuarios();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> deletarUsuario(@PathVariable Long id) {
        try {
            usuarioService.deletarUsuario(id);
        } catch (Exception e){
            try {
                new FileWriter(new File("log.txt")).write(e.getMessage());
            } catch (IOException ex){
                System.out.println("Algo deu errado: " + ex.getMessage());
            }
        }
        return ResponseEntity.ok().build();
    }

}
