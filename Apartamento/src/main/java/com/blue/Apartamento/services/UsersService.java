package com.blue.Apartamento.services;

import com.blue.Apartamento.models.UsersModel;
import com.blue.Apartamento.models.UsersModel.TipoUsuario;
import com.blue.Apartamento.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    // Obtener todos los usuarios
    public List<UsersModel> listarUsuarios() {
        return usersRepository.findAll();
    }

    // Obtener un usuario por su ID
    public Optional<UsersModel> obtenerPorId(Long id) {
        return usersRepository.findById(id);
    }

    // Guardar o actualizar un usuario
    public UsersModel guardarUsuario(UsersModel usuario) {
        // Aquí podrías agregar validaciones o cifrado de contraseña si quieres
        return usersRepository.save(usuario);
    }

    // Eliminar un usuario por su ID
    public boolean eliminarUsuario(Long id) {
        try {
            usersRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // 🔹 Buscar usuarios por tipo
    public List<UsersModel> getUsersByTipo(TipoUsuario tipo) {
        return usersRepository.findByTipo(tipo);
    }
}