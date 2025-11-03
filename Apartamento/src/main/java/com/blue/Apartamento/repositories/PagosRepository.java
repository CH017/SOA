package com.blue.Apartamento.repositories;

import com.blue.Apartamento.models.PagosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagosRepository extends JpaRepository<PagosModel, Long> {

    // 🔹 Obtener todos los pagos de una reservación
    List<PagosModel> findByReservacion_Id(Long idReservacion);

    // 🔹 Obtener pagos por método
    List<PagosModel> findByMetodoPago(PagosModel.MetodoPago metodoPago);
}
