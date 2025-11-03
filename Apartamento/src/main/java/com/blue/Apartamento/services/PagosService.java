package com.blue.Apartamento.services;

import com.blue.Apartamento.models.PagosModel;
import com.blue.Apartamento.repositories.PagosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagosService {

    @Autowired
    private PagosRepository pagosRepository;

    // 🔹 Obtener todos los pagos
    public List<PagosModel> getAllPagos() {
        return pagosRepository.findAll();
    }

    // 🔹 Obtener pago por ID
    public Optional<PagosModel> getPagoById(Long id) {
        return pagosRepository.findById(id);
    }

    // 🔹 Guardar o actualizar pago
    public PagosModel savePago(PagosModel pago) {
        return pagosRepository.save(pago);
    }

    // 🔹 Eliminar pago por ID
    public void deletePago(Long id) {
        pagosRepository.deleteById(id);
    }

    // 🔹 Obtener todos los pagos de una reservación
    public List<PagosModel> getPagosByReservacion(Long idReservacion) {
        return pagosRepository.findByReservacion_Id(idReservacion);
    }

    // 🔹 Obtener pagos por método de pago
    public List<PagosModel> getPagosByMetodo(PagosModel.MetodoPago metodoPago) {
        return pagosRepository.findByMetodoPago(metodoPago);
    }
}

