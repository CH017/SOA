package com.blue.Apartamento.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "Usuarios")
public class UsersModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_usuario", nullable = false, length = 20)
    private TipoUsuario tipo = TipoUsuario.CLIENTE;

    @Column(name = "nombre", nullable = false, length = 35)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 35)
    private String apellido;

    @Column(name = "email", unique = true, nullable = false, length = 50)
    private String email;

    @Column(name = "telefono", length = 15)
    private String telefono;

    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;

    @Column(name = "documento_identidad", unique = true, length = 20)
    private String documentoIdentidad;

    @Column(name = "direccion", length = 100)
    private String direccion;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fechaRegistro;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoUsuario estado;

    @Column(name = "contrasena_hash", nullable = false, length = 100)
    private String contrasenaHash;

    @Column(name = "ultimo_login")
    private LocalDateTime ultimoLogin;

    // --- Constructores ---
    public UsersModel() {
    }

    public UsersModel(TipoUsuario tipo, String nombre, String apellido, String email, String telefono,
                      Date fechaNacimiento, String documentoIdentidad, String direccion,
                      LocalDateTime fechaRegistro, EstadoUsuario estado, String contrasenaHash,
                      LocalDateTime ultimoLogin) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.documentoIdentidad = documentoIdentidad;
        this.direccion = direccion;
        this.fechaRegistro = fechaRegistro;
        this.estado = estado;
        this.contrasenaHash = contrasenaHash;
        this.ultimoLogin = ultimoLogin;
    }

    // --- Getters y Setters ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public void setDocumentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public EstadoUsuario getEstado() {
        return estado;
    }

    public void setEstado(EstadoUsuario estado) {
        this.estado = estado;
    }

    public String getContrasenaHash() {
        return contrasenaHash;
    }

    public void setContrasenaHash(String contrasenaHash) {
        this.contrasenaHash = contrasenaHash;
    }

    public LocalDateTime getUltimoLogin() {
        return ultimoLogin;
    }

    public void setUltimoLogin(LocalDateTime ultimoLogin) {
        this.ultimoLogin = ultimoLogin;
    }

    // --- Enums internos ---
    public enum TipoUsuario {
        PROPIETARIO,
        CLIENTE,
        EMPRESA
    }

    public enum EstadoUsuario {
        ACTIVO,
        INACTIVO,
        SUSPENDIDO
    }
}