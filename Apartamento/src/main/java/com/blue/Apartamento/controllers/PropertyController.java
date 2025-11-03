package com.blue.Apartamento.controllers;

import com.blue.Apartamento.models.PropertyModels;
import com.blue.Apartamento.services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/properties")
@CrossOrigin(origins = "*")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    // Obtener todas las propiedades
    @GetMapping
    public ResponseEntity<List<PropertyModels>> getAllProperties() {
        List<PropertyModels> properties = propertyService.getAllProperties();
        return ResponseEntity.ok(properties);
    }

    // Obtener propiedad por ID
    @GetMapping("/{id}")
    public ResponseEntity<PropertyModels> getPropertyById(@PathVariable Long id) {
        Optional<PropertyModels> property = propertyService.getPropertyById(id);
        return property.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear nueva propiedad con validaciones básicas
    @PostMapping
    public ResponseEntity<?> createProperty(@RequestBody PropertyModels property) {
        String error = validarProperty(property);
        if (!error.isEmpty()) {
            return ResponseEntity.badRequest().body(error);
        }
        PropertyModels newProperty = propertyService.saveProperty(property);
        return ResponseEntity.ok(newProperty);
    }

    // Actualizar propiedad existente con validaciones
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProperty(@PathVariable Long id, @RequestBody PropertyModels propertyDetails) {
        Optional<PropertyModels> existingProperty = propertyService.getPropertyById(id);
        if (existingProperty.isPresent()) {
            String error = validarProperty(propertyDetails);
            if (!error.isEmpty()) {
                return ResponseEntity.badRequest().body(error);
            }

            PropertyModels property = existingProperty.get();
            property.setTitulo(propertyDetails.getTitulo());
            property.setDescripcion(propertyDetails.getDescripcion());
            property.setDireccion(propertyDetails.getDireccion());
            property.setCiudad(propertyDetails.getCiudad());
            property.setCodigoPostal(propertyDetails.getCodigoPostal());
            property.setPais(propertyDetails.getPais());
            property.setPrecioNoche(propertyDetails.getPrecioNoche());
            property.setEstado(propertyDetails.getEstado());
            property.setCapacidadPersonas(propertyDetails.getCapacidadPersonas());
            property.setNumeroHabitaciones(propertyDetails.getNumeroHabitaciones());
            property.setNumeroBanos(propertyDetails.getNumeroBanos());
            property.setMetrosCuadrados(propertyDetails.getMetrosCuadrados());
            property.setComodidades(propertyDetails.getComodidades());
            property.setNormasCasa(propertyDetails.getNormasCasa());
            property.setFechaActualizacion(java.time.LocalDateTime.now());

            PropertyModels updatedProperty = propertyService.saveProperty(property);
            return ResponseEntity.ok(updatedProperty);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar propiedad
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long id) {
        propertyService.deleteProperty(id);
        return ResponseEntity.noContent().build();
    }

    // Filtros
    @GetMapping("/propietario/{idPropietario}")
    public ResponseEntity<List<PropertyModels>> getPropertiesByPropietario(@PathVariable Long idPropietario) {
        List<PropertyModels> properties = propertyService.getPropertiesByPropietario(idPropietario);
        return ResponseEntity.ok(properties);
    }

    @GetMapping("/ciudad/{ciudad}")
    public ResponseEntity<List<PropertyModels>> getPropertiesByCiudad(@PathVariable String ciudad) {
        List<PropertyModels> properties = propertyService.getPropertiesByCiudad(ciudad);
        return ResponseEntity.ok(properties);
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<PropertyModels>> getPropertiesByEstado(@PathVariable PropertyModels.EstadoPropiedad estado) {
        List<PropertyModels> properties = propertyService.getPropertiesByEstado(estado);
        return ResponseEntity.ok(properties);
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<PropertyModels>> getPropertiesByTipo(@PathVariable PropertyModels.TipoPropiedad tipo) {
        List<PropertyModels> properties = propertyService.getPropertiesByTipo(tipo);
        return ResponseEntity.ok(properties);
    }

    // 🔎 Comprobar disponibilidad
    @GetMapping("/{id}/disponibilidad")
    public ResponseEntity<?> comprobarDisponibilidad(@PathVariable Long id) {
        boolean disponible = propertyService.comprobarDisponibilidad(id);
        String mensaje = disponible
                ? "La propiedad está disponible."
                : "La propiedad no está disponible.";
        return ResponseEntity.ok(java.util.Map.of(
                "idPropiedad", id,
                "disponible", disponible,
                "mensaje", mensaje
        ));
    }

    // ✅ Validaciones básicas de propiedad
    private String validarProperty(PropertyModels property) {
        if (property.getTitulo() == null || property.getTitulo().isEmpty())
            return "El título es obligatorio.";
        if (property.getDescripcion() == null || property.getDescripcion().isEmpty())
            return "La descripción es obligatoria.";
        if (property.getDireccion() == null || property.getDireccion().isEmpty())
            return "La dirección es obligatoria.";
        if (property.getCiudad() == null || property.getCiudad().isEmpty())
            return "La ciudad es obligatoria.";
        if (property.getPais() == null || property.getPais().isEmpty())
            return "El país es obligatorio.";
        if (property.getPrecioNoche() == null || property.getPrecioNoche().compareTo(BigDecimal.ZERO) < 0)
            return "El precio por noche debe ser mayor o igual a cero.";
        if (property.getCapacidadPersonas() <= 0)
            return "La capacidad de personas debe ser mayor a cero.";
        if (property.getNumeroHabitaciones() < 0)
            return "El número de habitaciones no puede ser negativo.";
        if (property.getNumeroBanos() < 0)
            return "El número de baños no puede ser negativo.";
        if (property.getMetrosCuadrados() < 0)
            return "Los metros cuadrados no pueden ser negativos.";
        if (property.getEstado() == null)
            return "El estado de la propiedad es obligatorio.";
        if (property.getTipo() == null)
            return "El tipo de propiedad es obligatorio.";

        return ""; // todo correcto
    }
}