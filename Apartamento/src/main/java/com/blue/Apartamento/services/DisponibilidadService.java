package com.blue.Apartamento.services;

import com.blue.Apartamento.models.DisponibilidadModel;
import com.blue.Apartamento.repositories.DisponibilidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DisponibilidadService {

    @Autowired
    private DisponibilidadRepository disponibilidadRepository;

    // 🔹 Obtener todas las disponibilidades
    public List<DisponibilidadModel> getAllDisponibilidades() {
        return disponibilidadRepository.findAll();
    }

    // 🔹 Obtener disponibilidad por ID
    public Optional<DisponibilidadModel> getDisponibilidadById(Long id) {
        return disponibilidadRepository.findById(id);
    }

    // 🔹 Guardar o actualizar disponibilidad
    public DisponibilidadModel saveDisponibilidad(DisponibilidadModel disponibilidad) {
        return disponibilidadRepository.save(disponibilidad);
    }

    // 🔹 Eliminar disponibilidad por ID
    public void deleteDisponibilidad(Long id) {
        disponibilidadRepository.deleteById(id);
    }

    // 🔹 Obtener todas las disponibilidades de una propiedad
    public List<DisponibilidadModel> getDisponibilidadesByPropiedad(Long idPropiedad) {
        return disponibilidadRepository.findByPropiedad_Id(idPropiedad);
    }

    // 🔹 Obtener disponibilidad de una propiedad en una fecha específica
    public DisponibilidadModel getDisponibilidadByPropiedadAndFecha(Long idPropiedad, LocalDate fecha) {
        return disponibilidadRepository.findByPropiedad_IdAndFecha(idPropiedad, fecha);
    }

    // 🔹 Obtener todas las fechas disponibles de una propiedad
    public List<DisponibilidadModel> getFechasDisponiblesByPropiedad(Long idPropiedad) {
        return disponibilidadRepository.findByPropiedad_IdAndDisponibleTrue(idPropiedad);
    }

    // 🔹 Obtener todas las fechas no disponibles de una propiedad
    public List<DisponibilidadModel> getFechasNoDisponiblesByPropiedad(Long idPropiedad) {
        return disponibilidadRepository.findByPropiedad_IdAndDisponibleFalse(idPropiedad);
    }
}

