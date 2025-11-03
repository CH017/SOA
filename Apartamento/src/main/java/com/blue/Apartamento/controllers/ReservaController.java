package com.blue.Apartamento.controllers;

import com.blue.Apartamento.models.ReservaModel;
import com.blue.Apartamento.services.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservas")
@CrossOrigin(origins = "*")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    // ------------------ OBTENER RESERVAS ------------------
    @GetMapping
    public List<ReservaModel> getAllReservas() {
        return reservaService.getAllReservas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaModel> getReservaById(@PathVariable Long id) {
        Optional<ReservaModel> reserva = reservaService.getReservaById(id);
        return reserva.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/cliente/{idCliente}")
    public List<ReservaModel> getReservasByCliente(@PathVariable Long idCliente) {
        return reservaService.getReservasByCliente(idCliente);
    }

    @GetMapping("/propiedad/{idPropiedad}")
    public List<ReservaModel> getReservasByPropiedad(@PathVariable Long idPropiedad) {
        return reservaService.getReservasByPropiedad(idPropiedad);
    }

    @GetMapping("/estado/{estado}")
    public List<ReservaModel> getReservasByEstado(@PathVariable ReservaModel.EstadoReserva estado) {
        return reservaService.getReservasByEstado(estado);
    }

    @GetMapping("/fecha/{fecha}")
    public List<ReservaModel> getReservasPorFecha(@PathVariable String fecha) {
        LocalDate localFecha = LocalDate.parse(fecha);
        return reservaService.getReservasPorFecha(localFecha);
    }

    // ------------------ CREAR / RESERVAR ------------------
    @PostMapping
    public ResponseEntity<?> createReserva(@RequestBody ReservaModel reserva) {
        String error = validarReserva(reserva);
        if (!error.isEmpty()) {
            return ResponseEntity.badRequest().body(error);
        }

        try {
            ReservaModel nuevaReserva = reservaService.reservarApartamento(reserva);
            return ResponseEntity.ok(nuevaReserva);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // ------------------ ACTUALIZAR ------------------
    @PutMapping("/{id}")
    public ResponseEntity<?> updateReserva(@PathVariable Long id, @RequestBody ReservaModel reservaDetails) {
        String error = validarReserva(reservaDetails);
        if (!error.isEmpty()) {
            return ResponseEntity.badRequest().body(error);
        }

        try {
            reservaDetails.setIdReservacion(id);
            ReservaModel updated = reservaService.actualizarReserva(reservaDetails);
            return ResponseEntity.ok(updated);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // ------------------ CANCELAR ------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancelarReserva(@PathVariable Long id) {
        try {
            reservaService.cancelarReserva(id);
            return ResponseEntity.ok("Reserva cancelada correctamente");
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ------------------ VALIDACIONES ------------------
    private String validarReserva(ReservaModel reserva) {
        if (reserva.getPropiedad() == null || reserva.getPropiedad().getId() == null)
            return "La propiedad es obligatoria.";
        if (reserva.getCliente() == null || reserva.getCliente().getId() == null)
            return "El cliente es obligatorio.";
        if (reserva.getFechaEntrada() == null)
            return "La fecha de entrada es obligatoria.";
        if (reserva.getFechaSalida() == null)
            return "La fecha de salida es obligatoria.";
        if (reserva.getFechaSalida() != null && reserva.getFechaEntrada() != null
                && reserva.getFechaSalida().isBefore(reserva.getFechaEntrada()))
            return "La fecha de salida debe ser posterior a la fecha de entrada.";
        if (reserva.getNumeroHuespedes() <= 0)
            return "El número de huéspedes debe ser mayor a cero.";
        if (reserva.getPrecioTotal() == null || reserva.getPrecioTotal().compareTo(BigDecimal.ZERO) < 0)
            return "El precio total debe ser mayor o igual a cero.";
        if (reserva.getEstado() == null)
            return "El estado de la reserva es obligatorio.";

        return ""; // Todo correcto
    }
}
