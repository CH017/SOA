package com.blue.Apartamento.controllers;

import com.blue.Apartamento.models.ReservaModel;
import com.blue.Apartamento.services.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    // 🔹 Obtener todas las reservas
    @GetMapping
    public List<ReservaModel> getAllReservas() {
        return reservaService.getAllReservas();
    }

    // 🔹 Obtener reserva por ID
    @GetMapping("/{id}")
    public ResponseEntity<ReservaModel> getReservaById(@PathVariable Long id) {
        Optional<ReservaModel> reserva = reservaService.getReservaById(id);
        return reserva.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 🔹 Crear nueva reserva
    @PostMapping
    public ReservaModel createReserva(@RequestBody ReservaModel reserva) {
        return reservaService.saveReserva(reserva);
    }

    // 🔹 Actualizar reserva existente
    @PutMapping("/{id}")
    public ResponseEntity<ReservaModel> updateReserva(@PathVariable Long id, @RequestBody ReservaModel reservaDetails) {
        Optional<ReservaModel> reservaOptional = reservaService.getReservaById(id);
        if (reservaOptional.isPresent()) {
            ReservaModel reservaToUpdate = reservaOptional.get();

            reservaToUpdate.setPropiedad(reservaDetails.getPropiedad());
            reservaToUpdate.setCliente(reservaDetails.getCliente());
            reservaToUpdate.setFechaEntrada(reservaDetails.getFechaEntrada());
            reservaToUpdate.setFechaSalida(reservaDetails.getFechaSalida());
            reservaToUpdate.setNumeroHuespedes(reservaDetails.getNumeroHuespedes());
            reservaToUpdate.setPrecioTotal(reservaDetails.getPrecioTotal());
            reservaToUpdate.setEstado(reservaDetails.getEstado());
            reservaToUpdate.setFechaReservacion(reservaDetails.getFechaReservacion());
            reservaToUpdate.setNotas(reservaDetails.getNotas());
            reservaToUpdate.setCodigoReserva(reservaDetails.getCodigoReserva());
            reservaToUpdate.setFechaCheckin(reservaDetails.getFechaCheckin());
            reservaToUpdate.setFechaCheckout(reservaDetails.getFechaCheckout());

            ReservaModel updated = reservaService.saveReserva(reservaToUpdate);
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 🔹 Eliminar reserva
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable Long id) {
        Optional<ReservaModel> reserva = reservaService.getReservaById(id);
        if (reserva.isPresent()) {
            reservaService.deleteReserva(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 🔹 Obtener reservas por cliente
    @GetMapping("/cliente/{idCliente}")
    public List<ReservaModel> getReservasByCliente(@PathVariable Long idCliente) {
        return reservaService.getReservasByCliente(idCliente);
    }

    // 🔹 Obtener reservas por propiedad
    @GetMapping("/propiedad/{idPropiedad}")
    public List<ReservaModel> getReservasByPropiedad(@PathVariable Long idPropiedad) {
        return reservaService.getReservasByPropiedad(idPropiedad);
    }

    // 🔹 Obtener reservas por estado
    @GetMapping("/estado/{estado}")
    public List<ReservaModel> getReservasByEstado(@PathVariable ReservaModel.EstadoReserva estado) {
        return reservaService.getReservasByEstado(estado);
    }

    // 🔹 Obtener reservas de una propiedad en un rango de fechas
    @GetMapping("/propiedad/{idPropiedad}/fechas")
    public List<ReservaModel> getReservasByPropiedadAndFechas(@PathVariable Long idPropiedad,
                                                              @RequestParam String inicio,
                                                              @RequestParam String fin) {
        LocalDate fechaInicio = LocalDate.parse(inicio);
        LocalDate fechaFin = LocalDate.parse(fin);
        return reservaService.getReservasByPropiedadAndFechas(idPropiedad, fechaInicio, fechaFin);
    }

    // 🔹 Obtener reservas pendientes de pago de un cliente
    @GetMapping("/cliente/{idCliente}/pendientes")
    public List<ReservaModel> getReservasPendientesByCliente(@PathVariable Long idCliente) {
        return reservaService.getReservasPendientesByCliente(idCliente);
    }
}

