package com.socialcloud.ms_usuarios.service;

import com.socialcloud.ms_usuarios.dto.UsuarioRequest;
import com.socialcloud.ms_usuarios.model.Usuario;
import com.socialcloud.ms_usuarios.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;

    public Usuario crear(UsuarioRequest request) {
        if (repository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("El email ya existe");
        }

        Usuario usuario = Usuario.builder()
                .nombre(request.getNombre())
                .email(request.getEmail())
                .bio(request.getBio())
                .creadoEn(LocalDateTime.now())
                .build();

        return repository.save(usuario);
    }

    public List<Usuario> listar() {
        return repository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public Usuario actualizar(Long id, UsuarioRequest request) {
        Usuario usuario = buscarPorId(id);

        usuario.setNombre(request.getNombre());
        usuario.setEmail(request.getEmail());
        usuario.setBio(request.getBio());

        return repository.save(usuario);
    }

    public void eliminar(Long id) {
        Usuario usuario = buscarPorId(id);
        repository.delete(usuario);
    }
}