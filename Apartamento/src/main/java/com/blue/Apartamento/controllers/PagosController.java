package com.blue.Apartamento.controllers;

import com.blue.Apartamento.models.PagosModel;
import com.blue.Apartamento.services.PagosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pagos")
@CrossOrigin(origins = "*")
public class PagosController {

    @Autowired
    private PagosService pagosService;

    // 🔹 Obtener todos los pagos
    @GetMapping
    public ResponseEntity<List<PagosModel>> getAllPagos() {
        List<PagosModel> pagos = pagosService.getAllPagos();
        return ResponseEntity.ok(pagos);
    }

    // 🔹 Obtener pago por ID
    @GetMapping("/{id}")
    public ResponseEntity<PagosModel> getPagoById(@PathVariable Long id) {
        Optional<PagosModel> pago = pagosService.getPagoById(id);
        return pago.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 🔹 Crear nuevo pago con validaciones
    @PostMapping
    public ResponseEntity<?> createPago(@RequestBody PagosModel pago) {
        String error = validarPago(pago);
        if (!error.isEmpty()) {
            return ResponseEntity.badRequest().body(error);
        }
        PagosModel newPago = pagosService.savePago(pago);
        return ResponseEntity.ok(newPago);
    }

    // 🔹 Actualizar pago existente con validaciones
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePago(@PathVariable Long id, @RequestBody PagosModel pagoDetails) {
        Optional<PagosModel> pagoOptional = pagosService.getPagoById(id);
        if (pagoOptional.isPresent()) {
            String error = validarPago(pagoDetails);
            if (!error.isEmpty()) {
                return ResponseEntity.badRequest().body(error);
            }

            PagosModel pagoToUpdate = pagoOptional.get();
            pagoToUpdate.setReservacion(pagoDetails.getReservacion());
            pagoToUpdate.setMonto(pagoDetails.getMonto());
            pagoToUpdate.setMetodoPago(pagoDetails.getMetodoPago());
            pagoToUpdate.setFechaPago(pagoDetails.getFechaPago());
            pagoToUpdate.setReferenciaPago(pagoDetails.getReferenciaPago());
            pagoToUpdate.setDatosPago(pagoDetails.getDatosPago());
            pagoToUpdate.setFechaCreacion(pagoDetails.getFechaCreacion());

            PagosModel updated = pagosService.savePago(pagoToUpdate);
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 🔹 Eliminar pago
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePago(@PathVariable Long id) {
        Optional<PagosModel> pago = pagosService.getPagoById(id);
        if (pago.isPresent()) {
            pagosService.deletePago(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 🔹 Obtener pagos de una reservación
    @GetMapping("/reservacion/{idReservacion}")
    public ResponseEntity<List<PagosModel>> getPagosByReservacion(@PathVariable Long idReservacion) {
        List<PagosModel> pagos = pagosService.getPagosByReservacion(idReservacion);
        return ResponseEntity.ok(pagos);
    }

    // 🔹 Obtener pagos por método de pago
    @GetMapping("/metodo/{metodoPago}")
    public ResponseEntity<List<PagosModel>> getPagosByMetodo(@PathVariable PagosModel.MetodoPago metodoPago) {
        List<PagosModel> pagos = pagosService.getPagosByMetodo(metodoPago);
        return ResponseEntity.ok(pagos);
    }

    // ✅ Validaciones básicas de pago
    private String validarPago(PagosModel pago) {
        if (pago.getReservacion() == null)
            return "La reservación es obligatoria.";
        if (pago.getMonto() == null || pago.getMonto().doubleValue() <= 0)
            return "El monto debe ser mayor a cero.";
        if (pago.getMetodoPago() == null)
            return "El método de pago es obligatorio.";
        if (pago.getFechaPago() == null)
            return "La fecha de pago es obligatoria.";
        if (pago.getReferenciaPago() == null || pago.getReferenciaPago().isEmpty())
            return "La referencia de pago es obligatoria.";

        return ""; // todo correcto
    }
}
