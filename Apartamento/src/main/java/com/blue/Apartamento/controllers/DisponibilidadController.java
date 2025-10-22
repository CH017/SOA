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
public class DisponibilidadController {

    @Autowired
    private DisponibilidadService disponibilidadService;

    // 🔹 Obtener todas las disponibilidades
    @GetMapping
    public List<DisponibilidadModel> getAllDisponibilidades() {
        return disponibilidadService.getAllDisponibilidades();
    }

    // 🔹 Obtener disponibilidad por ID
    @GetMapping("/{id}")
    public ResponseEntity<DisponibilidadModel> getDisponibilidadById(@PathVariable Long id) {
        Optional<DisponibilidadModel> disponibilidad = disponibilidadService.getDisponibilidadById(id);
        return disponibilidad.map(ResponseEntity::ok)
                             .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 🔹 Crear nueva disponibilidad
    @PostMapping
    public DisponibilidadModel createDisponibilidad(@RequestBody DisponibilidadModel disponibilidad) {
        return disponibilidadService.saveDisponibilidad(disponibilidad);
    }

    // 🔹 Actualizar disponibilidad existente
    @PutMapping("/{id}")
    public ResponseEntity<DisponibilidadModel> updateDisponibilidad(@PathVariable Long id, @RequestBody DisponibilidadModel disponibilidadDetails) {
        Optional<DisponibilidadModel> disponibilidadOptional = disponibilidadService.getDisponibilidadById(id);
        if (disponibilidadOptional.isPresent()) {
            DisponibilidadModel disponibilidadToUpdate = disponibilidadOptional.get();

            disponibilidadToUpdate.setPropiedad(disponibilidadDetails.getPropiedad());
            disponibilidadToUpdate.setFecha(disponibilidadDetails.getFecha());
            disponibilidadToUpdate.setDisponible(disponibilidadDetails.isDisponible());
            disponibilidadToUpdate.setPrecioEspecial(disponibilidadDetails.getPrecioEspecial());

            DisponibilidadModel updated = disponibilidadService.saveDisponibilidad(disponibilidadToUpdate);
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
    public List<DisponibilidadModel> getDisponibilidadesByPropiedad(@PathVariable Long idPropiedad) {
        return disponibilidadService.getDisponibilidadesByPropiedad(idPropiedad);
    }

    // 🔹 Obtener disponibilidad específica por propiedad y fecha
    @GetMapping("/propiedad/{idPropiedad}/fecha/{fecha}")
    public DisponibilidadModel getDisponibilidadByPropiedadAndFecha(@PathVariable Long idPropiedad,
                                                                    @PathVariable String fecha) {
        LocalDate localDate = LocalDate.parse(fecha);
        return disponibilidadService.getDisponibilidadByPropiedadAndFecha(idPropiedad, localDate);
    }

    // 🔹 Obtener solo fechas disponibles de una propiedad
    @GetMapping("/propiedad/{idPropiedad}/disponibles")
    public List<DisponibilidadModel> getFechasDisponibles(@PathVariable Long idPropiedad) {
        return disponibilidadService.getFechasDisponiblesByPropiedad(idPropiedad);
    }

    // 🔹 Obtener solo fechas no disponibles de una propiedad
    @GetMapping("/propiedad/{idPropiedad}/no-disponibles")
    public List<DisponibilidadModel> getFechasNoDisponibles(@PathVariable Long idPropiedad) {
        return disponibilidadService.getFechasNoDisponiblesByPropiedad(idPropiedad);
    }
}
