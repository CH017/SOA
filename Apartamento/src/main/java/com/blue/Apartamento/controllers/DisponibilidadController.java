package com.blue.Apartamento.controllers;

import com.blue.Apartamento.models.DisponibilidadModel;
import com.blue.Apartamento.services.DisponibilidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/disponibilidad")
@CrossOrigin(origins = "*")
public class DisponibilidadController {

    @Autowired
    private DisponibilidadService disponibilidadService;

    // 🔹 Obtener todas las disponibilidades
    @GetMapping
    public ResponseEntity<List<DisponibilidadModel>> getAllDisponibilidades() {
        List<DisponibilidadModel> disponibilidades = disponibilidadService.getAllDisponibilidades();
        return ResponseEntity.ok(disponibilidades);
    }

    // 🔹 Obtener disponibilidad por ID
    @GetMapping("/{id}")
    public ResponseEntity<DisponibilidadModel> getDisponibilidadById(@PathVariable Long id) {
        Optional<DisponibilidadModel> disponibilidad = disponibilidadService.getDisponibilidadById(id);
        return disponibilidad.map(ResponseEntity::ok)
                             .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 🔹 Crear nueva disponibilidad con validaciones
    @PostMapping
    public ResponseEntity<?> createDisponibilidad(@RequestBody DisponibilidadModel disponibilidad) {
        String error = validarDisponibilidad(disponibilidad);
        if (!error.isEmpty()) {
            return ResponseEntity.badRequest().body(error);
        }
        DisponibilidadModel newDisponibilidad = disponibilidadService.saveDisponibilidad(disponibilidad);
        return ResponseEntity.ok(newDisponibilidad);
    }

    // 🔹 Actualizar disponibilidad existente con validaciones
    @PutMapping("/{id}")
    public ResponseEntity<?> updateDisponibilidad(@PathVariable Long id, @RequestBody DisponibilidadModel disponibilidadDetails) {
        Optional<DisponibilidadModel> disponibilidadOptional = disponibilidadService.getDisponibilidadById(id);
        if (disponibilidadOptional.isPresent()) {
            String error = validarDisponibilidad(disponibilidadDetails);
            if (!error.isEmpty()) {
                return ResponseEntity.badRequest().body(error);
            }

            DisponibilidadModel disponibilidad = disponibilidadOptional.get();
            disponibilidad.setPropiedad(disponibilidadDetails.getPropiedad());
            disponibilidad.setFecha(disponibilidadDetails.getFecha());
            disponibilidad.setDisponible(disponibilidadDetails.isDisponible());
            disponibilidad.setPrecioEspecial(disponibilidadDetails.getPrecioEspecial());

            DisponibilidadModel updated = disponibilidadService.saveDisponibilidad(disponibilidad);
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 🔹 Eliminar disponibilidad
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDisponibilidad(@PathVariable Long id) {
        Optional<DisponibilidadModel> disponibilidad = disponibilidadService.getDisponibilidadById(id);
        if (disponibilidad.isPresent()) {
            disponibilidadService.deleteDisponibilidad(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 🔹 Obtener disponibilidades por propiedad
    @GetMapping("/propiedad/{idPropiedad}")
    public ResponseEntity<List<DisponibilidadModel>> getDisponibilidadesByPropiedad(@PathVariable Long idPropiedad) {
        List<DisponibilidadModel> disponibilidades = disponibilidadService.getDisponibilidadesByPropiedad(idPropiedad);
        return ResponseEntity.ok(disponibilidades);
    }

    // 🔹 Obtener disponibilidad específica por propiedad y fecha
    @GetMapping("/propiedad/{idPropiedad}/fecha/{fecha}")
    public ResponseEntity<?> getDisponibilidadByPropiedadAndFecha(@PathVariable Long idPropiedad,
                                                                  @PathVariable String fecha) {
        try {
            LocalDate localDate = LocalDate.parse(fecha);
            DisponibilidadModel disponibilidad = disponibilidadService.getDisponibilidadByPropiedadAndFecha(idPropiedad, localDate);
            if (disponibilidad != null) {
                return ResponseEntity.ok(disponibilidad);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Formato de fecha inválido. Use yyyy-MM-dd.");
        }
    }

    // 🔹 Obtener solo fechas disponibles de una propiedad
    @GetMapping("/propiedad/{idPropiedad}/disponibles")
    public ResponseEntity<List<DisponibilidadModel>> getFechasDisponibles(@PathVariable Long idPropiedad) {
        List<DisponibilidadModel> fechasDisponibles = disponibilidadService.getFechasDisponiblesByPropiedad(idPropiedad);
        return ResponseEntity.ok(fechasDisponibles);
    }

    // 🔹 Obtener solo fechas no disponibles de una propiedad
    @GetMapping("/propiedad/{idPropiedad}/no-disponibles")
    public ResponseEntity<List<DisponibilidadModel>> getFechasNoDisponibles(@PathVariable Long idPropiedad) {
        List<DisponibilidadModel> fechasNoDisponibles = disponibilidadService.getFechasNoDisponiblesByPropiedad(idPropiedad);
        return ResponseEntity.ok(fechasNoDisponibles);
    }

    // ✅ Validaciones básicas de disponibilidad
    private String validarDisponibilidad(DisponibilidadModel disponibilidad) {
        if (disponibilidad.getPropiedad() == null) return "La propiedad es obligatoria.";
        if (disponibilidad.getFecha() == null) return "La fecha es obligatoria.";
        if (disponibilidad.getPrecioEspecial() != null && disponibilidad.getPrecioEspecial().compareTo(java.math.BigDecimal.ZERO) < 0)
            return "El precio especial no puede ser negativo.";
        return ""; // todo correcto
    }
}
