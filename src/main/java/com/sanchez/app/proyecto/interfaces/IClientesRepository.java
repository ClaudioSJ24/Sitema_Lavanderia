package com.sanchez.app.proyecto.interfaces;

import com.sanchez.app.proyecto.models.Cliente;

import java.sql.SQLException;

public interface IClientesRepository extends IRepository<Cliente>{

    Long guardarReturnId(Cliente cliente) throws SQLException;
}
