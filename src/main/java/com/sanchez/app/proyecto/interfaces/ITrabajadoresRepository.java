package com.sanchez.app.proyecto.interfaces;

import com.sanchez.app.proyecto.models.Trabajador;

import java.sql.SQLException;

public interface ITrabajadoresRepository extends IRepository<Trabajador>{
    Long guardarReturnId(Trabajador trabajador) throws SQLException;

}
