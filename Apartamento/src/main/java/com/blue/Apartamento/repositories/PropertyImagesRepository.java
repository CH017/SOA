package com.blue.Apartamento.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.blue.Apartamento.models.PropertyImagesModel;

import java.util.List;

@Repository
public interface PropertyImagesRepository extends JpaRepository<PropertyImagesModel, Long> {

    // 🔹 Buscar imágenes por propiedad
    List<PropertyImagesModel> findByPropiedad_Id(Long idPropiedad);

    // 🔹 Buscar solo la imagen principal de una propiedad
    PropertyImagesModel findFirstByPropiedad_IdAndEsPrincipalTrue(Long idPropiedad);

    // 🔹 Buscar todas las imágenes ordenadas por su campo "orden"
    List<PropertyImagesModel> findByPropiedad_IdOrderByOrdenAsc(Long idPropiedad);
}
