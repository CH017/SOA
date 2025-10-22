package com.blue.Apartamento.controllers;

import com.blue.Apartamento.models.UsersModel;
import com.blue.Apartamento.models.UsersModel.TipoUsuario;
import com.blue.Apartamento.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsersController {

    @Autowired
    private UsersService usersService;

    // Obtener todos los usuarios
    @GetMapping
    public List<UsersModel> getAllUsuarios() {
        return usersService.listarUsuarios();
    }

    // Obtener un usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<UsersModel> getUsuarioById(@PathVariable Long id) {
        Optional<UsersModel> usuario = usersService.obtenerPorId(id);
        return usuario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 🔹 Obtener usuarios por tipo
    @GetMapping("/tipo/{tipo}")
    public List<UsersModel> getUsersByTipo(@PathVariable TipoUsuario tipo) {
        return usersService.getUsersByTipo(tipo);
    }
    
    // Crear un nuevo usuario
    @PostMapping
    public UsersModel createUsuario(@RequestBody UsersModel usuario) {
        return usersService.guardarUsuario(usuario);
    }

    // Actualizar un usuario existente
    @PutMapping("/{id}")
    public ResponseEntity<UsersModel> updateUsuario(@PathVariable Long id, @RequestBody UsersModel usuarioDetails) {
        Optional<UsersModel> usuarioOptional = usersService.obtenerPorId(id);
        if (usuarioOptional.isPresent()) {
            UsersModel usuarioToUpdate = usuarioOptional.get();

            // Actualizar campos principales
            usuarioToUpdate.setNombre(usuarioDetails.getNombre());
            usuarioToUpdate.setApellido(usuarioDetails.getApellido());
            usuarioToUpdate.setEmail(usuarioDetails.getEmail());
            usuarioToUpdate.setTelefono(usuarioDetails.getTelefono());
            usuarioToUpdate.setDireccion(usuarioDetails.getDireccion());
            usuarioToUpdate.setEstado(usuarioDetails.getEstado());
            usuarioToUpdate.setTipo(usuarioDetails.getTipo());
            usuarioToUpdate.setContrasenaHash(usuarioDetails.getContrasenaHash());

            UsersModel updatedUsuario = usersService.guardarUsuario(usuarioToUpdate);
            return ResponseEntity.ok(updatedUsuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        Optional<UsersModel> usuario = usersService.obtenerPorId(id);
        if (usuario.isPresent()) {
            usersService.eliminarUsuario(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

   
}