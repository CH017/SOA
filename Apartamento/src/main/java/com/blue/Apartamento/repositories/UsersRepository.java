package com.blue.Apartamento.repositories;

import com.blue.Apartamento.models.UsersModel;
import com.blue.Apartamento.models.UsersModel.TipoUsuario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<UsersModel, Long> {
List<UsersModel> findByTipo(TipoUsuario tipo);
}

