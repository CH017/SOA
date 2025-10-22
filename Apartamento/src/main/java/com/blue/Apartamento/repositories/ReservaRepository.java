package com.blue.Apartamento.repositories;

import com.blue.Apartamento.models.ReservaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<ReservaModel, Long> {

    // 🔹 Obtener todas las reservas de un cliente
    List<ReservaModel> findByCliente_Id(Long idCliente);

    // 🔹 Obtener todas las reservas de una propiedad
    List<ReservaModel> findByPropiedad_Id(Long idPropiedad);

    // 🔹 Obtener reservas por estado
    List<ReservaModel> findByEstado(ReservaModel.EstadoReserva estado);

    // 🔹 Obtener reservas de una propiedad en un rango de fechas
    List<ReservaModel> findByPropiedad_IdAndFechaEntradaGreaterThanEqualAndFechaSalidaLessThanEqual(
            Long idPropiedad, LocalDate fechaInicio, LocalDate fechaFin);

    // 🔹 Obtener reservas pendientes de pago
    List<ReservaModel> findByCliente_IdAndEstado(Long idCliente, ReservaModel.EstadoReserva estado);
}

