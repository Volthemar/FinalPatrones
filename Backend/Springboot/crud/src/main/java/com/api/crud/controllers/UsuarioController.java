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
import org.springframework.http.HttpStatus;
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
    private UsuarioService usuarioService;
    @Autowired
    IEmailService emailService;

    @PostMapping("/postUsuario")
    public UsuarioModel postGuardarUsuario(@RequestBody UsuarioModel usuario) {
        return this.usuarioService.guardarUsuario(usuario);
    }

    @GetMapping("/{id}")
    public Optional<UsuarioModel> getUsuariosPorId(@PathVariable Long id) {
        return this.usuarioService.getPorId(id);
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<UsuarioModel> actualizarEstado(@PathVariable("id") long id,
            @RequestParam("estado") boolean estado) {
        Optional<UsuarioModel> usuario = usuarioService.actualizarEstado(id, estado);
        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/estado-false")
    public ResponseEntity<List<UsuarioModel>> getUsuariosConEstadoFalse() {
        List<UsuarioModel> usuarios = usuarioService.getUsuariosPorEstado(false);
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(usuarios);
        }
    }

    @GetMapping("/getUsuarios")
    public ResponseEntity<List<UsuarioModel>> getUsuarios() {
        List<UsuarioModel> usuariosActivos = usuarioService.getUsuariosPorActivo(true);
        if (usuariosActivos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(usuariosActivos);
        }
    }

    @PatchMapping("/{id}/bloquear")
    public ResponseEntity<String> bloquearUsuario(@PathVariable Long id) {
        UsuarioModel usuarioActualizado = usuarioService.updateEstado(id, false);
        if (usuarioActualizado != null) {
            return ResponseEntity.ok("Usuario con ID " + id + " fue bloqueado exitosamente.");
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/desbloquear")
    public ResponseEntity<String> desbloquearUsuario(@PathVariable Long id) {
        UsuarioModel usuarioActualizado = usuarioService.updateEstado(id, true);
        if (usuarioActualizado != null) {
            return ResponseEntity.ok("Usuario con ID " + id + " fue desbloqueado exitosamente.");
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/activar")
    public ResponseEntity<String> activarUsuario(@PathVariable Long id) {
        UsuarioModel usuarioActualizado = usuarioService.updateActivo(id, true);
        if (usuarioActualizado != null) {
            return ResponseEntity.ok("Usuario con ID " + id + " fue activado exitosamente.");
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/desactivar")
    public ResponseEntity<String> desactivarUsuario(@PathVariable Long id) {
        UsuarioModel usuarioActualizado = usuarioService.updateActivo(id, false);
        if (usuarioActualizado != null) {
            return ResponseEntity.ok("Usuario con ID " + id + " fue desactivado exitosamente.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario con ID " + id + " no fue encontrado.");
    }

}
