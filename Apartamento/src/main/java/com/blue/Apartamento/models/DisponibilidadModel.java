package com.blue.Apartamento.models;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "disponibilidad")
public class DisponibilidadModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_disponibilidad")
    private Long idDisponibilidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_propiedad", nullable = false)
    private PropertyModels propiedad;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "disponible", nullable = false)
    private boolean disponible;

    @Column(name = "precio_especial", precision = 10, scale = 2)
    private BigDecimal precioEspecial;

    // 🧱 Constructor vacío (requerido por JPA)
    public DisponibilidadModel() {
    }

    // 🏗️ Constructor con parámetros
    public DisponibilidadModel(Long idDisponibilidad, PropertyModels propiedad, LocalDate fecha, boolean disponible, BigDecimal precioEspecial) {
        this.idDisponibilidad = idDisponibilidad;
        this.propiedad = propiedad;
        this.fecha = fecha;
        this.disponible = disponible;
        this.precioEspecial = precioEspecial;
    }

    // 🔹 Getters y Setters
    public Long getIdDisponibilidad() {
        return idDisponibilidad;
    }

    public void setIdDisponibilidad(Long idDisponibilidad) {
        this.idDisponibilidad = idDisponibilidad;
    }

    public PropertyModels getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(PropertyModels propiedad) {
        this.propiedad = propiedad;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public BigDecimal getPrecioEspecial() {
        return precioEspecial;
    }

    public void setPrecioEspecial(BigDecimal precioEspecial) {
        this.precioEspecial = precioEspecial;
    }
}
