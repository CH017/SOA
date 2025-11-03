package com.blue.Apartamento.models;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservas")
public class ReservaModel {

    public enum EstadoReserva {
        PAGADO,
        PENDIENTE,
        PROCESANDO,
        CANCELADO,
        A_PLAZOS
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reservacion")
    private Long idReservacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_propiedad", nullable = false)
    private PropertyModels propiedad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente")
    private UsersModel cliente; // Solo si tipo CLIENTE

    @Column(name = "fecha_entrada", nullable = false)
    private LocalDate fechaEntrada;

    @Column(name = "fecha_salida", nullable = false)
    private LocalDate fechaSalida;

    @Column(name = "numero_huespedes")
    private int numeroHuespedes;

    @Column(name = "precio_total", precision = 10, scale = 2)
    private BigDecimal precioTotal;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 20)
    private EstadoReserva estado;

    @Column(name = "fecha_reservacion", nullable = false)
    private LocalDateTime fechaReservacion;

    @Column(name = "notas", columnDefinition = "TEXT")
    private String notas;

    @Column(name = "codigo_reserva", unique = true, length = 50)
    private String codigoReserva;

    @Column(name = "fecha_checkin")
    private LocalDateTime fechaCheckin;

    @Column(name = "fecha_checkout")
    private LocalDateTime fechaCheckout;

    // 🧱 Constructor vacío
    public ReservaModel() {
    }

    // 🏗️ Constructor completo
    public ReservaModel(Long idReservacion, PropertyModels propiedad, UsersModel cliente, LocalDate fechaEntrada,
                        LocalDate fechaSalida, int numeroHuespedes, BigDecimal precioTotal, EstadoReserva estado,
                        LocalDateTime fechaReservacion, String notas, String codigoReserva,
                        LocalDateTime fechaCheckin, LocalDateTime fechaCheckout) {
        this.idReservacion = idReservacion;
        this.propiedad = propiedad;
        this.cliente = cliente;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.numeroHuespedes = numeroHuespedes;
        this.precioTotal = precioTotal;
        this.estado = estado;
        this.fechaReservacion = fechaReservacion;
        this.notas = notas;
        this.codigoReserva = codigoReserva;
        this.fechaCheckin = fechaCheckin;
        this.fechaCheckout = fechaCheckout;
    }

    // 🔹 Getters y Setters
    public Long getIdReservacion() {
        return idReservacion;
    }

    public void setIdReservacion(Long idReservacion) {
        this.idReservacion = idReservacion;
    }

    public PropertyModels getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(PropertyModels propiedad) {
        this.propiedad = propiedad;
    }

    public UsersModel getCliente() {
        return cliente;
    }

    public void setCliente(UsersModel cliente) {
        this.cliente = cliente;
    }

    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(LocalDate fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public int getNumeroHuespedes() {
        return numeroHuespedes;
    }

    public void setNumeroHuespedes(int numeroHuespedes) {
        this.numeroHuespedes = numeroHuespedes;
    }

    public BigDecimal getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(BigDecimal precioTotal) {
        this.precioTotal = precioTotal;
    }

    public EstadoReserva getEstado() {
        return estado;
    }

    public void setEstado(EstadoReserva estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaReservacion() {
        return fechaReservacion;
    }

    public void setFechaReservacion(LocalDateTime fechaReservacion) {
        this.fechaReservacion = fechaReservacion;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public String getCodigoReserva() {
        return codigoReserva;
    }

    public void setCodigoReserva(String codigoReserva) {
        this.codigoReserva = codigoReserva;
    }

    public LocalDateTime getFechaCheckin() {
        return fechaCheckin;
    }

    public void setFechaCheckin(LocalDateTime fechaCheckin) {
        this.fechaCheckin = fechaCheckin;
    }

    public LocalDateTime getFechaCheckout() {
        return fechaCheckout;
    }

    public void setFechaCheckout(LocalDateTime fechaCheckout) {
        this.fechaCheckout = fechaCheckout;
    }
}
