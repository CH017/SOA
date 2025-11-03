package com.blue.Apartamento.services;

import com.blue.Apartamento.models.PropertyModels;
import com.blue.Apartamento.repositories.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    // Obtener todas las propiedades
    public List<PropertyModels> getAllProperties() {
        return propertyRepository.findAll();
    }

    // Obtener una propiedad por ID
    public Optional<PropertyModels> getPropertyById(Long id) {
        return propertyRepository.findById(id);
    }

    // Guardar o actualizar una propiedad
    public PropertyModels saveProperty(PropertyModels property) {
        return propertyRepository.save(property);
    }

    // Eliminar una propiedad
    public void deleteProperty(Long id) {
        propertyRepository.deleteById(id);
    }

    // Buscar propiedades por propietario
    public List<PropertyModels> getPropertiesByPropietario(Long idPropietario) {
        return propertyRepository.findByPropietario_Id(idPropietario);
    }

    // Buscar propiedades por ciudad
    public List<PropertyModels> getPropertiesByCiudad(String ciudad) {
        return propertyRepository.findByCiudad(ciudad);
    }

    // Buscar propiedades por estado
    public List<PropertyModels> getPropertiesByEstado(PropertyModels.EstadoPropiedad estado) {
        return propertyRepository.findByEstado(estado);
    }

    // Buscar propiedades por tipo
    public List<PropertyModels> getPropertiesByTipo(PropertyModels.TipoPropiedad tipo) {
        return propertyRepository.findByTipo(tipo);
    }

    // ✅ Comprobar disponibilidad de una propiedad
    public boolean comprobarDisponibilidad(Long idPropiedad) {
        PropertyModels propiedad = propertyRepository.findById(idPropiedad)
                .orElseThrow(() -> new NoSuchElementException("Propiedad no encontrada con ID: " + idPropiedad));

        // Se considera disponible solo si su estado actual es DISPONIBLE
        return propiedad.getEstado() == PropertyModels.EstadoPropiedad.DISPONIBLE;
    }

    // ✅ (Opcional) Cambiar el estado de una propiedad
    public PropertyModels cambiarEstado(Long idPropiedad, PropertyModels.EstadoPropiedad nuevoEstado) {
        PropertyModels propiedad = propertyRepository.findById(idPropiedad)
                .orElseThrow(() -> new NoSuchElementException("Propiedad no encontrada con ID: " + idPropiedad));

        propiedad.setEstado(nuevoEstado);
        propiedad.setFechaActualizacion(java.time.LocalDateTime.now());
        return propertyRepository.save(propiedad);
    }
}