package com.blue.Apartamento.repositories;

import com.blue.Apartamento.models.DisponibilidadModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DisponibilidadRepository extends JpaRepository<DisponibilidadModel, Long> {

    // 🔹 Obtener todas las disponibilidades de una propiedad
    List<DisponibilidadModel> findByPropiedad_Id(Long idPropiedad);

    // 🔹 Obtener disponibilidad de una propiedad en una fecha específica
    DisponibilidadModel findByPropiedad_IdAndFecha(Long idPropiedad, LocalDate fecha);

    // 🔹 Obtener todas las fechas disponibles de una propiedad
    List<DisponibilidadModel> findByPropiedad_IdAndDisponibleTrue(Long idPropiedad);

    // 🔹 Obtener todas las fechas no disponibles de una propiedad
    List<DisponibilidadModel> findByPropiedad_IdAndDisponibleFalse(Long idPropiedad);
}

