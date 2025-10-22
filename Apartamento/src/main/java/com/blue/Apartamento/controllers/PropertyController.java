package com.blue.Apartamento.controllers;

import com.blue.Apartamento.models.PropertyModels;
import com.blue.Apartamento.services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    // Obtener todas las propiedades
    @GetMapping
    public List<PropertyModels> getAllProperties() {
        return propertyService.getAllProperties();
    }

    // Obtener una propiedad por ID
    @GetMapping("/{id}")
    public ResponseEntity<PropertyModels> getPropertyById(@PathVariable Long id) {
        Optional<PropertyModels> property = propertyService.getPropertyById(id);
        return property.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear una nueva propiedad
    @PostMapping
    public PropertyModels createProperty(@RequestBody PropertyModels property) {
        return propertyService.saveProperty(property);
    }

    // Actualizar una propiedad existente
    @PutMapping("/{id}")
    public ResponseEntity<PropertyModels> updateProperty(@PathVariable Long id, @RequestBody PropertyModels propertyDetails) {
        Optional<PropertyModels> propertyOptional = propertyService.getPropertyById(id);
        if (propertyOptional.isPresent()) {
            PropertyModels propertyToUpdate = propertyOptional.get();

            // Actualizar los campos necesarios
            propertyToUpdate.setTitulo(propertyDetails.getTitulo());
            propertyToUpdate.setDescripcion(propertyDetails.getDescripcion());
            propertyToUpdate.setDireccion(propertyDetails.getDireccion());
            propertyToUpdate.setCiudad(propertyDetails.getCiudad());
            propertyToUpdate.setCodigoPostal(propertyDetails.getCodigoPostal());
            propertyToUpdate.setPais(propertyDetails.getPais());
            propertyToUpdate.setPrecioNoche(propertyDetails.getPrecioNoche());
            propertyToUpdate.setEstado(propertyDetails.getEstado());
            propertyToUpdate.setCapacidadPersonas(propertyDetails.getCapacidadPersonas());
            propertyToUpdate.setNumeroHabitaciones(propertyDetails.getNumeroHabitaciones());
            propertyToUpdate.setNumeroBanos(propertyDetails.getNumeroBanos());
            propertyToUpdate.setMetrosCuadrados(propertyDetails.getMetrosCuadrados());
            propertyToUpdate.setComodidades(propertyDetails.getComodidades());
            propertyToUpdate.setNormasCasa(propertyDetails.getNormasCasa());
            propertyToUpdate.setFechaActualizacion(propertyDetails.getFechaActualizacion());

            PropertyModels updatedProperty = propertyService.saveProperty(propertyToUpdate);
            return ResponseEntity.ok(updatedProperty);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar una propiedad
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long id) {
        Optional<PropertyModels> property = propertyService.getPropertyById(id);
        if (property.isPresent()) {
            propertyService.deleteProperty(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 🔎 Filtros opcionales
    @GetMapping("/propietario/{idPropietario}")
    public List<PropertyModels> getPropertiesByPropietario(@PathVariable Long idPropietario) {
        return propertyService.getPropertiesByPropietario(idPropietario);
    }

    @GetMapping("/ciudad/{ciudad}")
    public List<PropertyModels> getPropertiesByCiudad(@PathVariable String ciudad) {
        return propertyService.getPropertiesByCiudad(ciudad);
    }

    @GetMapping("/estado/{estado}")
    public List<PropertyModels> getPropertiesByEstado(@PathVariable PropertyModels.EstadoPropiedad estado) {
        return propertyService.getPropertiesByEstado(estado);
    }

    @GetMapping("/tipo/{tipo}")
    public List<PropertyModels> getPropertiesByTipo(@PathVariable PropertyModels.TipoPropiedad tipo) {
        return propertyService.getPropertiesByTipo(tipo);
    }
}