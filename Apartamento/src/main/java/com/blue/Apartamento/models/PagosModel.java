package com.blue.Apartamento.models;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pagos")
public class PagosModel {

    public enum MetodoPago {
        EFECTIVO,
        TARJETA,
        MESES,
        TRANSFERENCIA
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago")
    private Long idPago;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reservacion", nullable = false)
    private ReservaModel reservacion;

    @Column(name = "monto", precision = 10, scale = 2, nullable = false)
    private BigDecimal monto;

    @Enumerated(EnumType.STRING)
    @Column(name = "metodo_pago", nullable = false, length = 20)
    private MetodoPago metodoPago;

    @Column(name = "fecha_pago")
    private LocalDateTime fechaPago;

    @Column(name = "referencia_pago", length = 50)
    private String referenciaPago;

    @Column(name = "datos_pago", columnDefinition = "TEXT")
    private String datosPago;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    // 🧱 Constructor vacío
    public PagosModel() {
    }

    // 🏗️ Constructor completo
    public PagosModel(Long idPago, ReservaModel reservacion, BigDecimal monto, MetodoPago metodoPago,
                      LocalDateTime fechaPago, String referenciaPago, String datosPago, LocalDateTime fechaCreacion) {
        this.idPago = idPago;
        this.reservacion = reservacion;
        this.monto = monto;
        this.metodoPago = metodoPago;
        this.fechaPago = fechaPago;
        this.referenciaPago = referenciaPago;
        this.datosPago = datosPago;
        this.fechaCreacion = fechaCreacion;
    }

    // 🔹 Getters y Setters
    public Long getIdPago() {
        return idPago;
    }

    public void setIdPago(Long idPago) {
        this.idPago = idPago;
    }

    public ReservaModel getReservacion() {
        return reservacion;
    }

    public void setReservacion(ReservaModel reservacion) {
        this.reservacion = reservacion;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public LocalDateTime getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDateTime fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getReferenciaPago() {
        return referenciaPago;
    }

    public void setReferenciaPago(String referenciaPago) {
        this.referenciaPago = referenciaPago;
    }

    public String getDatosPago() {
        return datosPago;
    }

    public void setDatosPago(String datosPago) {
        this.datosPago = datosPago;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}

