package com.blue.Apartamento.models;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Propiedades")
public class PropertyModels {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_property")
    private Long id;

    // Relación con el propietario (solo si el usuario es de tipo PROPIETARIO)
    @ManyToOne
    @JoinColumn(name = "id_propietario", referencedColumnName = "id_usuario")
    private UsersModel propietario;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 20)
    private TipoPropiedad tipo;

    @Column(name = "titulo", nullable = false, length = 100)
    private String titulo;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "direccion", columnDefinition = "TEXT")
    private String direccion;

    @Column(name = "ciudad", length = 50)
    private String ciudad;

    @Column(name = "codigo_postal", length = 15)
    private String codigoPostal;

    @Column(name = "pais", length = 50)
    private String pais;

    @Column(name = "latitud", precision = 10, scale = 6)
    private BigDecimal latitud;

    @Column(name = "longitud", precision = 10, scale = 6)
    private BigDecimal longitud;

    @Column(name = "precio_noche", precision = 10, scale = 2)
    private BigDecimal precioNoche;

    @Column(name = "capacidad_personas")
    private int capacidadPersonas;

    @Column(name = "numero_habitaciones")
    private int numeroHabitaciones;

    @Column(name = "numero_banos")
    private int numeroBanos;

    @Column(name = "metros_cuadrados")
    private int metrosCuadrados;

    @Column(name = "comodidades", columnDefinition = "TEXT")
    private String comodidades;

    @Column(name = "normas_casa", columnDefinition = "TEXT")
    private String normasCasa;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 20)
    private EstadoPropiedad estado = EstadoPropiedad.DISPONIBLE;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    // --- Constructores ---
    public PropertyModels() {
    }

    public PropertyModels(UsersModel propietario, TipoPropiedad tipo, String titulo, String descripcion, String direccion,
                          String ciudad, String codigoPostal, String pais, BigDecimal latitud, BigDecimal longitud,
                          BigDecimal precioNoche, int capacidadPersonas, int numeroHabitaciones, int numeroBanos,
                          int metrosCuadrados, String comodidades, String normasCasa,
                          EstadoPropiedad estado, LocalDateTime fechaCreacion, LocalDateTime fechaActualizacion) {
        this.propietario = propietario;
        this.tipo = tipo;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
        this.pais = pais;
        this.latitud = latitud;
        this.longitud = longitud;
        this.precioNoche = precioNoche;
        this.capacidadPersonas = capacidadPersonas;
        this.numeroHabitaciones = numeroHabitaciones;
        this.numeroBanos = numeroBanos;
        this.metrosCuadrados = metrosCuadrados;
        this.comodidades = comodidades;
        this.normasCasa = normasCasa;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
    }

    // --- Getters y Setters ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsersModel getPropietario() {
        return propietario;
    }

    public void setPropietario(UsersModel propietario) {
        this.propietario = propietario;
    }

    public TipoPropiedad getTipo() {
        return tipo;
    }

    public void setTipo(TipoPropiedad tipo) {
        this.tipo = tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public BigDecimal getLatitud() {
        return latitud;
    }

    public void setLatitud(BigDecimal latitud) {
        this.latitud = latitud;
    }

    public BigDecimal getLongitud() {
        return longitud;
    }

    public void setLongitud(BigDecimal longitud) {
        this.longitud = longitud;
    }

    public BigDecimal getPrecioNoche() {
        return precioNoche;
    }

    public void setPrecioNoche(BigDecimal precioNoche) {
        this.precioNoche = precioNoche;
    }

    public int getCapacidadPersonas() {
        return capacidadPersonas;
    }

    public void setCapacidadPersonas(int capacidadPersonas) {
        this.capacidadPersonas = capacidadPersonas;
    }

    public int getNumeroHabitaciones() {
        return numeroHabitaciones;
    }

    public void setNumeroHabitaciones(int numeroHabitaciones) {
        this.numeroHabitaciones = numeroHabitaciones;
    }

    public int getNumeroBanos() {
        return numeroBanos;
    }

    public void setNumeroBanos(int numeroBanos) {
        this.numeroBanos = numeroBanos;
    }

    public int getMetrosCuadrados() {
        return metrosCuadrados;
    }

    public void setMetrosCuadrados(int metrosCuadrados) {
        this.metrosCuadrados = metrosCuadrados;
    }

    public String getComodidades() {
        return comodidades;
    }

    public void setComodidades(String comodidades) {
        this.comodidades = comodidades;
    }

    public String getNormasCasa() {
        return normasCasa;
    }

    public void setNormasCasa(String normasCasa) {
        this.normasCasa = normasCasa;
    }

    public EstadoPropiedad getEstado() {
        return estado;
    }

    public void setEstado(EstadoPropiedad estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    // --- Enums internos ---
    public enum TipoPropiedad {
        LOTE,
        HABITACION,
        EDIFICIO
    }

    public enum EstadoPropiedad {
        RESERVADO,
        OCUPADO,
        DISPONIBLE,
        MANTENIMIENTO
    }
}
