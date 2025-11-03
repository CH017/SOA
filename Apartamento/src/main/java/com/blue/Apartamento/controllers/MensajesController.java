package com.blue.Apartamento.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.blue.Apartamento.models.MensajesModel;
import com.blue.Apartamento.services.MensajesService;

@RestController
@RequestMapping("/api/mensajes")
@CrossOrigin(origins = "*")
public class MensajesController {

    @Autowired
    private MensajesService mensajesService;

    // 🔹 Obtener todos los mensajes
    @GetMapping
    public ResponseEntity<List<MensajesModel>> getAllMensajes() {
        List<MensajesModel> mensajes = mensajesService.getAllMensajes();
        return ResponseEntity.ok(mensajes);
    }

    // 🔹 Obtener mensaje por ID
    @GetMapping("/{id}")
    public ResponseEntity<MensajesModel> getMensajeById(@PathVariable Long id) {
        Optional<MensajesModel> mensaje = mensajesService.getMensajeById(id);
        return mensaje.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 🔹 Crear o enviar un nuevo mensaje con validaciones
    @PostMapping
    public ResponseEntity<?> createMensaje(@RequestBody MensajesModel mensaje) {
        String error = validarMensaje(mensaje);
        if (!error.isEmpty()) {
            return ResponseEntity.badRequest().body(error);
        }
        MensajesModel newMensaje = mensajesService.saveMensaje(mensaje);
        return ResponseEntity.ok(newMensaje);
    }

    // 🔹 Actualizar un mensaje existente con validaciones
    @PutMapping("/{id}")
    public ResponseEntity<?> updateMensaje(@PathVariable Long id, @RequestBody MensajesModel mensajeDetails) {
        Optional<MensajesModel> mensajeOptional = mensajesService.getMensajeById(id);
        if (mensajeOptional.isPresent()) {
            String error = validarMensaje(mensajeDetails);
            if (!error.isEmpty()) {
                return ResponseEntity.badRequest().body(error);
            }

            MensajesModel mensajeToUpdate = mensajeOptional.get();
            mensajeToUpdate.setAsunto(mensajeDetails.getAsunto());
            mensajeToUpdate.setContenido(mensajeDetails.getContenido());
            mensajeToUpdate.setLeido(mensajeDetails.isLeido());

            MensajesModel updatedMensaje = mensajesService.saveMensaje(mensajeToUpdate);
            return ResponseEntity.ok(updatedMensaje);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 🔹 Eliminar un mensaje
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMensaje(@PathVariable Long id) {
        Optional<MensajesModel> mensaje = mensajesService.getMensajeById(id);
        if (mensaje.isPresent()) {
            mensajesService.deleteMensaje(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 🔹 Marcar un mensaje como leído
    @PatchMapping("/{id}/leido")
    public ResponseEntity<MensajesModel> marcarComoLeido(@PathVariable Long id) {
        MensajesModel mensajeLeido = mensajesService.marcarComoLeido(id);
        if (mensajeLeido != null) {
            return ResponseEntity.ok(mensajeLeido);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // ✅ Validaciones básicas de mensaje
    private String validarMensaje(MensajesModel mensaje) {
        if (mensaje.getAsunto() == null || mensaje.getAsunto().isEmpty())
            return "El asunto es obligatorio.";
        if (mensaje.getContenido() == null || mensaje.getContenido().isEmpty())
            return "El contenido es obligatorio.";
        return ""; // todo correcto
    }
}
