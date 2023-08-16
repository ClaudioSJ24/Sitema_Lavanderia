package com.sanchez.app.proyecto.interfaces;

import com.sanchez.app.proyecto.models.Registro;

import java.sql.SQLException;

public interface IRegistrosService extends IService<Registro>{

    Long guardaReturnId(Registro registro) throws SQLException;
}
