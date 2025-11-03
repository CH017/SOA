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
@CrossOrigin(origins = "*")
public class PropertyImagesController {

    @Autowired
    private PropertyImagesService propertyImagesService;

    // 🔹 Obtener todas las imágenes
    @GetMapping
    public ResponseEntity<List<PropertyImagesModel>> getAllImages() {
        List<PropertyImagesModel> images = propertyImagesService.getAllImages();
        return ResponseEntity.ok(images);
    }

    // 🔹 Obtener imagen por ID
    @GetMapping("/{id}")
    public ResponseEntity<PropertyImagesModel> getImageById(@PathVariable Long id) {
        Optional<PropertyImagesModel> image = propertyImagesService.getImageById(id);
        return image.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 🔹 Crear nueva imagen con validaciones
    @PostMapping
    public ResponseEntity<?> createImage(@RequestBody PropertyImagesModel image) {
        String error = validarImage(image);
        if (!error.isEmpty()) {
            return ResponseEntity.badRequest().body(error);
        }
        PropertyImagesModel newImage = propertyImagesService.saveImage(image);
        return ResponseEntity.ok(newImage);
    }

    // 🔹 Actualizar imagen existente con validaciones
    @PutMapping("/{id}")
    public ResponseEntity<?> updateImage(@PathVariable Long id, @RequestBody PropertyImagesModel imageDetails) {
        Optional<PropertyImagesModel> imageOptional = propertyImagesService.getImageById(id);
        if (imageOptional.isPresent()) {
            String error = validarImage(imageDetails);
            if (!error.isEmpty()) {
                return ResponseEntity.badRequest().body(error);
            }

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
    public ResponseEntity<List<PropertyImagesModel>> getImagesByProperty(@PathVariable Long propertyId) {
        List<PropertyImagesModel> images = propertyImagesService.getImagesByProperty(propertyId);
        return ResponseEntity.ok(images);
    }

    // 🔹 Obtener la imagen principal de una propiedad
    @GetMapping("/property/{propertyId}/main")
    public ResponseEntity<PropertyImagesModel> getMainImageByProperty(@PathVariable Long propertyId) {
        PropertyImagesModel mainImage = propertyImagesService.getMainImageByProperty(propertyId);
        return ResponseEntity.ok(mainImage);
    }

    // 🔹 Obtener imágenes ordenadas por "orden"
    @GetMapping("/property/{propertyId}/ordered")
    public ResponseEntity<List<PropertyImagesModel>> getImagesByPropertyOrdered(@PathVariable Long propertyId) {
        List<PropertyImagesModel> images = propertyImagesService.getImagesByPropertyOrdered(propertyId);
        return ResponseEntity.ok(images);
    }

    // ✅ Validaciones básicas de imagen
    private String validarImage(PropertyImagesModel image) {
        if (image.getUrlImagen() == null || image.getUrlImagen().isEmpty())
            return "La URL de la imagen es obligatoria.";
        if (image.getOrden() < 0)
            return "El orden no puede ser negativo.";
        if (image.getFechaSubida() == null)
            return "La fecha de subida es obligatoria.";

        return ""; // todo correcto
    }
}
