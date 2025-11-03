package com.blue.Apartamento.controllers;

import com.blue.Apartamento.models.ReseñasModel;
import com.blue.Apartamento.services.ReseñasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reseñas")
@CrossOrigin(origins = "*")
public class ReseñasController {

    @Autowired
    private ReseñasService reseñasService;

    // 🔹 Crear o actualizar reseña con validaciones
    @PostMapping
    public ResponseEntity<?> guardarReseña(@RequestBody ReseñasModel reseña) {
        String error = validarReseña(reseña);
        if (!error.isEmpty()) {
            return ResponseEntity.badRequest().body(error);
        }
        return ResponseEntity.ok(reseñasService.guardarReseña(reseña));
    }

    // 🔹 Obtener todas las reseñas
    @GetMapping
    public ResponseEntity<List<ReseñasModel>> obtenerTodas() {
        return ResponseEntity.ok(reseñasService.obtenerTodas());
    }

    // 🔹 Obtener reseña por ID
    @GetMapping("/{id}")
    public ResponseEntity<ReseñasModel> obtenerPorId(@PathVariable Long id) {
        Optional<ReseñasModel> reseña = reseñasService.obtenerPorId(id);
        return reseña.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 🔹 Eliminar reseña
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReseña(@PathVariable Long id) {
        boolean eliminado = reseñasService.eliminarReseña(id);
        return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    // 🔹 Obtener reseñas por ID de reservación
    @GetMapping("/reservacion/{idReservacion}")
    public ResponseEntity<List<ReseñasModel>> obtenerPorReservacion(@PathVariable Long idReservacion) {
        return ResponseEntity.ok(reseñasService.obtenerPorReservacion(idReservacion));
    }

    // 🔹 Obtener reseñas por ID de cliente
    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<ReseñasModel>> obtenerPorCliente(@PathVariable Long idCliente) {
        return ResponseEntity.ok(reseñasService.obtenerPorCliente(idCliente));
    }

    // 🔹 Obtener reseñas por ID de propiedad
    @GetMapping("/propiedad/{idPropiedad}")
    public ResponseEntity<List<ReseñasModel>> obtenerPorPropiedad(@PathVariable Long idPropiedad) {
        return ResponseEntity.ok(reseñasService.obtenerPorPropiedad(idPropiedad));
    }

    // ✅ Validaciones básicas de reseña
    private String validarReseña(ReseñasModel reseña) {
        if (reseña.getReservacion() == null)
            return "La reservación es obligatoria.";

        if (reseña.getCalificacionLimpieza() < 1 || reseña.getCalificacionLimpieza() > 5)
            return "La calificación de limpieza debe estar entre 1 y 5.";
        if (reseña.getCalificacionUbicacion() < 1 || reseña.getCalificacionUbicacion() > 5)
            return "La calificación de ubicación debe estar entre 1 y 5.";
        if (reseña.getCalificacionComunicacion() < 1 || reseña.getCalificacionComunicacion() > 5)
            return "La calificación de comunicación debe estar entre 1 y 5.";
        if (reseña.getCalificacionGeneral() < 1 || reseña.getCalificacionGeneral() > 5)
            return "La calificación general debe estar entre 1 y 5.";

        if (reseña.getComentario() == null || reseña.getComentario().isEmpty())
            return "El comentario es obligatorio.";

        return ""; // todo correcto
    }
}
