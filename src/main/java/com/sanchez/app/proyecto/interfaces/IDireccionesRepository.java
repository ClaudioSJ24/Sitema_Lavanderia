package com.sanchez.app.proyecto.interfaces;

import com.sanchez.app.proyecto.models.Direccion;

import java.sql.SQLException;

public interface IDireccionesRepository extends IRepository<Direccion>{

    Long guardarReturnId(Direccion direccion) throws SQLException;
}
