package com.blue.Apartamento.services;

import com.blue.Apartamento.models.ReservaModel;
import com.blue.Apartamento.repositories.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    // 🔹 Obtener todas las reservas
    public List<ReservaModel> getAllReservas() {
        return reservaRepository.findAll();
    }

    // 🔹 Obtener reserva por ID
    public Optional<ReservaModel> getReservaById(Long id) {
        return reservaRepository.findById(id);
    }

    // 🔹 Guardar o actualizar reserva
    public ReservaModel saveReserva(ReservaModel reserva) {
        return reservaRepository.save(reserva);
    }

    // 🔹 Eliminar reserva por ID
    public void deleteReserva(Long id) {
        reservaRepository.deleteById(id);
    }

    // 🔹 Obtener todas las reservas de un cliente
    public List<ReservaModel> getReservasByCliente(Long idCliente) {
        return reservaRepository.findByCliente_Id(idCliente);
    }

    // 🔹 Obtener todas las reservas de una propiedad
    public List<ReservaModel> getReservasByPropiedad(Long idPropiedad) {
        return reservaRepository.findByPropiedad_Id(idPropiedad);
    }

    // 🔹 Obtener reservas por estado
    public List<ReservaModel> getReservasByEstado(ReservaModel.EstadoReserva estado) {
        return reservaRepository.findByEstado(estado);
    }

    // 🔹 Obtener reservas de una propiedad en un rango de fechas
    public List<ReservaModel> getReservasByPropiedadAndFechas(Long idPropiedad, LocalDate fechaInicio, LocalDate fechaFin) {
        return reservaRepository.findByPropiedad_IdAndFechaEntradaGreaterThanEqualAndFechaSalidaLessThanEqual(idPropiedad, fechaInicio, fechaFin);
    }

    // 🔹 Obtener reservas pendientes de pago de un cliente
    public List<ReservaModel> getReservasPendientesByCliente(Long idCliente) {
        return reservaRepository.findByCliente_IdAndEstado(idCliente, ReservaModel.EstadoReserva.PENDIENTE);
    }
}

