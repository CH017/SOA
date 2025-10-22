package com.blue.Apartamento.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blue.Apartamento.models.MensajesModel;
import com.blue.Apartamento.services.MensajesService;

@RestController
@RequestMapping("/api/mensajes")
public class MensajesController {

    @Autowired
    private MensajesService mensajesService;

    // 🔹 Obtener todos los mensajes
    @GetMapping
    public List<MensajesModel> getAllMensajes() {
        return mensajesService.getAllMensajes();
    }

    // 🔹 Obtener mensaje por ID
    @GetMapping("/{id}")
    public ResponseEntity<MensajesModel> getMensajeById(@PathVariable Long id) {
        Optional<MensajesModel> mensaje = mensajesService.getMensajeById(id);
        return mensaje.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 🔹 Crear o enviar un nuevo mensaje
    @PostMapping
    public MensajesModel createMensaje(@RequestBody MensajesModel mensaje) {
        return mensajesService.saveMensaje(mensaje);
    }

    // 🔹 Actualizar un mensaje existente
    @PutMapping("/{id}")
    public ResponseEntity<MensajesModel> updateMensaje(@PathVariable Long id, @RequestBody MensajesModel mensajeDetails) {
        Optional<MensajesModel> mensajeOptional = mensajesService.getMensajeById(id);
        if (mensajeOptional.isPresent()) {
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
}
