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
public class PagosController {

    @Autowired
    private PagosService pagosService;

    // 🔹 Obtener todos los pagos
    @GetMapping
    public List<PagosModel> getAllPagos() {
        return pagosService.getAllPagos();
    }

    // 🔹 Obtener pago por ID
    @GetMapping("/{id}")
    public ResponseEntity<PagosModel> getPagoById(@PathVariable Long id) {
        Optional<PagosModel> pago = pagosService.getPagoById(id);
        return pago.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 🔹 Crear nuevo pago
    @PostMapping
    public PagosModel createPago(@RequestBody PagosModel pago) {
        return pagosService.savePago(pago);
    }

    // 🔹 Actualizar pago existente
    @PutMapping("/{id}")
    public ResponseEntity<PagosModel> updatePago(@PathVariable Long id, @RequestBody PagosModel pagoDetails) {
        Optional<PagosModel> pagoOptional = pagosService.getPagoById(id);
        if (pagoOptional.isPresent()) {
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
    public List<PagosModel> getPagosByReservacion(@PathVariable Long idReservacion) {
        return pagosService.getPagosByReservacion(idReservacion);
    }

    // 🔹 Obtener pagos por método de pago
    @GetMapping("/metodo/{metodoPago}")
    public List<PagosModel> getPagosByMetodo(@PathVariable PagosModel.MetodoPago metodoPago) {
        return pagosService.getPagosByMetodo(metodoPago);
    }
}

