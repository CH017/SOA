package com.blue.Apartamento.repositories;

import com.blue.Apartamento.models.ReseñasModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReseñasRepository extends JpaRepository<ReseñasModel, Long> {

    // 🔹 Obtener todas las reseñas de una reservación
    List<ReseñasModel> findByReservacion_Id(Long idReservacion);

    // 🔹 Obtener todas las reseñas de un cliente (a través de la reservación)
    List<ReseñasModel> findByReservacion_Cliente_Id(Long idCliente);

    // 🔹 Obtener todas las reseñas de una propiedad (a través de la reservación)
    List<ReseñasModel> findByReservacion_Propiedad_Id(Long idPropiedad);
}

