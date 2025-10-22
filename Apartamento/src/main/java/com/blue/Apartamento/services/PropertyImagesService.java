package com.blue.Apartamento.services;

import com.blue.Apartamento.models.PropertyImagesModel;
import com.blue.Apartamento.repositories.PropertyImagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyImagesService {

    @Autowired
    private PropertyImagesRepository propertyImagesRepository;

    // 🔹 Obtener todas las imágenes
    public List<PropertyImagesModel> getAllImages() {
        return propertyImagesRepository.findAll();
    }

    // 🔹 Obtener imagen por ID
    public Optional<PropertyImagesModel> getImageById(Long id) {
        return propertyImagesRepository.findById(id);
    }

    // 🔹 Guardar o actualizar imagen
    public PropertyImagesModel saveImage(PropertyImagesModel image) {
        return propertyImagesRepository.save(image);
    }

    // 🔹 Eliminar imagen
    public void deleteImage(Long id) {
        propertyImagesRepository.deleteById(id);
    }

    // 🔹 Obtener todas las imágenes de una propiedad
    public List<PropertyImagesModel> getImagesByProperty(Long propertyId) {
        return propertyImagesRepository.findByPropiedad_Id(propertyId);
    }

    // 🔹 Obtener la imagen principal de una propiedad
    public PropertyImagesModel getMainImageByProperty(Long propertyId) {
        return propertyImagesRepository.findFirstByPropiedad_IdAndEsPrincipalTrue(propertyId);
    }

    // 🔹 Obtener imágenes de una propiedad ordenadas por "orden"
    public List<PropertyImagesModel> getImagesByPropertyOrdered(Long propertyId) {
        return propertyImagesRepository.findByPropiedad_IdOrderByOrdenAsc(propertyId);
    }
}
