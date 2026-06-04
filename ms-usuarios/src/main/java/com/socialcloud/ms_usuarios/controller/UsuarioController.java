package com.socialcloud.ms_usuarios.controller;

import com.socialcloud.ms_usuarios.dto.UsuarioRequest;
import com.socialcloud.ms_usuarios.model.Usuario;
import com.socialcloud.ms_usuarios.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    @GetMapping("/health")
    public String health() {
        return "ms-usuarios activo";
    }

    @PostMapping
    public Usuario crear(@Valid @RequestBody UsuarioRequest request) {
        return service.crear(request);
    }

    @GetMapping
    public List<Usuario> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Usuario buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Usuario actualizar(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioRequest request
    ) {
        return service.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}