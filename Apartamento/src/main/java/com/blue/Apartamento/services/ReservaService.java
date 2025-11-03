package com.blue.Apartamento.services;

import com.blue.Apartamento.models.ReservaModel;
import com.blue.Apartamento.repositories.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    // ------------------ CREAR / RESERVAR ------------------
    public ReservaModel reservarApartamento(ReservaModel reserva) {
        // Verificar disponibilidad básica
        List<ReservaModel> reservas = reservaRepository.findByPropiedad_Id(reserva.getPropiedad().getId());
        for (ReservaModel r : reservas) {
            if (!(reserva.getFechaSalida().isBefore(r.getFechaEntrada()) || reserva.getFechaEntrada().isAfter(r.getFechaSalida()))) {
                throw new RuntimeException("La propiedad no está disponible en esas fechas.");
            }
        }

        // Calcular precio total
        long dias = ChronoUnit.DAYS.between(reserva.getFechaEntrada(), reserva.getFechaSalida());
        if (dias <= 0) dias = 1;
        reserva.setPrecioTotal(reserva.getPropiedad().getPrecioNoche().multiply(BigDecimal.valueOf(dias)));

        reserva.setEstado(ReservaModel.EstadoReserva.PENDIENTE);
        reserva.setFechaReservacion(LocalDateTime.now());
        return reservaRepository.save(reserva);
    }

    // ------------------ CANCELAR ------------------
    public void cancelarReserva(Long idReserva) {
        ReservaModel reserva = reservaRepository.findById(idReserva)
                .orElseThrow(() -> new NoSuchElementException("Reserva no encontrada"));
        reserva.setEstado(ReservaModel.EstadoReserva.CANCELADO);
        reservaRepository.save(reserva);
    }

    // ------------------ OBTENER RESERVAS ------------------
    public List<ReservaModel> getAllReservas() {
        return reservaRepository.findAll();
    }

    public Optional<ReservaModel> getReservaById(Long id) {
        return reservaRepository.findById(id);
    }

    public List<ReservaModel> getReservasByCliente(Long idCliente) {
        return reservaRepository.findByCliente_Id(idCliente);
    }

    public List<ReservaModel> getReservasByPropiedad(Long idPropiedad) {
        return reservaRepository.findByPropiedad_Id(idPropiedad);
    }

    public List<ReservaModel> getReservasByEstado(ReservaModel.EstadoReserva estado) {
        return reservaRepository.findByEstado(estado);
    }

    public List<ReservaModel> getReservasPorFecha(LocalDate fecha) {
        return reservaRepository.findAll().stream()
                .filter(r -> !r.getFechaEntrada().isAfter(fecha) && !r.getFechaSalida().isBefore(fecha))
                .toList();
    }

    // ------------------ ACTUALIZAR ------------------
    public ReservaModel actualizarReserva(ReservaModel reservaActualizada) {
        ReservaModel reservaExistente = reservaRepository.findById(reservaActualizada.getIdReservacion())
                .orElseThrow(() -> new NoSuchElementException("Reserva no encontrada"));

        reservaExistente.setFechaEntrada(reservaActualizada.getFechaEntrada());
        reservaExistente.setFechaSalida(reservaActualizada.getFechaSalida());
        reservaExistente.setEstado(reservaActualizada.getEstado());
        reservaExistente.setCliente(reservaActualizada.getCliente());
        reservaExistente.setPropiedad(reservaActualizada.getPropiedad());
        reservaExistente.setNumeroHuespedes(reservaActualizada.getNumeroHuespedes());
        reservaExistente.setNotas(reservaActualizada.getNotas());

        // Recalcular precio total
        long dias = ChronoUnit.DAYS.between(reservaExistente.getFechaEntrada(), reservaExistente.getFechaSalida());
        if (dias <= 0) dias = 1;
        reservaExistente.setPrecioTotal(reservaExistente.getPropiedad().getPrecioNoche().multiply(BigDecimal.valueOf(dias)));

        return reservaRepository.save(reservaExistente);
    }
}


