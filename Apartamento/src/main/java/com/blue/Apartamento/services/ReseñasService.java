package com.blue.Apartamento.services;

import com.blue.Apartamento.models.ReseñasModel;
import com.blue.Apartamento.repositories.ReseñasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReseñasService {

    @Autowired
    private ReseñasRepository reseñasRepository;

    // 🔹 Crear o actualizar una reseña
    public ReseñasModel guardarReseña(ReseñasModel reseña) {
        return reseñasRepository.save(reseña);
    }

    // 🔹 Obtener todas las reseñas
    public List<ReseñasModel> obtenerTodas() {
        return reseñasRepository.findAll();
    }

    // 🔹 Buscar reseña por ID
    public Optional<ReseñasModel> obtenerPorId(Long id) {
        return reseñasRepository.findById(id);
    }

    // 🔹 Eliminar reseña por ID
    public boolean eliminarReseña(Long id) {
        if (reseñasRepository.existsById(id)) {
            reseñasRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // 🔹 Obtener reseñas por reservación
    public List<ReseñasModel> obtenerPorReservacion(Long idReservacion) {
        return reseñasRepository.findByReservacion_Id(idReservacion);
    }

    // 🔹 Obtener reseñas por cliente
    public List<ReseñasModel> obtenerPorCliente(Long idCliente) {
        return reseñasRepository.findByReservacion_Cliente_Id(idCliente);
    }

    // 🔹 Obtener reseñas por propiedad
    public List<ReseñasModel> obtenerPorPropiedad(Long idPropiedad) {
        return reseñasRepository.findByReservacion_Propiedad_Id(idPropiedad);
    }
}
