package com.blue.Apartamento.services;

import com.blue.Apartamento.models.MensajesModel;
import com.blue.Apartamento.models.UsersModel;
import com.blue.Apartamento.repositories.MensajesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MensajesService {

    @Autowired
    private MensajesRepository mensajesRepository;

    // 🔹 Obtener todos los mensajes
    public List<MensajesModel> getAllMensajes() {
        return mensajesRepository.findAll();
    }

    // 🔹 Obtener un mensaje por ID
    public Optional<MensajesModel> getMensajeById(Long id) {
        return mensajesRepository.findById(id);
    }

    // 🔹 Guardar o actualizar un mensaje
    public MensajesModel saveMensaje(MensajesModel mensaje) {
        return mensajesRepository.save(mensaje);
    }

    // 🔹 Eliminar un mensaje
    public void deleteMensaje(Long id) {
        mensajesRepository.deleteById(id);
    }

    // 🔹 Buscar mensajes enviados por un usuario
    public List<MensajesModel> getMensajesEnviados(UsersModel remitente) {
        return mensajesRepository.findByRemitente(remitente);
    }

    // 🔹 Buscar mensajes recibidos por un usuario
    public List<MensajesModel> getMensajesRecibidos(UsersModel destinatario) {
        return mensajesRepository.findByDestinatario(destinatario);
    }

    // 🔹 Buscar mensajes no leídos por un usuario
    public List<MensajesModel> getMensajesNoLeidos(UsersModel destinatario) {
        return mensajesRepository.findByDestinatarioAndLeidoFalse(destinatario);
    }

    // 🔹 Marcar un mensaje como leído
    public MensajesModel marcarComoLeido(Long id) {
        Optional<MensajesModel> mensajeOpt = mensajesRepository.findById(id);
        if (mensajeOpt.isPresent()) {
            MensajesModel mensaje = mensajeOpt.get();
            mensaje.setLeido(true);
            return mensajesRepository.save(mensaje);
        }
        return null;
    }
}
