package com.blue.Apartamento.controllers;

import com.blue.Apartamento.models.PropertyImagesModel;
import com.blue.Apartamento.services.PropertyImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/property-images")
public class PropertyImagesController {

    @Autowired
    private PropertyImagesService propertyImagesService;

    // 🔹 Obtener todas las imágenes
    @GetMapping
    public List<PropertyImagesModel> getAllImages() {
        return propertyImagesService.getAllImages();
    }

    // 🔹 Obtener imagen por ID
    @GetMapping("/{id}")
    public ResponseEntity<PropertyImagesModel> getImageById(@PathVariable Long id) {
        Optional<PropertyImagesModel> image = propertyImagesService.getImageById(id);
        return image.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 🔹 Crear nueva imagen
    @PostMapping
    public PropertyImagesModel createImage(@RequestBody PropertyImagesModel image) {
        return propertyImagesService.saveImage(image);
    }

    // 🔹 Actualizar imagen existente
    @PutMapping("/{id}")
    public ResponseEntity<PropertyImagesModel> updateImage(@PathVariable Long id, @RequestBody PropertyImagesModel imageDetails) {
        Optional<PropertyImagesModel> imageOptional = propertyImagesService.getImageById(id);
        if (imageOptional.isPresent()) {
            PropertyImagesModel imageToUpdate = imageOptional.get();

            imageToUpdate.setUrlImagen(imageDetails.getUrlImagen());
            imageToUpdate.setOrden(imageDetails.getOrden());
            imageToUpdate.setEsPrincipal(imageDetails.isEsPrincipal());
            imageToUpdate.setFechaSubida(imageDetails.getFechaSubida());

            PropertyImagesModel updatedImage = propertyImagesService.saveImage(imageToUpdate);
            return ResponseEntity.ok(updatedImage);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 🔹 Eliminar imagen
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long id) {
        Optional<PropertyImagesModel> image = propertyImagesService.getImageById(id);
        if (image.isPresent()) {
            propertyImagesService.deleteImage(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 🔹 Obtener imágenes de una propiedad
    @GetMapping("/property/{propertyId}")
    public List<PropertyImagesModel> getImagesByProperty(@PathVariable Long propertyId) {
        return propertyImagesService.getImagesByProperty(propertyId);
    }

    // 🔹 Obtener la imagen principal de una propiedad
    @GetMapping("/property/{propertyId}/main")
    public PropertyImagesModel getMainImageByProperty(@PathVariable Long propertyId) {
        return propertyImagesService.getMainImageByProperty(propertyId);
    }

    // 🔹 Obtener imágenes ordenadas por "orden"
    @GetMapping("/property/{propertyId}/ordered")
    public List<PropertyImagesModel> getImagesByPropertyOrdered(@PathVariable Long propertyId) {
        return propertyImagesService.getImagesByPropertyOrdered(propertyId);
    }
}

