package com.blue.Apartamento.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "property_images")
public class PropertyImagesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_imagen")
    private Long idImagen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_propiedad", nullable = false)
    private PropertyModels propiedad;

    @Column(name = "url_imagen", nullable = false, length = 255)
    private String urlImagen;

    @Column(name = "orden")
    private int orden;

    @Column(name = "es_principal", nullable = false)
    private boolean esPrincipal;

    @Column(name = "fecha_subida", nullable = false)
    private LocalDateTime fechaSubida;

    // 🧱 Constructor vacío (requerido por JPA)
    public PropertyImagesModel() {
    }

    // 🏗️ Constructor con parámetros
    public PropertyImagesModel(Long idImagen, PropertyModels propiedad, String urlImagen, int orden, boolean esPrincipal, LocalDateTime fechaSubida) {
        this.idImagen = idImagen;
        this.propiedad = propiedad;
        this.urlImagen = urlImagen;
        this.orden = orden;
        this.esPrincipal = esPrincipal;
        this.fechaSubida = fechaSubida;
    }

    // 🔹 Getters y Setters
    public Long getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(Long idImagen) {
        this.idImagen = idImagen;
    }

    public PropertyModels getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(PropertyModels propiedad) {
        this.propiedad = propiedad;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public boolean isEsPrincipal() {
        return esPrincipal;
    }

    public void setEsPrincipal(boolean esPrincipal) {
        this.esPrincipal = esPrincipal;
    }

    public LocalDateTime getFechaSubida() {
        return fechaSubida;
    }

    public void setFechaSubida(LocalDateTime fechaSubida) {
        this.fechaSubida = fechaSubida;
    }
}

