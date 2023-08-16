package com.sanchez.app.proyecto.interfaces;

import com.sanchez.app.proyecto.models.Registro;

import java.sql.SQLException;

public interface IRegistrosRepository extends IRepository<Registro>{

    Long guardarReturnId(Registro registro) throws SQLException;
}
