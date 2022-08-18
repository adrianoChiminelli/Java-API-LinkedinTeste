package com.linkedin.apilinkedin.services;

import com.linkedin.apilinkedin.entities.Usuario;
import com.linkedin.apilinkedin.repositories.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    IUsuarioRepository iUsuarioRepository;

    @Autowired
    public UsuarioService(IUsuarioRepository iUsuarioRepository) {
        this.iUsuarioRepository = iUsuarioRepository;
    }

    public Optional<Usuario> buscarUsuarioPorId(Long id) {
        return iUsuarioRepository.findById(id);
    }

    public Usuario postUsuario(Usuario usuario) {
        return iUsuarioRepository.save(usuario);
    }

    public List<Usuario> buscarUsuarios() {
        return iUsuarioRepository.findAll();
    }

    public void deletarUsuario(Long id) {
        iUsuarioRepository.deleteById(id);
    }
}
