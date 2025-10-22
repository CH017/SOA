package com.blue.Apartamento.services;

import com.blue.Apartamento.models.PropertyModels;
import com.blue.Apartamento.repositories.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
}
