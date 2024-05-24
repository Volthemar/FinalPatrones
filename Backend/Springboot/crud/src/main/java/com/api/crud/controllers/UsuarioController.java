package com.api.crud.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.api.crud.services.IEmailService;
import com.api.crud.services.UsuarioService;

import com.api.crud.models.UsuarioModel;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/usuario")

public class UsuarioController {
    @Autowired
    private UsuarioService userService;
    @Autowired
    IEmailService emailService;

    @PostMapping("/postUsuario")
    public UsuarioModel postGuardarUsuario(@RequestBody UsuarioModel usuario) {
        return this.userService.guardarUsuario(usuario);
    }

    @GetMapping("/{id}")
    public Optional<UsuarioModel> getUsuariosPorId(@PathVariable Long id) {
        return this.userService.getPorId(id);
    }

    @Autowired
    UsuarioService UsuarioService;

    @PutMapping("/{id}/estado")
    public ResponseEntity<UsuarioModel> actualizarEstado(@PathVariable("id") long id,
            @RequestParam("estado") boolean estado) {
        Optional<UsuarioModel> usuario = UsuarioService.actualizarEstado(id, estado);
        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/estado-false")
    public ResponseEntity<List<UsuarioModel>> getUsuariosConEstadoFalse() {
        List<UsuarioModel> usuarios = UsuarioService.getUsuariosPorEstado(false);
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(usuarios);
        }
    }

    @GetMapping("/getUsuarios")
    public ResponseEntity<List<UsuarioModel>> getUsuarios() {
        List<UsuarioModel> usuariosActivos = UsuarioService.getUsuariosPorActivo(true);
        if (usuariosActivos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(usuariosActivos);
        }
    }

    @PatchMapping("/{id}/bloquear")
    public ResponseEntity<UsuarioModel> bloquearUsuario(@PathVariable Long id) {
        UsuarioModel usuarioActualizado = UsuarioService.updateEstado(id, false);
        if (usuarioActualizado != null) {
            return ResponseEntity.ok(usuarioActualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/desbloquear")
    public ResponseEntity<UsuarioModel> desbloquearUsuario(@PathVariable Long id) {
        UsuarioModel usuarioActualizado = UsuarioService.updateEstado(id, true);
        if (usuarioActualizado != null) {
            return ResponseEntity.ok(usuarioActualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/desactivar")
    public ResponseEntity<UsuarioModel> desactivarUsuario(@PathVariable Long id) {
        UsuarioModel usuarioActualizado = UsuarioService.updateActivo(id, false);
        if (usuarioActualizado != null) {
            return ResponseEntity.ok(usuarioActualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/activar")
    public ResponseEntity<UsuarioModel> activarUsuario(@PathVariable Long id) {
        UsuarioModel usuarioActualizado = UsuarioService.updateActivo(id, true);
        if (usuarioActualizado != null) {
            return ResponseEntity.ok(usuarioActualizado);
        }
        return ResponseEntity.notFound().build();
    }

}
