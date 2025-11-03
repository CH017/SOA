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
@CrossOrigin(origins = "*")
public class UsersController {

    @Autowired
    private UsersService usersService;

    // ------------------ OBTENER USUARIOS ------------------
    @GetMapping
    public List<UsersModel> getAllUsuarios() {
        return usersService.listarUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsersModel> getUsuarioById(@PathVariable Long id) {
        Optional<UsersModel> usuario = usersService.obtenerPorId(id);
        return usuario.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/tipo/{tipo}")
    public List<UsersModel> getUsersByTipo(@PathVariable TipoUsuario tipo) {
        return usersService.getUsersByTipo(tipo);
    }

    // ------------------ CREAR USUARIO ------------------
    @PostMapping
    public ResponseEntity<?> createUsuario(@RequestBody UsersModel usuario) {
        String error = validarUsuario(usuario);
        if (!error.isEmpty()) {
            return ResponseEntity.badRequest().body(error);
        }

        UsersModel nuevoUsuario = usersService.guardarUsuario(usuario);
        return ResponseEntity.ok(nuevoUsuario);
    }

    // ------------------ ACTUALIZAR USUARIO ------------------
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUsuario(@PathVariable Long id, @RequestBody UsersModel usuarioDetails) {
        Optional<UsersModel> usuarioOptional = usersService.obtenerPorId(id);
        if (usuarioOptional.isPresent()) {
            String error = validarUsuario(usuarioDetails);
            if (!error.isEmpty()) {
                return ResponseEntity.badRequest().body(error);
            }

            UsersModel usuarioToUpdate = usuarioOptional.get();
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

    // ------------------ ELIMINAR USUARIO ------------------
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

    // ------------------ VALIDACIONES ------------------
    private String validarUsuario(UsersModel usuario) {
        if (usuario.getNombre() == null || usuario.getNombre().trim().isEmpty())
            return "El nombre es obligatorio.";
        if (usuario.getApellido() == null || usuario.getApellido().trim().isEmpty())
            return "El apellido es obligatorio.";
        if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty())
            return "El email es obligatorio.";
        if (!usuario.getEmail().matches("^[\\w.-]+@[\\w.-]+\\.\\w+$"))
            return "El email no tiene un formato válido.";
        if (usuario.getContrasenaHash() == null || usuario.getContrasenaHash().trim().isEmpty())
            return "La contraseña es obligatoria.";
        if (usuario.getTipo() == null)
            return "El tipo de usuario es obligatorio.";

        return ""; // Todo correcto
    }
}
