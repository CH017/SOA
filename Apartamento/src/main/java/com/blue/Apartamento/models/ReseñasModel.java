package com.blue.Apartamento.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reseñas")
public class ReseñasModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resena")
    private Long idResena;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reservacion", nullable = false)
    private ReservaModel reservacion;

    @Column(name = "calificacion_limpieza", nullable = false)
    private int calificacionLimpieza;

    @Column(name = "calificacion_ubicacion", nullable = false)
    private int calificacionUbicacion;

    @Column(name = "calificacion_comunicacion", nullable = false)
    private int calificacionComunicacion;

    @Column(name = "calificacion_general", nullable = false)
    private int calificacionGeneral;

    @Column(name = "comentario", columnDefinition = "TEXT")
    private String comentario;

    @Column(name = "fecha_resena")
    private LocalDateTime fechaResena;

    @Column(name = "respuesta_propietario", columnDefinition = "TEXT")
    private String respuestaPropietario;

    @Column(name = "fecha_respuesta")
    private LocalDateTime fechaRespuesta;

    // 🧱 Constructor vacío
    public ReseñasModel() {
    }

    // 🏗️ Constructor completo
    public ReseñasModel(Long idResena, ReservaModel reservacion, int calificacionLimpieza, int calificacionUbicacion,
                        int calificacionComunicacion, int calificacionGeneral, String comentario,
                        LocalDateTime fechaResena, String respuestaPropietario, LocalDateTime fechaRespuesta) {
        this.idResena = idResena;
        this.reservacion = reservacion;
        this.calificacionLimpieza = calificacionLimpieza;
        this.calificacionUbicacion = calificacionUbicacion;
        this.calificacionComunicacion = calificacionComunicacion;
        this.calificacionGeneral = calificacionGeneral;
        this.comentario = comentario;
        this.fechaResena = fechaResena;
        this.respuestaPropietario = respuestaPropietario;
        this.fechaRespuesta = fechaRespuesta;
    }

    // 🔹 Getters y Setters
    public Long getIdResena() {
        return idResena;
    }

    public void setIdResena(Long idResena) {
        this.idResena = idResena;
    }

    public ReservaModel getReservacion() {
        return reservacion;
    }

    public void setReservacion(ReservaModel reservacion) {
        this.reservacion = reservacion;
    }

    public int getCalificacionLimpieza() {
        return calificacionLimpieza;
    }

    public void setCalificacionLimpieza(int calificacionLimpieza) {
        this.calificacionLimpieza = calificacionLimpieza;
    }

    public int getCalificacionUbicacion() {
        return calificacionUbicacion;
    }

    public void setCalificacionUbicacion(int calificacionUbicacion) {
        this.calificacionUbicacion = calificacionUbicacion;
    }

    public int getCalificacionComunicacion() {
        return calificacionComunicacion;
    }

    public void setCalificacionComunicacion(int calificacionComunicacion) {
        this.calificacionComunicacion = calificacionComunicacion;
    }

    public int getCalificacionGeneral() {
        return calificacionGeneral;
    }

    public void setCalificacionGeneral(int calificacionGeneral) {
        this.calificacionGeneral = calificacionGeneral;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDateTime getFechaResena() {
        return fechaResena;
    }

    public void setFechaResena(LocalDateTime fechaResena) {
        this.fechaResena = fechaResena;
    }

    public String getRespuestaPropietario() {
        return respuestaPropietario;
    }

    public void setRespuestaPropietario(String respuestaPropietario) {
        this.respuestaPropietario = respuestaPropietario;
    }

    public LocalDateTime getFechaRespuesta() {
        return fechaRespuesta;
    }

    public void setFechaRespuesta(LocalDateTime fechaRespuesta) {
        this.fechaRespuesta = fechaRespuesta;
    }
}

