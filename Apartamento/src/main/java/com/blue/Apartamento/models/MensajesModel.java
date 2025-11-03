package com.blue.Apartamento.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Mensajes")
public class MensajesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mensaje")
    private Long idMensaje;

    // 🔹 FK: Remitente (usuario que envía el mensaje)
    @ManyToOne
    @JoinColumn(name = "id_remitente", referencedColumnName = "id_usuario", nullable = false)
    private UsersModel remitente;

    // 🔹 FK: Destinatario (usuario que recibe el mensaje)
    @ManyToOne
    @JoinColumn(name = "id_destinatario", referencedColumnName = "id_usuario", nullable = false)
    private UsersModel destinatario;

    // 🔹 FK: Reservación asociada al mensaje
    @ManyToOne
    @JoinColumn(name = "id_reservacion", referencedColumnName = "id_reservacion", nullable = false)
    private ReservaModel reservacion;

    @Column(name = "asunto", nullable = false, length = 100)
    private String asunto;

    @Column(name = "contenido", columnDefinition = "TEXT", nullable = false)
    private String contenido;

    @Column(name = "leido", nullable = false)
    private boolean leido = false;

    @Column(name = "fecha_envio", nullable = false)
    private LocalDateTime fechaEnvio = LocalDateTime.now();

    // 🔹 Constructor vacío
    public MensajesModel() {}

    // 🔹 Constructor completo
    public MensajesModel(Long idMensaje, UsersModel remitente, UsersModel destinatario, 
                         ReservaModel reservacion, String asunto, String contenido, 
                         boolean leido, LocalDateTime fechaEnvio) {
        this.idMensaje = idMensaje;
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.reservacion = reservacion;
        this.asunto = asunto;
        this.contenido = contenido;
        this.leido = leido;
        this.fechaEnvio = fechaEnvio;
    }

    // 🔹 Getters y Setters
    public Long getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(Long idMensaje) {
        this.idMensaje = idMensaje;
    }

    public UsersModel getRemitente() {
        return remitente;
    }

    public void setRemitente(UsersModel remitente) {
        this.remitente = remitente;
    }

    public UsersModel getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(UsersModel destinatario) {
        this.destinatario = destinatario;
    }

    public ReservaModel getReservacion() {
        return reservacion;
    }

    public void setReservacion(ReservaModel reservacion) {
        this.reservacion = reservacion;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public boolean isLeido() {
        return leido;
    }

    public void setLeido(boolean leido) {
        this.leido = leido;
    }

    public LocalDateTime getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(LocalDateTime fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }
}
