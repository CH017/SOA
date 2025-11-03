package com.blue.Apartamento.repositories;

import com.blue.Apartamento.models.MensajesModel;
import com.blue.Apartamento.models.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MensajesRepository extends JpaRepository<MensajesModel, Long> {

    // 🔹 Buscar todos los mensajes enviados por un usuario
    List<MensajesModel> findByRemitente(UsersModel remitente);

    // 🔹 Buscar todos los mensajes recibidos por un usuario
    List<MensajesModel> findByDestinatario(UsersModel destinatario);

    // 🔹 Buscar mensajes no leídos por destinatario
    List<MensajesModel> findByDestinatarioAndLeidoFalse(UsersModel destinatario);
}
