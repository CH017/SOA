package com.blue.Apartamento.repositories;

import com.blue.Apartamento.models.PropertyModels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<PropertyModels, Long> {
 // Buscar todas las propiedades por el ID del propietario
    List<PropertyModels> findByPropietario_Id(Long idPropietario);

    // Buscar propiedades por ciudad
    List<PropertyModels> findByCiudad(String ciudad);

    // Buscar propiedades por estado (disponible, ocupado, etc.)
    List<PropertyModels> findByEstado(PropertyModels.EstadoPropiedad estado);

    // Buscar propiedades por tipo (lote, habitación, edificio)
    List<PropertyModels> findByTipo(PropertyModels.TipoPropiedad tipo);
  
}